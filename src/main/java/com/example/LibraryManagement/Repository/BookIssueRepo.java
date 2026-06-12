package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Model.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIssueRepo extends JpaRepository<BookIssue, Long> {
}
