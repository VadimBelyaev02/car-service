package com.andersen.carservice.model.response;

import com.andersen.carservice.model.entity.Repairer;
import com.andersen.carservice.model.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderResponse {

    private UUID id;
    private BigDecimal price;
    private Instant openingDate;
    private Instant completionDate;
    private OrderStatus status;

    @ToString.Exclude
    @Builder.Default
    private List<Repairer> repairers = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", price=" + price +
//                ", openingDate=" + openingDate +
//                ", completionDate=" + completionDate +
//                ", status=" + status +
//                '}';
//    }
}
