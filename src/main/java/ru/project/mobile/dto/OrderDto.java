package ru.project.mobile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Map<String, Integer> bookList;
    private double fullPrice;
    private String date;
}