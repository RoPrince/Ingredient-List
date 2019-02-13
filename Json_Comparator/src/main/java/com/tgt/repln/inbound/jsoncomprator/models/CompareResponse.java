package com.tgt.repln.inbound.jsoncomprator.models;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class CompareResponse {
    private String storeId;

    private String legacyResponse;

    private String currentResponse;

    @Override
    public String toString() {
        return "CompareResponse{" + "\n" +
                "-------Json Responses does not match for:" + storeId + "\n" +
                ", legacyResponse='" + legacyResponse + '\'' + "\n" +
                ", currentResponse='" + currentResponse + '\'' + "\n" +
                '}';
    }
}
