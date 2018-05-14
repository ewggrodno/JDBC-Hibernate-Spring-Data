package entity;

import lombok.Data;

@Data
public class EmplProj {

    private long employeeId;
    private long projectId;

    public EmplProj() {
    }

    public EmplProj(long employeeId, long projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

}
