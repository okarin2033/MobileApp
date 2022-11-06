package ru.project.mobile.dto;

import lombok.Data;

@Data
public class Book {
    String bookName;
    String url;
    String imageUrl;
    String genre;
    String articul;
    String author;
    String description;
    int price;
}
