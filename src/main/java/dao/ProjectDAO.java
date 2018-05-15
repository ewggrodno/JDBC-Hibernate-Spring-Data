package dao;

import entity.Project;

import java.util.List;

public interface ProjectDAO {

    void add(Project project);

    List<Project> getAll();

    Project getById(long id);

    void update(Project project);

    void remove(Project project);
}
