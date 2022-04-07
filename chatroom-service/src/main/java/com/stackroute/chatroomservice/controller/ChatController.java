package com.stackroute.chatroomservice.controller;

import com.stackroute.chatroomservice.domain.ChatMessage;
import com.stackroute.chatroomservice.domain.ChatNotification;
import com.stackroute.chatroomservice.domain.ChatRoom;
import com.stackroute.chatroomservice.service.ChatMessageService;
import com.stackroute.chatroomservice.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ChatController {

    private SimpMessagingTemplate messagingTemplate;
    private ChatMessageService chatMessageService;
    private ChatRoomService chatRoomService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService, ChatRoomService chatRoomService){
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic")
    @PostMapping("/chat")
    public void processMessage(@RequestBody ChatMessage chatMessage){
        var chatId = chatRoomService.getChatId(chatMessage.getSenderId(),chatMessage.getRecipientId(),true);
        chatMessage.setChatId(chatId.get());
        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(saved.getId(),saved.getSenderId(),saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages (@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage (@PathVariable String id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }

    @GetMapping("/check")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/getall")
    public List<ChatMessage> getAll(){
        return chatMessageService.getAllList();
    }

    @GetMapping("/getchatroom")
    public List<ChatRoom> getChatroom(){
        return chatRoomService.getAllChats();
    }
}
