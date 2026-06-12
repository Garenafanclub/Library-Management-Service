package com.example.LibraryManagement.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private Long isbn;

    @NotBlank(message = "title is required")
    private String title;

    private String author;
    private String category;

    @NotBlank(message = "How many available copies are there is required.")
    private Long availableCopies;
}
