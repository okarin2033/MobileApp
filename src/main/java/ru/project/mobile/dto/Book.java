package ru.project.mobile.dto;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    String bookName;
    String url;
    List<String> imageUrl;
    String genre;
    String articul;
    String author;
    String description;
    double price;
}
