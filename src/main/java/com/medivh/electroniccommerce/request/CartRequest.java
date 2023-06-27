package com.medivh.electroniccommerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private int userId;
    private int goodId;
    private int cartId;
    private int orderId;
}
