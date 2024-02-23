package com.ssafy.rest.rest.member.service;

import com.ssafy.rest.rest.member.dto.JoinDto;
import com.ssafy.rest.rest.member.entity.MemberEntity;
import com.ssafy.rest.rest.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean joinProcess(JoinDto joinDto) {

        String userName = joinDto.getUserName();
        String password = joinDto.getPassword();

        Boolean isExist = memberRepository.existsByUserName(userName);

        if(isExist) {

            return false;
        }

        MemberEntity data = new MemberEntity();

        data.setUserName(userName);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        memberRepository.save(data);

        return true;

    }
}
