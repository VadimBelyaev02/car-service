package com.andersen.carservice.model.request;

import com.andersen.carservice.model.entity.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class OrderRequest {
    private BigDecimal price;
    private Instant completionDate;
    private OrderStatus status;

    @Builder.Default
    @ToString.Exclude
    private List<UUID> repairersIds = new ArrayList<>();
}
