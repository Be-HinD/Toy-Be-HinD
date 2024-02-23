package com.ssafy.rest.rest.member.repository;

import com.ssafy.rest.rest.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    Boolean existsByUserName(String userName);
    MemberEntity findByUserName(String userName);
}
