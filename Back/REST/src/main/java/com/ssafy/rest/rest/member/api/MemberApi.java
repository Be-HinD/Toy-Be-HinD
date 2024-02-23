package com.ssafy.rest.rest.member.api;

import com.ssafy.rest.rest.member.dto.JoinDto;
import com.ssafy.rest.rest.member.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@RestController
@Slf4j
@RequestMapping("/member")
public class MemberApi {

    private final JoinService joinService;

    public MemberApi(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(JoinDto joinDto) {

        Boolean isValid = joinService.joinProcess(joinDto);
        if(isValid) {
            return new ResponseEntity<>("회원가입 완료되었습니다.", HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>("회원가입이 실패하였습니다.", HttpStatus.OK);
        }
    }

    @GetMapping("/main")
    public String main() {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main Controller : "+name + " : " + role;
    }

}
