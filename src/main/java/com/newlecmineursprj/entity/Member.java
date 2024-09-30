package com.newlecmineursprj.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private long id;
    @NotBlank
    @Size(min = 2, max = 4)
    @Pattern(regexp = "^[가-힣]*$")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,16}$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$")
    private String password;

    @NotBlank
    @Pattern(regexp = "^010\\d{8}$")
    private String phoneNumber;

    private Timestamp regDate;

    private String paymentPassword;

    @NotBlank
    @Email
    private String email;

    private boolean smsReception;
    private boolean emailReception;
    @Builder.Default
    private boolean enabled = true;

    private int point;

    public void setEncodedPassword(String password) {
        this.password = password;
    }
}
