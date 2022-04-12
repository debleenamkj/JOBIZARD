package com.stackroute.chatroomservice.service;

import com.stackroute.chatroomservice.domain.ChatRoom;
import com.stackroute.chatroomservice.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatRoomService {

    private ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository){
        log.info("Autowiring chatRoomRepository Done");
        this.chatRoomRepository = chatRoomRepository;
    }

    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist){
        log.debug("Inside ChatRoomService - getChatId");
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId)
                .or(()->{
                    if(!createIfNotExist){
                        return Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }

    public List<ChatRoom> getAllChats(){
        log.debug("Inside ChatRoomService - getAllChats");
        return chatRoomRepository.findAll();
    }
}
