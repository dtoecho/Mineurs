package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import org.springframework.security.oauth2.core.user.OAuth2User;


public interface RegisterService {
    int reg(Member member);

    void reg(OAuth2User oAuth2User);
}
