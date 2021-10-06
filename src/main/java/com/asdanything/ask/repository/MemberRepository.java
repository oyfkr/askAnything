package com.asdanything.ask.repository;

import com.asdanything.ask.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);
    Member findByName(String name);
}
