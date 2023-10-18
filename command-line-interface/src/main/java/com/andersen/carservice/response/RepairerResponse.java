package com.andersen.carservice.response;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.RepairerStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class RepairerResponse {
    private UUID id;
    private String name;
    private RepairerStatus status;
    private String email;
    private List<Order> orders;
}
