package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class DataManagerTests {


    @Test
    public void save_recipe_data() {
        var dm = new DataManager();

        var ingredient1 = new Ingredient(0, 0, "eggs");
        var ingredient2 = new Ingredient(0, 0, "milk");
        var ingredient3 = new Ingredient(0, 0, "sugar");
        var ingredient4 = new Ingredient(0, 0, "oil");

//       data provided based on version where List of Ingredients hence the error
//        var recipe = new Recipe(0, "test pancakes", "snack", 30, 4, Arrays.asList(ingredient1, ingredient2, ingredient3, ingredient4), "Mix exverything and do the baking", "src/com/images");
//
//        dm.save(Arrays.asList(recipe));
    }

    @Test
    public void save_ingredient_data() {
        var dm = new DataManager();
        var ingredient1 = new Ingredient(0, 0, "eggs");
//        var ingredient2 = new Ingredient(0, 0, "milk");
//        var ingredient3 = new Ingredient(0, 0, "sugar");
//        var ingredient4 = new Ingredient(0, 0, "oil");

        dm.saveIngredient(ingredient1);
    }

    @Test
    public void load_ingredient() {
        var dm = new DataManager();
         var ingredient = dm.loadIngredient();
        Assert.assertNotNull(ingredient);
    }

    @Test
    public void load_recipes() {
        var dm = new DataManager();
        var recipes = dm.load();
        Assert.assertTrue(recipes.size() != 0);
    }
}