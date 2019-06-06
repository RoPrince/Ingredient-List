package com.country.codes.country.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IngredientsList implements Serializable {

    private List<Drinks> drinks;


}
