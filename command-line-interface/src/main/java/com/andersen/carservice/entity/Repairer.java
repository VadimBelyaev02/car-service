package com.andersen.carservice.entity;

import com.andersen.carservice.entity.enums.RepairerStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Repairer {
    private UUID id;
    private String name;
    private RepairerStatus status;
    private String email;

    @EqualsAndHashCode.Exclude
    private List<UUID> ordersIds;

    public void deleteOrder(UUID orderId) {
        ordersIds.remove(orderId);
    }

    public void addOrder(UUID orderId) {
        ordersIds.add(orderId);
    }
}
