package com.asdanything.ask.Entity;

import com.asdanything.ask.dto.RegisterDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Register {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Member member;

    private String phoneNum;

    private String ability;

    private String address;

    public static Register createRegister(RegisterDto registerDto, Member member){
        Register register = new Register();
        register.setAbility(registerDto.getAbility());
        register.setMember(member);
        register.setPhoneNum(registerDto.getPhoneNum());
        register.setAddress(registerDto.getAddress());

        return register;
    }
}
