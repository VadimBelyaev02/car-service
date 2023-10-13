package com.andersen.carservice.entity;

import com.andersen.carservice.entity.enums.RepairerStatus;
import lombok.*;

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
}
