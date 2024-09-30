package com.newlecmineursprj.config.security;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.entity.Role;
import com.newlecmineursprj.repository.MemberRepository;
import com.newlecmineursprj.repository.RoleRepository;
import com.newlecmineursprj.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserDetailsService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository repository;
    private final RoleRepository roleRepository;
    private final RegisterService registerService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String userName = oAuth2User.getAttribute("name");
        Member member = repository.findByEmail(email);

        WebUserDetails userDetails;

        if (member == null) {

            registerService.reg(oAuth2User);

            userDetails = WebUserDetails.builder()
                    .attributes(oAuth2User.getAttributes())
                    .name(oAuth2User.getName())
                    .username(userName).build();
        } else {
            userDetails = userDetailsFromExistringMember(member, oAuth2User);
        }
        return userDetails;
    }

    private WebUserDetails userDetailsFromExistringMember(Member member, OAuth2User oAuth2User) {
        WebUserDetails userDetails = new WebUserDetails();
        userDetails.setAttributes(oAuth2User.getAttributes());
        userDetails.setName(oAuth2User.getName());
        userDetails.setUsername(member.getEmail());

        List<Role> roles = roleRepository.findAllByMemberId(member.getId());
        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        userDetails.setId(member.getId());
        userDetails.setEmail(member.getEmail());
        userDetails.setPassword(member.getPassword());
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
