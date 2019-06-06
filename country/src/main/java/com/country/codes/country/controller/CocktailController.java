package com.country.codes.country.controller;

import com.country.codes.country.business.IngredientService;
import com.country.codes.country.model.IngredientsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getAllCocktailIngredients")
public class CocktailController {

    private Logger logger = LoggerFactory.getLogger(CocktailController.class);

    private IngredientService ingredientService;

    public CocktailController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCountryCodes() {

        logger.info("Received Request to get all Cocktail Ingredients list");

        IngredientsList ingredientsList = ingredientService.getAllCocktailIngredients();

        if (logger.isDebugEnabled()) {
            logger.debug("Received response for getting Cocktail Ingredients list");
        } else {
            logger.info("Received response for getting Cocktail Ingredients list");
        }

        return new ResponseEntity<>(ingredientsList, HttpStatus.OK);
    }


}
