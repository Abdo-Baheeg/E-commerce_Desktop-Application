package source.Products;
import source.dataBase.DataBase;

import static source.dataBase.DataBase.Categories;


public  class Category {
   private String name;
   private int id;
   private static int index=0;

   public Category( String name) {
       this.name = name;
       this.id = index+1;
       index++;
   }
    public void createCategory( String name) {
       Categories.add(new Category(name));
    }
    public void deleteCategory(int index) {
        Categories.remove(index);
    }
    public void deleteCategory(){
       Categories.removeLast();
    }



    public void setName(String name) {
        this.name = name;
    }


    //    // Enum for product categories
//    public enum Categories {
//        ELECTRONICS_AND_GADGETS,
//        FASHION_AND_APPAREL,
//        HOME_AND_LIVING,
//        BEAUTY_AND_PERSONAL_CARE,
//        HEALTH_AND_FITNESS,
//        FOOD_AND_BEVERAGES,
//        BOOKS_AND_STATIONERY,
//        KIDS_AND_BABY_PRODUCTS,
//        SPORTS_AND_OUTDOORS,
//        AUTOMOTIVE,
//        PET_SUPPLIES,
//        TECHNOLOGY_SERVICES,
//        HANDMADE_AND_CUSTOM_PRODUCTS
//    }
}



