package dao;

import entity.EmplProj;

import java.util.List;

public interface EmplProjDAO {

    void add(EmplProj emplProj);

    List<EmplProj> getAll();

    EmplProj getByEmployeeIdAndProjectId(long employeeId, long projectId);

    void update(EmplProj oldEmplProj, EmplProj newEmplProj);

    void remove(EmplProj emplProj);
}
