package com.andersen.carservice.request;

import com.andersen.carservice.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class OrderRequest {

    private BigDecimal price;
    private Instant completionDate;
    private OrderStatus status;
    private List<UUID> repairersIds;
}
