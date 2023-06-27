package com.medivh.electroniccommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class shoppingCart {
    private int user_id;
    private int goods_id;
    private int count=1;
}
