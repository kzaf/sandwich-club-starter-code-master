package com.udacity.sandwichclub.utils;


import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject mName = sandwichJson.getJSONObject("name");
            JSONArray mIngredients = sandwichJson.getJSONArray("ingredients");

            String mainName = mName.getString("mainName");
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            String image = sandwichJson.getString("image");

            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray jsonArrayWithAlsoKnownAsValues = mName.getJSONArray("alsoKnownAs");

            if(jsonArrayWithAlsoKnownAsValues != null){
                for(int i=0; i < jsonArrayWithAlsoKnownAsValues.length(); i++){
                    String akaValue = jsonArrayWithAlsoKnownAsValues.getString(i);
                    alsoKnownAs.add(akaValue);
                }
            }

            List<String> ingredients = new ArrayList<>();

            if(mIngredients != null){
                for(int i=0; i < mIngredients.length(); i++){
                    String akaValue = mIngredients.getString(i);
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
