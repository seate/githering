package com.solution.loginSolution.JWT.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.loginSolution.JWT.DTO.RefreshTokenRequestDTO;
import com.solution.loginSolution.JWT.Service.RefreshTokenService;
import com.solution.loginSolution.User.Normal.DTO.UserLoginRequestDTO;
import com.solution.loginSolution.User.Normal.Entity.PrincipalDetails;
import com.solution.loginSolution.User.Normal.Service.UserService;
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
import java.util.HashMap;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String accessTokenHeaderName;
    private final String refreshTokenHeaderName;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @Builder
    public JwtAuthenticationFilter(String accessTokenHeaderName, String refreshTokenHeaderName, AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService, UserService userService) throws Exception {
        this.accessTokenHeaderName = accessTokenHeaderName;
        this.refreshTokenHeaderName = refreshTokenHeaderName;
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;

        setFilterProcessesUrl("/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("attempt Authentication");
        UserLoginRequestDTO userLoginRequestDTO = null;

        try {
            // 이 부분을 위해서 MemberRequestDTO에 NoArgsConStructor가 필요함
            userLoginRequestDTO = objectMapper.readValue(request.getInputStream(), UserLoginRequestDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assert userLoginRequestDTO != null;

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequestDTO.getUserEmail(), userLoginRequestDTO.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        log.info("authentication success");

        String userEmail = ((PrincipalDetails) authResult.getPrincipal()).getUsername();

        Long userId = userService.findIdByUserEmail(userEmail);

        String accessToken = jwtTokenProvider.createAccessToken(userId, userEmail);
        String refreshToken = jwtTokenProvider.createRefreshToken(userId, userEmail);

        refreshTokenService.saveOrUpdate(new RefreshTokenRequestDTO(refreshToken)); // 새로 발급한 refreshToken 저장


        // body에 tokens 작성
        HashMap<String, Object> responseBodyWriting = new HashMap<>();
        responseBodyWriting.put("id", userId); // body에 userId 추가
        responseBodyWriting.put(accessTokenHeaderName, accessToken); //body에 accessToken 추가
        responseBodyWriting.put(refreshTokenHeaderName, refreshToken); //body에 refreshToken 추가
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
