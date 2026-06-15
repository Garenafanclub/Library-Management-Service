package com.example.LibraryManagement.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    @NotNull(message = "ISBN is required")
    private Long isbn;

    @NotNull(message = "title is required")
    private String title;

    private String author;
    private String category;

    @NotNull(message = "Available copies are required")
    @Min(value = 1, message = "Available copies must be at least 1")
    private Long availableCopies;
}
