package com.tgt.repln.inbound.jsoncomprator.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.repln.inbound.jsoncomprator.models.CompareResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

    public boolean checkDifference(CompareResponse response) {
        Boolean value = true;
        try {
            value = isEqual(response.getLegacyResponse(), response.getCurrentResponse()) ? false : true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;

    }

    public List<CompareResponse> compareResponse(List<CompareResponse> compareResponsesList) {
        return compareResponsesList.stream()
                .filter(s -> checkDifference(s))
                .collect(Collectors.toList());

    }

    private boolean isEqual(String legacyResponse, String newResponse) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode tree1 = mapper.readTree(legacyResponse);
        final JsonNode tree2 = mapper.readTree(newResponse);
        return tree1.equals(tree2);
    }

    public void writeToFile(List<CompareResponse> unEqualList) throws IOException {
        FileWriter writer = new FileWriter("CustomBlock_ResponseIssues.txt");
        for (CompareResponse unequal : unEqualList) {
            writer.write("\n");
            writer.write(unequal.getStoreId());
        }
        writer.close();
    }
}
