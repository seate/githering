package com.solution.loginSolution.JWT.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.User.General.DTO.GeneralUserLoginRequestDTO;
import com.solution.loginSolution.User.General.Entity.PrincipalDetails;
import com.solution.loginSolution.User.General.Service.GeneralUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final GeneralUserService generalUserService;

    @Builder
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService, GeneralUserService generalUserService) throws Exception {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.generalUserService = generalUserService;

        setFilterProcessesUrl("/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("attempt Authentication");
        GeneralUserLoginRequestDTO generalUserLoginRequestDTO = null;

        try {
            // 이 부분을 위해서 MemberRequestDTO에 NoArgsConStructor가 필요함
            generalUserLoginRequestDTO = objectMapper.readValue(request.getInputStream(), GeneralUserLoginRequestDTO.class);
        }
        catch (Exception e) {
            log.error("jwtAuthenticationFilter error");
        }
        assert generalUserLoginRequestDTO != null;

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                generalUserLoginRequestDTO.getUserEmail(), generalUserLoginRequestDTO.getPassword());

        // 이곳에서 authenticate하기 위해 CustomUserDetailsService의 loadUserByUsername()이 실행됨
        // 따라서 loadUserByUsername()에서는 UserDetails를 구현한 클래스를 return해야함
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        log.info("authentication success");

        String userEmail = ((PrincipalDetails) authResult.getPrincipal()).getUsername();

        Long userId = generalUserService.findIdByUserEmail(userEmail);

        String accessToken = jwtTokenProvider.createAccessToken(userId, userEmail);
        String refreshToken = jwtTokenProvider.createRefreshToken(userId, userEmail);

        refreshTokenService.saveOrUpdate(new RefreshTokenRequestDTO(refreshToken).toEntity(jwtTokenProvider)); // 새로 발급한 refreshToken 저장


        // body에 tokens 작성
        //HashMap<String, Object> responseBodyWriting = new HashMap<>();
        Map<String, Object> responseBodyWriting = jwtTokenProvider.generateResponseBody(userId, userEmail);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // body type 설정
        response.getWriter().print(objectMapper.writeValueAsString(responseBodyWriting)); //body에 작성


        // header에 tokens 작성
        //response.addHeader(accessTokenHeaderName, accessToken);
        //response.addHeader(refreshTokenHeaderName, refreshToken);
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        log.info("authentication fail");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
