package com.stackroute.chatroomservice.repository;

import com.stackroute.chatroomservice.domain.ChatMessage;
import com.stackroute.chatroomservice.domain.MessageStatus;
import com.stackroute.chatroomservice.dto.ReceiverIdGroup;
import com.stackroute.chatroomservice.dto.SenderIdGroup;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(String chatId);

    @Aggregation("{$group: {_id: $chatId, senderIds: { $addToSet: $senderId}}}")
    List<SenderIdGroup> groupByChatIdAndSenderId();

    @Aggregation("{$group: {_id: $chatId, recipientIds: { $addToSet: $recipientId}}}")
    List<ReceiverIdGroup> groupByChatIdAndRecipientId();
}
