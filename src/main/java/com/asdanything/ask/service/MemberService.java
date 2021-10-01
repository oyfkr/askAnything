package com.asdanything.ask.service;

import com.asdanything.ask.Entity.Member;
import com.asdanything.ask.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){

        checkDuplication(member);
        return memberRepository.save(member);
    }

    private void checkDuplication(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
