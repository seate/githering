package com.solution.loginSolution.JWT.auth;

import com.solution.loginSolution.JWT.Exception.AccessTokenExpiredException;
import com.solution.loginSolution.JWT.Exception.AccessTokenNotExistException;
import com.solution.loginSolution.JWT.Exception.TokenNotValidException;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Entity.PrincipalDetails;
import com.solution.loginSolution.User.General.Service.GeneralUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final GeneralUserService generalUserService;

    private final RequestMatcher excludeRequestMatcher;

    private final LogoutAccessTokenService logoutAccessTokenService;

    @Builder
    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider, GeneralUserService generalUserService, LogoutAccessTokenService logoutAccessTokenService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.generalUserService = generalUserService;
        this.logoutAccessTokenService = logoutAccessTokenService;
        this.excludeRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/users", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/users/emailCheck", HttpMethod.GET.name()),

                new AntPathRequestMatcher("/token/reIssue", HttpMethod.POST.name()),

                new AntPathRequestMatcher("/", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/login", HttpMethod.GET.name()), //TODO form login 제외 시 해제

                new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/v3/api-docs/**", HttpMethod.GET.name()),

                new AntPathRequestMatcher("/assets/**", HttpMethod.GET.name())
        );
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
            if (!logoutAccessTokenService.isValid(accessToken)) {
                throw new TokenNotValidException();
            }

            Long userId = jwtTokenProvider.getUserIdByAccessToken(accessToken);
            GeneralUser generalUser = generalUserService.findById(userId)
                    .orElseThrow(TokenNotValidException::new);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            generalUser,
                            null,
                            generalUserService.findById(userId)
                                    .map(PrincipalDetails::new)
                                    .orElseThrow(TokenNotValidException::new)
                                    .getAuthorities()
                    )
            );

            chain.doFilter(request, response); //filter 계속 진행

        } catch (AccessTokenExpiredException e){
            log.debug("AccessTokenExpired: ", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } catch (AccessTokenNotExistException e) {
            log.warn("Token Not Exist: ", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } catch (TokenNotValidException e) { //
            log.warn("TokenNotValid: ", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            log.error("unhandled error: ", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "처리되지 않은 에러입니다.");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return excludeRequestMatcher.matches(request);
    }
}
