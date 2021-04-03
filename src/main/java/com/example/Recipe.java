package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
    private int recipeId;
    private String title;
    private String course;
    private int totalTime;
    private int numberOfServings;
    // works with String List
    private String [] ingredients;
//    private List<Ingredient> ingredients = new ArrayList<>();
    private String instructions;
    private String pictureLink;
}