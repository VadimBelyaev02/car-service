package com.andersen.carservice.entity;

import com.andersen.carservice.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Order {
    private UUID id;
    private BigDecimal price;
    private Instant openingDate;
    private Instant completionDate;
    private OrderStatus status;
}
