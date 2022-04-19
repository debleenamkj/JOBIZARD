package com.satckroute.applicationRegisterService.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    private String userEmailId;
    private String userName;
    private byte[] userImage;
}
