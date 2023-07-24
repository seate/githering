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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Builder
    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            logger.info("authorization 시작 = " + request.getRequestURL());

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
            System.out.println("TokenExpired: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (TokenNotValidException | AccessTokenNotExistException e) { //
            System.out.println("token not valid: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } catch (Exception e) {
            System.out.println("unhandled error: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "처리되지 않은 에러입니다.");
        }
    }


    /*@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURL = request.getRequestURL().toString();
        System.out.println("author requestURL: " + requestURL);
        return !requestURL.startsWith(origin + "/api/");
    }*/
}
