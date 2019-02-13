package com.tgt.repln.inbound.jsoncomprator;

import com.tgt.repln.inbound.jsoncomprator.models.CompareResponse;
import com.tgt.repln.inbound.jsoncomprator.utils.Utilities;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class JsonComparator {


    public static void main(String[] args) throws IOException {

        String filePath = new File("./src/main/resources/StoreList.txt").getPath();

        Logger logger = Logger.getLogger("Custom_Block_Comparator_Log");

        LoggingToFile(logger);

        List<String> storesList = Files.lines(Paths.get(String.valueOf(filePath)))
                .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

        RestTemplate restTemplate = new RestTemplate();
        CompareResponse compareResponse = new CompareResponse();
        List<CompareResponse> compareResponsesList = new ArrayList<>();
        Utilities utils = new Utilities();

        for (String str :
                storesList) {

            String legacyUrl = "http://customblock-aisle-block-details.target.com:8019/custom_block_location_details/v1?receiving_location_id=%s";
            //  "http://t%sisp0001.target.com/ReplenishmentServices/api/BlockAisle/GetMasterBlockAisleDetails";
            String currentUrl = "http://customblock-aisle-block-details.target.com:8019/custom_block_location_details/v1?receiving_location_id=%s";

            legacyUrl = String.format(legacyUrl, str);
            logger.info("Legacy url: " + legacyUrl);

            currentUrl = String.format(currentUrl, str);
            logger.info("Current url: " + legacyUrl);

            ResponseEntity<String> legacyResponse = restTemplate.exchange(legacyUrl, HttpMethod.GET, null, String.class);
            logger.info("Legacy response: " + legacyResponse.getBody());
            ResponseEntity<String> currentResponse = restTemplate.exchange(currentUrl, HttpMethod.GET, null, String.class);
            logger.info("Current response: " + legacyResponse.getBody());

            createResponse(compareResponse, str, legacyResponse, currentResponse);

            compareResponsesList.add(compareResponse);
        }

        List<CompareResponse> unEqualList = utils.compareResponse(compareResponsesList);
        if (unEqualList.size() > 0) {
            logger.info("Custom Block unlike stores: " + unEqualList.toString());
        } else {
            logger.info("No Unlike Stores Found");
        }
        utils.writeToFile(unEqualList);

    }

    private static void LoggingToFile(Logger logger) throws IOException {
        FileHandler fh;
        fh = new FileHandler("CustomBlock_Comparator.log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

    private static void createResponse(CompareResponse compareResponse, String str, ResponseEntity<String> legacyResponse, ResponseEntity<String> newResponse) throws IOException {
        compareResponse.setStoreId(str);
        compareResponse.setLegacyResponse(legacyResponse.getBody());
       // compareResponse.setCurrentResponse(newResponse.getBody());
        String resp = new String(Files.readAllBytes(Paths.get("/Users/z067709/Desktop/CB_New.txt")), "UTF-8");
        compareResponse.setCurrentResponse(resp);
    }


}
