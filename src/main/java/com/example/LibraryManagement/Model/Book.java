package com.example.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long isbn;

    private String title;
    private String author;
    private String category;
    private Long availableCopies;

    @JsonIgnore
    @OneToMany(mappedBy = "book",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<BookIssue> bookIssue = new ArrayList<>();
}
