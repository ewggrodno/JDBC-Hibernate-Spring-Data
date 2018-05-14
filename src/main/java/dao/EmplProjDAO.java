package dao;

import entity.EmplProj;

import java.util.List;

public interface EmplProjDAO {

    //create
    void add(EmplProj emplProj);

    //read
    List<EmplProj> getAll();

    EmplProj getByEmployeeIdAndProjectId(long employeeId, long projectId);

    //update
    void update(EmplProj oldEmplProj, EmplProj newEmplProj);

    //delete
    void remove(EmplProj emplProj);
}
