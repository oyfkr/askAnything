package com.asdanything.ask.repository;

import com.asdanything.ask.Entity.Member;
import com.asdanything.ask.Entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByMemberId(Long id);
    Register findByMember(Member member);
    List<Register> findByAbilityContaining(String search);
}
