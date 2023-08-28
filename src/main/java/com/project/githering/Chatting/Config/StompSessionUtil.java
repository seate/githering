package com.project.githering.Chatting.Config;

import com.project.githering.JWT.auth.JwtTokenProvider;
import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Entity.PrincipalDetails;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompSessionUtil {

    private final GeneralUserService generalUserService;

    private final JwtTokenProvider jwtTokenProvider;

    private final String sessionAuthenticationName = "Authentication";

    @Value("${jwt.accessToken.GettingHeaderName}")
    private String accessTokenHeaderName;

    public void setAuthentication(StompHeaderAccessor stompHeaderAccessor) {
        String accessToken = String.valueOf(stompHeaderAccessor.getNativeHeader(accessTokenHeaderName).get(0));

        GeneralUser generalUser = generalUserService.findById(jwtTokenProvider.getUserIdByAccessToken(accessToken))
                .orElseThrow(UserNotExistException::new);


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                generalUser,
                null,
                new PrincipalDetails(generalUser).getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        stompHeaderAccessor.getSessionAttributes().put(sessionAuthenticationName, authentication);
    }

    public GeneralUser getGeneralUserFromSession(StompHeaderAccessor stompHeaderAccessor) {
        return (GeneralUser) (
                (UsernamePasswordAuthenticationToken) stompHeaderAccessor.getSessionAttributes().get(sessionAuthenticationName)
        ).getPrincipal();
    }
}
