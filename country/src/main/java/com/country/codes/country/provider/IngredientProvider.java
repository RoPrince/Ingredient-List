package com.country.codes.country.provider;

import com.country.codes.country.config.ConfigurationProperties;
import com.country.codes.country.model.IngredientsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IngredientProvider {

    private Logger logger = LoggerFactory.getLogger(IngredientProvider.class);

    private ConfigurationProperties configurationProperties;

    private RestTemplate restTemplate;


    public IngredientProvider(ConfigurationProperties configurationProperties, RestTemplate restTemplate) {
        this.configurationProperties = configurationProperties;
        this.restTemplate = restTemplate;
    }

    public IngredientsList getIngredientsForCocktail() {

        logger.info("Received request to get Cocktail Ingredients list from RAPID API");

        String getIngredientsUrl = configurationProperties.getIngredientListUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "23039b6910msh78f455f6c33382ap14ba32jsn9a0fdf9d33a4");
        HttpEntity entity = new HttpEntity(headers);

        logger.info("Sending request to Rapid API with \n url:'{}'\n", getIngredientsUrl);
        ResponseEntity<IngredientsList> response = restTemplate.exchange(getIngredientsUrl, HttpMethod.GET, entity, IngredientsList.class);
        if (logger.isDebugEnabled()) {
            logger.debug("Received response from Rapid API for Cocktail Ingredients list:'{}'", response.getBody().toString());
        } else {
            logger.info("Received response from Rapid API for cocktail ingredients ");
        }
        return response.getBody();
    }
}
