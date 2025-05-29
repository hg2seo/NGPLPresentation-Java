package com.ngplpresentation.ngpl_backend.service;

import com.ngplpresentation.ngpl_backend.domain.Member;
import com.ngplpresentation.ngpl_backend.dto.DeleteRequest;
import com.ngplpresentation.ngpl_backend.dto.RegisterRequest;
import com.ngplpresentation.ngpl_backend.dto.UpdateRequest;
import com.ngplpresentation.ngpl_backend.dto.response.Status;
import com.ngplpresentation.ngpl_backend.handler.GeneralException;
import com.ngplpresentation.ngpl_backend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final int PWD_MIN_LENGTH = 8;

    private final int PWD_MAX_LENGTH = 16;

    private final MemberRepository memberRepository;

    public Member registerMember(RegisterRequest req) {
        validateUserInfo(req.getUserId(), req.getEmail(), req.getPassword(), req.getConfirmPassword());

        Member member = Member.builder()
                .userId(req.getUserId())
                .password(req.getPassword())
                .name(req.getName())
                .email(req.getEmail())
                .registered(LocalDateTime.now())
                .build();

        return memberRepository.save(member);
    }

    public void validateUserInfo(String userId, String email, String pwd, String confpwd) {
        if (pwd.length() < PWD_MIN_LENGTH || pwd.length() > PWD_MAX_LENGTH)
            throw new GeneralException(Status.BAD_REQUEST_ARGUMENT_PWD_MIN_MAX);

        if (!pwd.equals(confpwd))
            throw new GeneralException(Status.BAD_REQUEST_ARGUMENT_PWD_NOT_SAME);

        if (memberRepository.findByUserId(userId).isPresent())
            throw new GeneralException(Status.BAD_REQUEST_EXISTING_USERID);

        if (memberRepository.findByEmail(email).isPresent())
            throw new GeneralException(Status.BAD_REQUEST_EXISTING_EMAIL);
    }

    public Member findMember(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new GeneralException(Status.BAD_REQUEST_NON_EXISTENCE_MEMBER));
    }

    @Transactional
    public Member updateMember(UpdateRequest req) {
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new GeneralException(Status.BAD_REQUEST_NON_EXISTENCE_MEMBER));

        if (memberRepository.findByEmail(req.getEmail()).isPresent())
            throw new GeneralException(Status.BAD_REQUEST_EXISTING_EMAIL);

        member.update(
                req.getName() != null ? req.getName() : member.getName(),
                req.getEmail() != null ? req.getEmail() : member.getEmail(),
                req.getPassword() != null ? req.getPassword() : member.getPassword()
        );

        return member;
    }

    @Transactional
    public void deleteMember(DeleteRequest req) {
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new GeneralException(Status.BAD_REQUEST_NON_EXISTENCE_MEMBER));

        if (!member.getPassword().equals(req.getPassword()))
            throw new GeneralException(Status.BAD_REQUEST_ARGUMENT_PWD_NOT_SAME);

        memberRepository.deleteById(member.getId());
    }
}
