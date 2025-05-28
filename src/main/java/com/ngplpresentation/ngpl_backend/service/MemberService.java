package com.ngplpresentation.ngpl_backend.service;

import com.ngplpresentation.ngpl_backend.dto.PasswordValidateRequest;
import com.ngplpresentation.ngpl_backend.dto.response.Status;
import com.ngplpresentation.ngpl_backend.handler.GeneralException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final int PWD_MIN_LENGTH = 8;

    private final int PWD_MAX_LENGTH = 16;

    public Boolean validatePassword(PasswordValidateRequest req) {
        String pwdToValidate = req.getPassword();
        String pwdConfirmString = req.getConfirmPassword();

        if (pwdToValidate == null || pwdConfirmString == null)
            throw new GeneralException(Status.BAD_REQUEST_ARGUMENT);

        if (pwdToValidate.length() < PWD_MIN_LENGTH || req.getPassword().length() > PWD_MAX_LENGTH)
            return false;

        if (!pwdToValidate.equals(pwdConfirmString))
            return false;

        return true;
    }
}
