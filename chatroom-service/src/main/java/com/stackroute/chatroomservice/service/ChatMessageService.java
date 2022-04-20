package com.stackroute.chatroomservice.service;

import com.stackroute.chatroomservice.domain.ChatMessage;
import com.stackroute.chatroomservice.domain.MessageStatus;
import com.stackroute.chatroomservice.dto.ReceiverIdGroup;
import com.stackroute.chatroomservice.dto.SenderIdGroup;
import com.stackroute.chatroomservice.exception.ResourceNotFoundException;
import com.stackroute.chatroomservice.repository.ChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ChatMessageService {

    private ChatMessageRepository repository;
    private ChatRoomService chatRoomService;
    private MongoOperations mongoOperations;

    @Autowired
    public ChatMessageService(ChatMessageRepository repository, ChatRoomService chatRoomService, MongoOperations mongoOperations){
        log.info("Autowiring Objects in ChatMessageService is Done");
        this.repository = repository;
        this.chatRoomService = chatRoomService;
        this.mongoOperations = mongoOperations;
    }

    public ChatMessage save(ChatMessage chatMessage) {
        log.debug("Inside ChatMessageService - save");
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        log.debug("Inside ChatMessageService - countNewMessages");
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        log.debug("Inside ChatMessageService - findChatMessages");
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
        var messages = chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }
        return messages;
    }

    public ChatMessage findById(String id) {
        log.debug("Inside ChatMessageService - findById");
        return repository.findById(id).map(chatMessage -> {
            chatMessage.setStatus(MessageStatus.DELIVERED);
            return repository.save(chatMessage);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Can't find message (" + id + ")"));
    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        log.debug("Inside ChatMessageService - updateStatuses");
        Query query = new Query(Criteria.where("senderId").is(senderId)
                .and("recipientId").is(recipientId));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }

    public List<ChatMessage> getAllList(){
        log.debug("Inside ChatMessageService - getAllList");
        return repository.findAll();
    }

//    public SenderIdGroup getSenderMessages(String chatId){
//        List<SenderIdGroup> senderList = repository.groupByChatIdAndSenderId();
//        return senderList.stream().filter(o->chatId == o.getChatId()).;
//
//    }

//    public ReceiverIdGroup getRecipientMessages(){
//
//    }
}
