package com.country.codes.country.business;

import com.country.codes.country.model.IngredientsList;
import com.country.codes.country.provider.IngredientProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private Logger logger = LoggerFactory.getLogger(IngredientService.class);

    private IngredientProvider ingredientProvider;

    public IngredientService(IngredientProvider ingredientProvider) {
        this.ingredientProvider = ingredientProvider;
    }

    public IngredientsList getAllCocktailIngredients() {
        logger.info("Calling Ingredients Provider to get all ingredients");
        IngredientsList ingredientsList = ingredientProvider.getIngredientsForCocktail();
        logger.info("Received response from Ingredients provider ");
        return ingredientsList;
    }
}
