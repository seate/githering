package com.project.githering.Chatting.Config;

import com.project.githering.Chatting.StompUtil.StompCustomErrorHandler;
import com.project.githering.Chatting.StompUtil.StompPreHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    private final StompPreHandler stompPreHandler;

    private final StompCustomErrorHandler stompCustomErrorHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();

        registry.setErrorHandler(stompCustomErrorHandler);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setPathMatcher(new AntPathMatcher(".")); // url을 chat/room/3 -> chat.room.3으로 참조하기 위한 설정

        // 메시지 발행 요청 -> 클라이언트에서 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");

        // 메시지 구독 요청 -> 클라이언트에서 메시지 받을 때
        //registry.enableSimpleBroker("/sub"); //rabbitmq를 사용하지 않을 경우
        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue"); //rabitmq를 사용할 경우
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompPreHandler);
    }
}
