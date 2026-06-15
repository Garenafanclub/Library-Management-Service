package com.example.LibraryManagement.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberCode;
    private String name;
    private String email;
    private int phoneNumber;


    @OneToMany(mappedBy = "member",
           cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true,
           fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<BookIssue> bookIssue = new ArrayList<>();
}
