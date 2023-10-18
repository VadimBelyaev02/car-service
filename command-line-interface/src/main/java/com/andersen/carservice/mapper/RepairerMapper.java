package com.andersen.carservice.mapper;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.entity.enums.RepairerStatus;
import com.andersen.carservice.request.RepairerRequest;
import com.andersen.carservice.response.RepairerResponse;

import java.util.UUID;

public class RepairerMapper {

    public RepairerResponse toResponse(Repairer repairer) {
        final String name = repairer.getName();
        final RepairerStatus status = repairer.getStatus();
        final String email = repairer.getEmail();
        final UUID id = repairer.getId();


        return RepairerResponse.builder()
                .id(id)
                .email(email)
                .status(status)
                .name(name)
                .build();
        /*
            private UUID id;
    private String name;
    private RepairerStatus status;
    private String email;

    @ToString.Exclude
    private List<UUID> ordersIds;
         */
    }

    public Repairer toEntity(RepairerRequest repairerRequest) {
        final String name = repairerRequest.getName();
        final RepairerStatus status = repairerRequest.getStatus();
        final String email = repairerRequest.getEmail();


        return Repairer.builder()
                .status(status)
                .email(email)
                .name(name)
                .build();
    }
}
