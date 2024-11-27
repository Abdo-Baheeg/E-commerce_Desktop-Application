package source.Products;


public abstract class Category {
    private String name;
    private int index;
    public static int id =0;
    public Category(String name) {
        this.name = name;
        this.index = id+1;
        id++;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
}



