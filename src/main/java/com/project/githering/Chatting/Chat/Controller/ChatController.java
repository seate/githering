package com.project.githering.Chatting.Chat.Controller;

import com.project.githering.Chatting.Chat.DTO.ChatResponseListDTO;
import com.project.githering.Chatting.Chat.DTO.CreateChatRequestDTO;
import com.project.githering.Chatting.Chat.Entity.Chat;
import com.project.githering.Chatting.Chat.Service.ChatService;
import com.project.githering.Chatting.StompUtil.StompSessionUtil;
import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final RabbitTemplate rabbitTemplate;

    private final StompSessionUtil stompSessionUtil;

    private final GeneralUserService generalUserService;

    private final String CHAT_EXCHANGE_NAME = "chat.exchange";

    @MessageMapping("chat.message.{chatRoomId}")
    public void sendMessage(StompHeaderAccessor stompHeaderAccessor, CreateChatRequestDTO createChatRequestDTO, @DestinationVariable String chatRoomId) {
        GeneralUser generalUser = stompSessionUtil.getGeneralUserFromSession(stompHeaderAccessor);

        Chat chat = createChatRequestDTO.toEntity(generalUser.getId(),Long.parseLong(chatRoomId));

        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, "room." + chatRoomId, chat);
    }

    /**
     * send에서 chat을 저장하지 않고 RabbitListener로 queue에서 pop된 메시지를 저장하는 이유는
     * send에서 DB 저장을 구현하면 채팅을 작성할 때 부하가 늘어날 수 있기 때문
     */
    @RabbitListener(queues = "chat.queue", messageConverter = "jsonMessageConverter")
    public void receiveMessage(Chat chat) {
        chatService.saveChatting(chat);
    }

    @ResponseBody
    @GetMapping("/chat/chatRoom/{chatRoomId}")
    public ResponseEntity<ChatResponseListDTO> findChattingByChatRoomId(@PathVariable Long chatRoomId) {
        Long userId = generalUserService.findIdByAuthentication();
        return new ResponseEntity<>(chatService.findByChatRoomId(userId, chatRoomId), HttpStatus.OK);
    }
}
