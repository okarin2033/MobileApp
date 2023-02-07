package ru.project.mobile.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;


@Data
public class OrderDto {
    private final Map<String, Integer> bookList;
    private final double fullPrice;
}