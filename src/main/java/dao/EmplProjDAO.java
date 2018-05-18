package dao;

import entity.EmplProj;
import entity.Employee;
import entity.Project;

import java.util.List;

public interface EmplProjDAO {

    void add(EmplProj emplProj);

    void addAll(Project project);

    List<EmplProj> getAll();

    List<EmplProj> getAll(Employee employee);

    List<EmplProj> getAll(Project project);

    EmplProj getByEmployeeIdAndProjectId(long employeeId, long projectId);

    void update(EmplProj oldEmplProj, EmplProj newEmplProj);

    void remove(EmplProj emplProj);
}
