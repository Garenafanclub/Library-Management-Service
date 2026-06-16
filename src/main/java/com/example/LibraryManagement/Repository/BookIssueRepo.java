package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Model.BookIssue;
import com.example.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookIssueRepo extends JpaRepository<BookIssue, Long> {
     int countByMemberIdAndStatus(Long memberId, String status);

     @Query("Select bi from BookIssue bi LEFT JOIN FETCH bi.book LEFT JOIN FETCH bi.member where bi.status = 'ISSUED'")
     List<BookIssue> findActiveIssue();


     @Query("Select bi from BookIssue bi LEFT JOIN FETCH bi.book LEFT JOIN FETCH bi.member " +
             "where bi.member.id = :memberId AND bi.status = 'ISSUED'")
     List<BookIssue> findMemberIssuedTheBooks(@Param("memberId") Long memberId);
}
