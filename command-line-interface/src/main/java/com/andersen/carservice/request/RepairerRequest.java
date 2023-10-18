package com.andersen.carservice.request;

import com.andersen.carservice.entity.enums.RepairerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class RepairerRequest {
    private String name;
    private RepairerStatus status;
    private String email;

    @ToString.Exclude
    @Builder.Default
    private List<UUID> ordersIds = new ArrayList<>();
}
