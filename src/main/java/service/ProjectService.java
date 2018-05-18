package service;

import bl.Util;
import dao.ProjectDAO;
import entity.EmplProj;
import entity.Employee;
import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements ProjectDAO {

    private static Connection connection = Util.getConnection();

    private static final String ID = "id";
    private static final String TITLE = "title";

    @Override
    public void add(Project project) {
        String sql = String.format(
                "INSERT INTO project (%s, %s) VALUES(?, ?)",
                ID, TITLE);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (project.getEmployees().size() > 0) {
            EmplProjServise emplProjServise = new EmplProjServise();
            emplProjServise.addAll(project);
        }
    }

    @Override
    public List<Project> getAll() {
        String sql = String.format(
                "SELECT %s, %s FROM project",
                ID, TITLE);

        List<Project> projectList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Project project = new Project();

                project.setId(resultSet.getLong(ID));
                project.setTitle(resultSet.getString(TITLE));

                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Project project : projectList) {

            EmplProjServise emplProjServise = new EmplProjServise();
            List<EmplProj> emplProjList = emplProjServise.getAll(project);

            for (EmplProj emplProj : emplProjList) {
                EmployeeService employeeService = new EmployeeService();
                Employee employee = employeeService.getById(emplProj.getEmployeeId());
                project.getEmployees().add(employee);
            }
        }

        return projectList;
    }

    @Override
    public Project getById(long id) {
        String sql = String.format("SELECT %s, %s FROM project WHERE ID=?",
                ID, TITLE);

        Project project = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            project = new Project();
            project.setId(resultSet.getLong(ID));
            project.setTitle(resultSet.getString(TITLE));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EmplProjServise emplProjServise = new EmplProjServise();
        List<EmplProj> emplProjList = emplProjServise.getAll(project);

        for (EmplProj emplProj : emplProjList) {
            EmployeeService employeeService = new EmployeeService();
            Employee employee = employeeService.getById(emplProj.getEmployeeId());
            project.getEmployees().add(employee);
        }

        return project;
    }

    @Override
    public void update(Project project) {
        String sqj = String.format("UPDATE project SET %s=? WHERE %s=?",
                TITLE, ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqj)) {

            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setLong(2, project.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Project project) {
        String sql = String.format("DELETE FROM project WHERE %s=?", ID);

        EmplProjServise emplProjServise = new EmplProjServise();
        List<EmplProj> emplProjList = emplProjServise.getAll(project);
        for (EmplProj emplProj : emplProjList) {
            emplProjServise.remove(emplProj);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, project.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
