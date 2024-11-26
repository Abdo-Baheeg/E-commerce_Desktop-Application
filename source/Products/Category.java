package source.Products;


public abstract class Category {
    public static void DisplayCategories() {
        // Loop through and display all categories
        for (Categories categories : Categories.values()) {
            System.out.println(categories.name());
        }
    }

    // Enum for product categories
    public enum Categories {
        ELECTRONICS_AND_GADGETS,
        FASHION_AND_APPAREL,
        HOME_AND_LIVING,
        BEAUTY_AND_PERSONAL_CARE,
        HEALTH_AND_FITNESS,
        FOOD_AND_BEVERAGES,
        BOOKS_AND_STATIONERY,
        KIDS_AND_BABY_PRODUCTS,
        SPORTS_AND_OUTDOORS,
        AUTOMOTIVE,
        PET_SUPPLIES,
        TECHNOLOGY_SERVICES,
        HANDMADE_AND_CUSTOM_PRODUCTS
    }
}



