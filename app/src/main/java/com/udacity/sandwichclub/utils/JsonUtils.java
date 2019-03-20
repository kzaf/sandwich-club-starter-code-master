package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String INGREDIENTS = "ingredients";
    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject mName = sandwichJson.optJSONObject(NAME);
            JSONArray mIngredients = sandwichJson.optJSONArray(INGREDIENTS);

            String mainName = mName.optString(MAIN_NAME);
            String placeOfOrigin = sandwichJson.optString(PLACE_OF_ORIGIN);
            String description = sandwichJson.optString(DESCRIPTION);
            String image = sandwichJson.optString(IMAGE);

            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray jsonArrayWithAlsoKnownAsValues = mName.optJSONArray("alsoKnownAs");

            if(jsonArrayWithAlsoKnownAsValues != null){
                for(int i=0; i < jsonArrayWithAlsoKnownAsValues.length(); i++){
                    String akaValue = jsonArrayWithAlsoKnownAsValues.optString(i);
                    alsoKnownAs.add(akaValue);
                }
            }

            List<String> ingredients = new ArrayList<>();

            if(mIngredients != null){
                for(int i=0; i < mIngredients.length(); i++){
                    String akaValue = mIngredients.optString(i);
                    ingredients.add(akaValue);
                }
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
