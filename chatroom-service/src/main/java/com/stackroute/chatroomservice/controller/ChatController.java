package com.stackroute.chatroomservice.controller;

import com.stackroute.chatroomservice.domain.ChatMessage;
import com.stackroute.chatroomservice.domain.ChatNotification;
import com.stackroute.chatroomservice.domain.ChatRoom;
import com.stackroute.chatroomservice.service.ChatMessageService;
import com.stackroute.chatroomservice.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin

@Slf4j
public class ChatController {

    private SimpMessagingTemplate messagingTemplate;
    private ChatMessageService chatMessageService;
    private ChatRoomService chatRoomService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService, ChatRoomService chatRoomService){
        log.info("Autowiring of Objects Done");
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic")
    @PostMapping("/chat")
    public void processMessage(@RequestBody ChatMessage chatMessage){
        log.debug("Inside ChatController - processMessage");
        var chatId = chatRoomService.getChatId(chatMessage.getSenderId(),chatMessage.getRecipientId(),true);
        chatMessage.setChatId(chatId.get());
        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(saved.getId(),saved.getSenderId(),saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        log.debug("Inside ChatController - processMessage");
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages (@PathVariable String senderId, @PathVariable String recipientId) {
        log.debug("Inside ChatController - findChatMessages");
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage (@PathVariable String id) {
        log.debug("Inside ChatController - findMessage");
        return ResponseEntity.ok(chatMessageService.findById(id));
    }

    @GetMapping("/check")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/getall")
    public List<ChatMessage> getAll(){
        log.debug("Inside ChatController - getAll");
        return chatMessageService.getAllList();
    }

    @GetMapping("/getchatroom")
    public List<ChatRoom> getChatroom(){
        log.debug("Inside ChatController - getChatroom");
        return chatRoomService.getAllChats();
    }



//    @GetMapping("/messagegroup/{chatId}")
//    public SenderIdGroup getMesssages(@PathVariable String chatId){
//        return chatMessageService.getSenderMessages(chatId);
//    }
}
