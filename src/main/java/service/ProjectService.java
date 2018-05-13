package service;

import bl.Util;
import dao.ProjectDAO;
import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements ProjectDAO {

    private static final String ID = "ID";
    private static final String TITLE = "TITLE";

    @Override
    public void add(Project project) {
        String sql = String.format(
                "INSERT INTO PROJECT (%s, %s) VALUES(?, ?)",
                ID, TITLE);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getAll() {
        String sql = String.format(
                "SELECT %s, %s FROM PROJECT",
                ID, TITLE);

        List<Project> projectList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){

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
        return projectList;
    }

    @Override
    public Project getById(Long id) {
        String sql = String.format("SELECT %s, %s FROM PROJECT WHERE ID=?",
                ID, TITLE);

        Project project = null;
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            project = new Project();
            project.setId(resultSet.getLong(ID));
            project.setTitle(resultSet.getString(TITLE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void update(Project project) {
        String sqj = String.format("UPDATE PROJECT SET %s=? WHERE %s=?",
                TITLE, ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqj)){

            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setLong(2, project.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Project project) {
        String sql = String.format("DELETE FROM PROJECT WHERE %s=?", ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, project.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
