package com.asdanything.ask.controller;

import com.asdanything.ask.Entity.Member;
import com.asdanything.ask.dto.MemberFormDto;
import com.asdanything.ask.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(Authentication auth, Model model){
        //String name = auth.getName();
        //model.addAttribute("name",name);
        return "home";
    }

    @GetMapping("/members/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/members/new")
    public String memberNew(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);

        } catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String memberLoginForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberLoginForm";
    }

    @GetMapping("/members/login/error")
    public String LoginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호 확인해주세요");
        return "/member/memberLoginForm";
    }
}
