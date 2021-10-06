package com.asdanything.ask.Entity;

import com.asdanything.ask.constant.Role;
import com.asdanything.ask.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING) // 데이터베이스에 저장할 때 enum을 USER 또는 ADMIN으로 저장한다.
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        String enPassword = passwordEncoder.encode(memberFormDto.getPassword());
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPassword(enPassword);
        member.setRole(Role.ADMIN);

        return member;
    }
}
