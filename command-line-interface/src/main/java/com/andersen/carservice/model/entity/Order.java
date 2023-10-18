package com.andersen.carservice.model.entity;

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
@NoArgsConstructor
public class Order {
    private UUID id;
    private BigDecimal price;
    private Instant openingDate;
    private Instant completionDate;
    private OrderStatus status;

    @ToString.Exclude
    @Builder.Default
    private List<UUID> repairersIds = new ArrayList<>();

    public void addRepairer(UUID repairerId) {
        repairersIds.add(repairerId);
    }

    public void deleteRepairer(UUID repairerId) {
        repairersIds.remove(repairerId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", openingDate=" + openingDate +
                ", completionDate=" + completionDate +
                ", status=" + status +
                '}';
    }
}
