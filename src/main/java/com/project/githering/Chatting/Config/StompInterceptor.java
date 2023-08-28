package com.project.githering.Chatting.Config;

import com.project.githering.Chatting.ChatRoom.Exception.NoAuthorityException;
import com.project.githering.Chatting.ChatRoom.Service.ChatRoomService;
import com.project.githering.JWT.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE) // security filter 이전에 실행되도록 설정
public class StompInterceptor implements ChannelInterceptor {

    private final ChatRoomService chatRoomService;

    private final JwtTokenProvider jwtTokenProvider;

    private final StompSessionUtil stompSessionUtil;


    @Value("${jwt.accessToken.GettingHeaderName}")
    private String accessTokenHeaderName;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand curCommand = accessor.getCommand();

        //세션 수립 시에 세션에 사용자 인증 정보 저장
        if (StompCommand.CONNECT.equals(curCommand)) {
            validateAccessToken(accessor);
            stompSessionUtil.setAuthentication(accessor);
        }

        //구독 및 채팅 작성시 권한 검증
        else if ((StompCommand.SEND.equals(curCommand) || StompCommand.SUBSCRIBE.equals(curCommand)) && !validate(accessor))
            throw new NoAuthorityException("이 채팅방에 포함되지 않은 사용자입니다.");

        return ChannelInterceptor.super.preSend(message, channel);
    }

    private String validateAccessToken(StompHeaderAccessor accessor) {
        String accessToken = String.valueOf(accessor.getNativeHeader(accessTokenHeaderName).get(0));
        jwtTokenProvider.validateAccessToken(accessToken);
        return accessToken;
    }

    private Boolean validate(StompHeaderAccessor accessor) {
        //accessToken 검증
        String accessToken = validateAccessToken(accessor);
        Long userId = jwtTokenProvider.getUserIdByAccessToken(accessToken);

        // 채팅방에 포함되어 있는지 검증
        String destination = accessor.getDestination();
        Long chatRoomId = Long.parseLong(Objects.requireNonNull(destination).substring(destination.lastIndexOf('.') + 1));

        return chatRoomService.isMember(userId, chatRoomId);
    }
}
