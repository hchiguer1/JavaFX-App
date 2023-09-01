package ma.enset.examjavafx.dao.entities;

import java.io.Serializable;

public class Category implements Serializable {
    private long id;
    private String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
