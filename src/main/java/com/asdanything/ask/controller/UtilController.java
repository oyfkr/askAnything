package com.asdanything.ask.controller;


import com.asdanything.ask.Entity.Member;
import com.asdanything.ask.Entity.Register;
import com.asdanything.ask.dto.RegisterDto;
import com.asdanything.ask.repository.MemberRepository;
import com.asdanything.ask.repository.RegisterRepository;
import com.asdanything.ask.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UtilController {

    private final RegisterService registerService;

    private final MemberRepository memberRepository;
    private final RegisterRepository registerRepository;

    @GetMapping("/util/find")
    public String find(){
        return "util/find";
    }

    @GetMapping("/util/register")
    public String registerForm(Model model){
        model.addAttribute("registerDto",new RegisterDto());
        return "util/register";
    }

    @PostMapping("/util/register")
    public String register(@Valid RegisterDto registerDto, BindingResult bindingResult, Authentication auth, Model model){

        if(bindingResult.hasErrors()){
            return "util/register";
        }

        try{
            registerService.saveRegister(auth.getName(),registerDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "util/register";
        }

        return "redirect:/";
    }
}
