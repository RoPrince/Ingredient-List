package com.tgt.repln.inbound.jsoncomprator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BlockAisleDTO {

    @JsonProperty("Block")
    private String block;

    @JsonProperty("AisleDTOList")
    private List<AisleDTO> aisleDTOList;
}