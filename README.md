# BitByteBistro
CSC207 group project repo


## ⚠️ HOW TO RUN THE PROGRAM FOR PHASE1


### Setup
1. Set up run configuration for main method : app.Main
2. Set environmental variables for the API keys : EDAMAM_API_ID=e32253d8;EDAMAM_API_KEY=829e78845ecaf34f4dab7abba727c568;NUTRITION_API_KEY=eb3e4233f7d4764ab894c00cdd0bf35b;NUTRITION_API_ID=97b256c0


### Use Case 1: Sign up
1. Run the Main.java, the default view is the login view where there's a text button that takes you to the login view.
2. Enter your userID, email and password and signup, you will be taken back to the login screen.

### Use Case 2: Login
1. This is the default view, login with your email and password once you've signed up.

### Use Case 3: Recipe Search by Recipe Name
1. Run the Main.java and login.
2. Type in the name of the recipe that you want to search at the top-left text field, and press enter / search button.
3. The result will show up in the panel below in couple seconds.


### Use Case 4: Advanced Recipe Search
1. Click the Advanced search button, next to the input text field.
2. Advanced search configuration window will pop up.
3. Configure the search and click search button. (click cancel to exit)
4. The advanced search result will show up in the same panel as where the result of simple recipe search by name shows up.


### Use Case 5: View Nutrition Information for each recipe (which will be used in the Nutrition Stats use case in Phase 2)
1. the nutrition information will pop up as print statements when you make a successful search query. in the future this will update the search recipe view directly.

### Use Case 6: Collapse saved Recipes to one or multiple Shopping Lists
1. Currently, on the Search page, there is the Convert Recipe to Grocery List button. Clicking in switches the user to a different view.
2. In the future, after user has successfully logged in and submitted their search queries, there will be a save recipe button next to each recipe they see
3. They can then save as many recipes as they like. In the future, when they click save, they will have a choice of which shopping list if any to save this recipe to.
4. Then, when they click the convert recipes to shopping list, it switches to another view.
5. The new view will contain the shopping lists they have if any. They can freely remove or add more recipes from their saved lists to their shopping lists.   

## PLAN
CURRENT PLAN:
our current plan is to do a recipe/grocery shopping interface. We'll use an API to search through recipes, which someone can add to their grocery cart (rather than all the individual ingredients), then our program will combine all the like-ingredients so there's only one amount (so rather than 2 cups of milk, 5 cups of milk, 1 cup of milk, people will know they need eight cups - and maybe we'll convert that to 2 litres or something). We also want to be able to scale the number of servings of each of the recipes we add. There will also be an option to add what's already in your fridge, so you don't buy ingredients you already have enough of.

An additional idea we had was to include an option for nutrition facts for the recipes planned, and maybe have something where people can see some figures related to that - avg protein per meal, avg cals per day, we'd use another API if we do this

Additionally, one other possible idea was to make this a bit of a social app, so scrolling through people's recipes, savings theirs, adding your own, maybe sharing what you've been cooking lately (though i think data for nutritional info should be left private in my opinion)

Finally, another idea was to use some API for the prices of ingredients in your area (taken from somewhere, idk where, maybe an API, unsure), to have an estimated idea of how much your groceries will cost.

An extra idea I just thought of: but it could be nice to have some kind of calendar feature where you can actually plan where the meals are going (that way you know you have enough for a week, for example). This would also feed into the nutrition info section, or just stats in general, since we could should "your favourite breakfast last month" or "avg servings of vegetables at lunchtime" or something like that.

TIMELINE FEATURES:
1) recipe search - Seonghyun
2) recipe to grocery list (view grocery list) - Fiona
3) fridge inventory (changes grocery list) - Fiona
4) adding recipes 
5) calendar/meal planner
6) recipe search by ingredient/any other "searchables" - Seonghyun
WE'LL SEE/PROBABLY NOT:
7) nutrition/personalized stats - Paula
9) budget + money saved
10) eco friendly OR for you + social


NOTE: each directory now has a file called testing.java which is not used for our project - it's just here as a placeholder so i can push the directories to maintain the structure.
