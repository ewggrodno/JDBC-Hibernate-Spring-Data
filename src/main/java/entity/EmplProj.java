package entity;

import java.util.Objects;

public class EmplProj {

    private long employeeId;
    private long projectId;

    public EmplProj() {
    }

    public EmplProj(long employeeId, long projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmplProj emplProj = (EmplProj) o;
        return Objects.equals(employeeId, emplProj.employeeId) &&
                Objects.equals(projectId, emplProj.projectId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, projectId);
    }

    @Override
    public String toString() {
        return "EmplProj{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                '}';
    }
}
