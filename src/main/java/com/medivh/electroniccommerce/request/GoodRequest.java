package com.medivh.electroniccommerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodRequest {
    private String name;
    private String author;
    private float price;
    private String introduce;
    private String category;
    private String img;
    private String detailImg;
}
