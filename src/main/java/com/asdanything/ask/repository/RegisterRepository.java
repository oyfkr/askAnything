package com.asdanything.ask.repository;

import com.asdanything.ask.Entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByMemberId(Long id);
}
