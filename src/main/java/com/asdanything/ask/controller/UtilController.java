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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UtilController {

    private final RegisterService registerService;

    private final MemberRepository memberRepository;
    private final RegisterRepository registerRepository;

    @GetMapping("/util/find")
    public String findForm(Model model){
        List<Register> findAll = registerRepository.findAll();
        model.addAttribute("findRegister",findAll);
        String search="";
        model.addAttribute("search", search);
        return "util/find";
    }

    @PostMapping("/util/find")
    public String find(@RequestParam String search, Model model){
        System.out.println(search);
        List<Register> findSearch = registerRepository.findByAbilityContaining(search);
        model.addAttribute("findSearch", findSearch);
        return "util/search";
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
