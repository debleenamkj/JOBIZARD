package com.satckroute.applicationRegisterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = " Organization Detail not exist.")
public class OrganizationDetailsNotFoundException extends Exception
{
}
