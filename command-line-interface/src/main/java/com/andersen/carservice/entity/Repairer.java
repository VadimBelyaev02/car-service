package com.andersen.carservice.entity;

import com.andersen.carservice.entity.enums.RepairerStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Builder.Default;


@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Repairer {
    private UUID id;
    private String name;
    private RepairerStatus status;
    private String email;

    @ToString.Exclude
    @Default
    private List<UUID> ordersIds = new ArrayList<>();

    public void deleteOrder(UUID orderId) {
        ordersIds.remove(orderId);
    }

    public void addOrder(UUID orderId) {
        ordersIds.add(orderId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("Repairer(")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", status=").append(status)
                .append(", email='").append(email).append('\'');
//        if (Objects.nonNull(ordersIds) && !ordersIds.isEmpty()) {
//            builder.append("Order's ids: ");
//            for (int i = 0; i < ordersIds.size(); i++) {
//                builder.append(i + 1).append(") ").append(ordersIds);
//            }
//        }
        builder.append(')');
        return builder.toString();
    }
}
