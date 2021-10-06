package com.asdanything.ask.dto;

import com.asdanything.ask.Entity.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {

    @NotBlank(message = "빈 칸이면 안됩니다.")
    private String phoneNum;
    @NotBlank(message = "빈 칸이면 안됩니다.")
    private String ability;
    @NotBlank(message = "빈 칸이면 안됩니다.")
    private String address;
}
