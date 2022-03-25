package com.stackroute.chatroomservice.repository;

import com.stackroute.chatroomservice.domain.ChatMessage;
import com.stackroute.chatroomservice.domain.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(String chatId);
}
