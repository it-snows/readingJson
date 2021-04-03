package com.example;

public class Main {
    public static void main(String[] args) {

        var dm = new DataManager();


     //method to load-read json file
        var recipes = dm.load();
        for (Recipe recipe :  recipes) {
            System.out.println(recipe);
        }



//        dm.loadRecipesIntoDb();

    }
}
