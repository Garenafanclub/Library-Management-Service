package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    boolean existByMemberCode(Long memberCode);
}
