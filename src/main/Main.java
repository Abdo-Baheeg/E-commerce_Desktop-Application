package src.main;
import src.database.Database;

public class Main {

    public static void main(String[] args) {
        Database.initDummyData();
        System.out.println("Welcome to our Project");
    }
}
