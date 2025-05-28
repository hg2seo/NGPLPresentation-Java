package com.ngplpresentation.ngpl_backend.repository;

import com.ngplpresentation.ngpl_backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String name);

    Optional<Member> findByEmail(String name);

}
