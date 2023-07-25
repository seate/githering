package com.solution.loginSolution.JWT.auth;

import com.solution.loginSolution.JWT.Exception.AccessTokenExpiredException;
import com.solution.loginSolution.JWT.Exception.AccessTokenNotExistException;
import com.solution.loginSolution.JWT.Exception.RefreshTokenExpiredException;
import com.solution.loginSolution.JWT.Exception.TokenNotValidException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Builder
    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            log.debug("authorization 시작: {}", request.getRequestURL());

            String accessToken = jwtTokenProvider.getAccessTokenByRequest(request)
                    .orElseThrow(AccessTokenNotExistException::new);

            // logout된 token일 경우 TokenNotValidException
            // accessToken이 만료된 경우 AccessTokenExpiredException
            // accessToken이 만료되지 않은 경우 계속 진행
            jwtTokenProvider.validateAccessToken(accessToken);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                        jwtTokenProvider.getUserEmailByAccessToken(accessToken), null
                    )
            );

            chain.doFilter(request, response); //filter 계속 진행

        } catch (AccessTokenExpiredException | RefreshTokenExpiredException e){
            log.debug("TokenExpired: ", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (TokenNotValidException | AccessTokenNotExistException e) { //
            log.warn("TokenNotValid: ", e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } catch (Exception e) {
            log.error("unhandled error: ", e);
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "처리되지 않은 에러입니다.");
        }
    }
}
