package entity;

import lombok.Data;

@Data
public class Project {

    private long id;
    private String title;

    public Project() {
    }

    public Project(long id, String title) {
        this.id = id;
        this.title = title;
    }

}
