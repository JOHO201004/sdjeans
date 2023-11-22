package com.sdjeans.sdjeans_app.C_app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdjeans.sdjeans_app.C_app.Entity.Member;

@Repository
public interface UserRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);
}