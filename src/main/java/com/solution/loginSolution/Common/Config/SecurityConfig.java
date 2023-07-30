package com.solution.loginSolution.Common.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.loginSolution.JWT.Service.LogoutAccessTokenService;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.JWT.auth.JwtAuthenticationFilter;
import com.solution.loginSolution.JWT.auth.JwtAuthorizationFilter;
import com.solution.loginSolution.JWT.auth.JwtTokenProvider;
import com.solution.loginSolution.User.General.Service.GeneralUserService;
import com.solution.loginSolution.User.OAuth2.Handler.oAuth2AuthenticationFailureHandler;
import com.solution.loginSolution.User.OAuth2.Handler.oAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final CorsConfigurationSource corsConfigurationSource;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenService refreshTokenService;

    private final GeneralUserService generalUserService;

    private final oAuth2AuthenticationSuccessHandler oAuth2SuccessHandler;

    private final oAuth2AuthenticationFailureHandler oAuth2FailureHandler;

    private final LogoutAccessTokenService logoutAccessTokenService;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{

        http
                .cors(httpSecurityCorsConfigurer ->
                    httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource)
                )

                .csrf(AbstractHttpConfigurer::disable)
                //.formLogin(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults()) // TODO 실험용 form login
                .httpBasic(AbstractHttpConfigurer::disable)

                .headers(headers ->
                        headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                                .cacheControl(HeadersConfigurer.CacheControlConfig::disable))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .logout(AbstractHttpConfigurer::disable)

                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .successHandler(oAuth2SuccessHandler)
                                .failureHandler(oAuth2FailureHandler)
                )

                .addFilterBefore(
                        JwtAuthenticationFilter.builder()
                                .authenticationManager(authenticationManager)
                                .jwtTokenProvider(jwtTokenProvider)
                                .objectMapper(objectMapper)
                                .refreshTokenService(refreshTokenService)
                                .generalUserService(generalUserService)
                                .build(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterAfter(
                        new JwtAuthorizationFilter(jwtTokenProvider, generalUserService, logoutAccessTokenService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");
        return roleHierarchy;
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}