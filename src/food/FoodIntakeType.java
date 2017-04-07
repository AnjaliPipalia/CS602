package food;

public enum  FoodIntakeType {
	DUMMY(""),
	BREAKFAST("Breakfast"),
	LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACKS("Snacks"),
    PARTYMEAL("PartyMeal"),
    MEAL("Meal"),
    OTHERS("Others"),
    ;

    private String mealNames;

    FoodIntakeType(String mealNames) {
        this.mealNames = mealNames;
    }

    public String mealName() {
        return mealNames;
    }
}