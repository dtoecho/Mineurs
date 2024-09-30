package com.newlecmineursprj.config.security;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.entity.Role;
import com.newlecmineursprj.repository.MemberRepository;
import com.newlecmineursprj.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebUserDetailsService implements UserDetailsService {

    private final MemberRepository repository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = repository.findByUsername(username)
                .orElseThrow( () -> new BadCredentialsException("등록된 사용자가 아닙니다."));

        List<Role> roles = roleRepository.findAllByMemberId(member.getId());

        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return WebUserDetails.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .paymentPassword(member.getPaymentPassword())
                .regDate(member.getRegDate())
                .authorities(authorities)
                .build();

    }
}
