package com.tgt.repln.inbound.jsoncomprator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class AisleDTO {

    @JsonProperty("Aisle")
    private Long aisle;

    @JsonProperty("Floor")
    private String floor;
}
