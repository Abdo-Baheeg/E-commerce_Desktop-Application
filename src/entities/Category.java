package src.entities;


public abstract class Category {
    private String name;
    private final int id;
    private static int idCounter=0;

    public Category(String name) {
        this.name = name;
        id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



