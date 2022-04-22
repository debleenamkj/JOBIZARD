package com.stackroute.chatroomservice.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class ReceiverIdGroup {
    @Id
    private String chatId;
    private List<String> recipientIds;
}
