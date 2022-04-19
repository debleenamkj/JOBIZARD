package com.satckroute.applicationRegisterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Organization Details image already exist.")
public class OrganizationDetailsAlreadyExistException extends Exception {
}
