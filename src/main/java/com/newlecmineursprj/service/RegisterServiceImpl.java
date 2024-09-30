package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.entity.MemberRole;
import com.newlecmineursprj.repository.MemberRoleRepository;
import com.newlecmineursprj.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepository repository;
    private final MemberRoleRepository memberRoleRepository;


    @Override
    @Transactional
    public int reg(Member member) {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String passwordEncoding = passwordEncoder.encode(member.getPassword());
        member.setEncodedPassword(passwordEncoding);

        try{
            int savedRowCount = repository.save(member);
            MemberRole memberRole = MemberRole.builder()
                    .roleId(1L)
                    .memberId(member.getId())
                    .build();
            
            memberRoleRepository.save(memberRole);

            return savedRowCount;
        } catch (DuplicateKeyException e) {
            log.error("Error during registration: {}", e.getMessage());
            throw e;
        }
    }

    /*OAuth2객체는 비밀번호가 따로 없고, 오로지 이메일주소와 사용자닉네임,프로필정보등만 얻을 수 있음
    * 때문에 기존 로컬로그인과 별개로 오버로딩해서 별도로 만듬 */
    /**/
    @Override
    public void reg(OAuth2User oAuth2User) {

        repository.save(Member.builder()
                .name(oAuth2User.getAttribute("name"))
                .email(oAuth2User.getAttribute("email"))
                .build());

        String email = oAuth2User.getAttribute("email");
        Member member = repository.findById(email);

        MemberRole memberRole = MemberRole.builder()
                .roleId(1L)
                .memberId(member.getId())
                .build();

        memberRoleRepository.save(memberRole);
    }
}


