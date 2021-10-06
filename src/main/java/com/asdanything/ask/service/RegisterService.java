package com.asdanything.ask.service;

import com.asdanything.ask.Entity.Member;
import com.asdanything.ask.Entity.Register;
import com.asdanything.ask.dto.RegisterDto;
import com.asdanything.ask.repository.MemberRepository;
import com.asdanything.ask.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterRepository registerRepository;
    private final MemberRepository memberRepository;

    public void saveRegister(String memberName, RegisterDto registerDto){
        Member findMember = memberRepository.findByName(memberName);
        Register register = Register.createRegister(registerDto, findMember);
        duplicateCheck(register);
        registerRepository.save(register);
    }

    public void duplicateCheck(Register register){
        Register findRegister = registerRepository.findByMemberId(register.getMember().getId());
        System.out.println(findRegister);

        if(findRegister != null){
            throw new IllegalStateException("이미 등록하셨습니다.");
        }
    }
}
