package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataManager {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/recipes?serverTimezone=UTC";

    private static final String FILE_PATH_TEST = "src/main/java/com/example/test.json";
    private static final String FILE_PATH_INGREDIENT = "src/main/java/com/example/testingredient.json";
    private static final String FILE_PATH_RECIPES = "src/main/java/com/example/recipes.json";

    //method to read json file
    public List<Recipe> load() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH_RECIPES);
            var data = fis.readAllBytes();
            var json = new String(data);

            Gson gson = new Gson();

            Type collectionType = new TypeToken<List<Recipe>>() {
            }.getType();

            return gson.fromJson(json, collectionType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // method to upload recipe json file to mysql DB
    public void loadRecipesIntoDb() {
        Connection con = null;
        try {
            con = getConnection();

            var recipes = load();
            String sqlStatement = "INSERT INTO recipes (recipe_title, course, total_time, number_of_servings," +
                    "ingredients, instructions, picture_link) values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sqlStatement);

            Path path = Paths.get(FILE_PATH_RECIPES);

            var lines = Files.readAllLines(path);

            for (Recipe recipe : recipes) {
                statement.setString(1, recipe.getTitle());
                statement.setString(2, recipe.getCourse());
                statement.setInt(3, recipe.getTotalTime());
                statement.setInt(4, recipe.getNumberOfServings());
                statement.setString(5, Arrays.toString(recipe.getIngredients()));
                statement.setString(6, recipe.getInstructions());
                statement.setString(7, recipe.getPictureLink());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //this cannot be used for list of objects because a specific class indicated
    //1. could create wrapper object with one property list
    public Ingredient loadIngredient() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH_INGREDIENT);
            var data = fis.readAllBytes();
            var json = new String(data);

            Gson gson = new Gson();
            return gson.fromJson(json, Ingredient.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //to create a json record
    public void save(List<Recipe> recipes) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var json = gson.toJson(recipes);
        try {
            var writer = new BufferedWriter(new FileWriter(FILE_PATH_TEST));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //to create a json record for ingredient
    public void saveIngredient(Ingredient ingredient) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var json = gson.toJson(ingredient);
        try {
            var writer = new BufferedWriter(new FileWriter(FILE_PATH_INGREDIENT));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, "test", "test123");
    }
}
