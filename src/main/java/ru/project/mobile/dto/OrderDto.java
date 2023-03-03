package ru.project.mobile.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;


@Data
public class OrderDto {
    private Map<String, Integer> bookList;
    private double fullPrice;
    private String date;
}