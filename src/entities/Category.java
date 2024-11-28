package src.entities;


public abstract class Category {
 private String name;
 private  int id;
 private static int index=0;
    protected Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.id = index+1;
        index++;
    }

}



