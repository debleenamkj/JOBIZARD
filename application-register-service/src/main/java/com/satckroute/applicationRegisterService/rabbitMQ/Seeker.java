package com.satckroute.applicationRegisterService.rabbitMQ;

import com.satckroute.applicationRegisterService.domain.Address;
import com.satckroute.applicationRegisterService.domain.Details;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class Seeker
{
    private String email;
    private ArrayList education;
    private ArrayList skillSet;
}
