package com.project.githering.Chatting.StompUtil;


import com.project.githering.Chatting.ChatRoom.Exception.NoAuthorityException;
import com.project.githering.JWT.Exception.AccessTokenExpiredException;
import com.project.githering.JWT.Exception.AccessTokenNotExistException;
import com.project.githering.JWT.Exception.TokenNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class StompCustomErrorHandler extends StompSubProtocolErrorHandler {

    public StompCustomErrorHandler() {
        super();
    }

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {

        if (ex instanceof MessageDeliveryException) {
            Throwable cause = ex.getCause();

            if (cause instanceof AccessTokenExpiredException
                    || cause instanceof AccessTokenNotExistException
                    || cause instanceof TokenNotValidException
                    || cause instanceof NullPointerException
                    || cause instanceof NoAuthorityException) {

                log.warn("chat message error occurred");

                StompHeaderAccessor errorAccessor = StompHeaderAccessor.create(StompCommand.ERROR);

                errorAccessor.setMessage(cause.getMessage());
                errorAccessor.setLeaveMutable(true);

                return MessageBuilder.createMessage(
                        cause.getMessage().getBytes(StandardCharsets.UTF_8),
                        errorAccessor.getMessageHeaders()
                );
            }
            else {
                String errorMessage = "예상치 못한 에러가 발생했습니다.";

                StompHeaderAccessor errorAccessor = StompHeaderAccessor.create(StompCommand.ERROR);

                errorAccessor.setMessage(errorMessage);
                errorAccessor.setLeaveMutable(true);

                return MessageBuilder.createMessage(
                        errorMessage.getBytes(),
                        errorAccessor.getMessageHeaders()
                );
            }
        }

        return super.handleClientMessageProcessingError(clientMessage, ex);
    }
}
