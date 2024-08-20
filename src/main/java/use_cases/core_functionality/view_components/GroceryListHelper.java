package use_cases.core_functionality;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityInteractor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;


public class GroceryListHelper {

    static ShoppingList getAdjustedGroceryListForDisplay(ShoppingList originalList) {
        List<Ingredient> adjustedIngredients = new ArrayList<>();
        List<Ingredient> fridgeItems = LoggedUserData.getLoggedInUser().getFridge().getIngredients();

        for (Ingredient grocery : originalList.getListItems()) {
            float adjustedQuantity = grocery.getQuantity();
            for (Ingredient fridgeItem : fridgeItems) {
                if (grocery.getIngredientName().equalsIgnoreCase(fridgeItem.getIngredientName()) &&
                        grocery.getQuantityUnit().equalsIgnoreCase(fridgeItem.getQuantityUnit())) {
                    adjustedQuantity -= fridgeItem.getQuantity();
                }
            }
            if (adjustedQuantity > 0) {
                Ingredient adjustedIngredient = new Ingredient(grocery.getIngredientID(), grocery.getIngredientName(),
                        grocery.getQuantityUnit(), grocery.getCategory(), adjustedQuantity);
                adjustedIngredients.add(adjustedIngredient);
            }
        }

        // Consolidate like ingredients
        Map<String, Ingredient> consolidatedIngredients = new LinkedHashMap<>();
        for (Ingredient ingredient : adjustedIngredients) {
            String key = ingredient.getIngredientName().toLowerCase() + ingredient.getQuantityUnit().toLowerCase();
            if (consolidatedIngredients.containsKey(key)) {
                consolidatedIngredients.get(key).addIngredientQuantity(ingredient.getQuantity());
            } else {
                consolidatedIngredients.put(key, ingredient);
            }
        }

        ShoppingList adjustedShoppingList = new ShoppingList(originalList.getListOwner(), originalList.getShoppingListName());
        adjustedShoppingList.setListItems(new ArrayList<>(consolidatedIngredients.values()));
        adjustedShoppingList.setEstimatedTotalCost(originalList.getEstimatedTotalCost());
        adjustedShoppingList.setRecipes(originalList.getRecipes());
        return adjustedShoppingList;
    }
}

