package com.LibraryManagement.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private Boolean isAvailable;
    private Double quantity;
    private String isbn;
}
