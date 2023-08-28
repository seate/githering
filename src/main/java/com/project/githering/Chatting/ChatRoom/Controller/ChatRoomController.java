package com.project.githering.Chatting.ChatRoom.Controller;

import com.project.githering.Chatting.ChatRoom.DTO.*;
import com.project.githering.Chatting.ChatRoom.Entity.ChatRoom;
import com.project.githering.Chatting.ChatRoom.Exception.ChatRoomNotExistException;
import com.project.githering.Chatting.ChatRoom.Service.ChatRoomService;
import com.project.githering.User.General.Service.GeneralUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    private final GeneralUserService generalUserService;


    //CREATE
    @PostMapping
    public ResponseEntity<Long> createChatRoom(@RequestBody @Valid CreateChatRoomRequestDTO createChatRoomRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        Long chatRoomId = chatRoomService.createChatRoom(userId, createChatRoomRequestDTO);

        return new ResponseEntity<>(chatRoomId, HttpStatus.CREATED);
    }

    @PostMapping("/invite")
    public ResponseEntity<Void> inviteChatRoom(@RequestBody @Valid InviteChatRoomRequestDTO inviteChatRoomRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        chatRoomService.inviteChatRoom(userId, inviteChatRoomRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId) {
        Long userId = generalUserService.findIdByAuthentication();
        chatRoomService.deleteChatRoomById(userId, chatRoomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{chatRoomId}/leave")
    public ResponseEntity<Void> leaveChatRoom(@PathVariable Long chatRoomId) {
        Long userId = generalUserService.findIdByAuthentication();
        chatRoomService.leaveChatRoom(userId, chatRoomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //READ
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDTO> findChatRoomById(@PathVariable Long chatRoomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(chatRoomId)
                .orElseThrow(ChatRoomNotExistException::new);

        return new ResponseEntity<>(new ChatRoomResponseDTO(chatRoom), HttpStatus.OK);
    }

    @GetMapping("/findAllByUserId")
    public ResponseEntity<ChatRoomListResponseDTO> findAllByUserId() {
        Long userId = generalUserService.findIdByAuthentication();
        ChatRoomListResponseDTO chatRoomListResponseDTO = new ChatRoomListResponseDTO(chatRoomService.findAllByUserId(userId));

        return new ResponseEntity<>(chatRoomListResponseDTO, HttpStatus.OK);
    }

    //UPDATE
    @PatchMapping("/master")
    public ResponseEntity<Void> updateMaster(@RequestBody @Valid UpdateChatRoomMasterRequestDTO updateChatRoomMasterRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        chatRoomService.updateMaster(userId, updateChatRoomMasterRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/name")
    public ResponseEntity<Void> updateName(@RequestBody @Valid UpdateChatRoomNameRequestDTO updateChatRoomNameRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        chatRoomService.updateName(userId, updateChatRoomNameRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}