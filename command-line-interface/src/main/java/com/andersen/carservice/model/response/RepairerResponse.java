package com.andersen.carservice.model.response;

import com.andersen.carservice.model.entity.Order;
import com.andersen.carservice.model.entity.enums.RepairerStatus;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class RepairerResponse {
    private UUID id;
    private String name;
    private RepairerStatus status;
    private String email;

    @Builder.Default
    private List<Order> orders = new ArrayList<>();

}
