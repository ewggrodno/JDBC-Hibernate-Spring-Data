package service;

import bl.Util;
import dao.EmplProjDAO;
import entity.EmplProj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmplProjServise implements EmplProjDAO {

    private String EMPLOYEE_ID = "EMPLOYEE_ID";
    private String PROJECT_ID = "PROJECT_ID";

    @Override
    public void add(EmplProj emplProj) {
        String sql = String.format(
                "INSERT INTO EMPL_PROJ (%s, %s) VALUES(?, ?)",
                EMPLOYEE_ID, PROJECT_ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, emplProj.getEmployeeId());
            preparedStatement.setLong(2, emplProj.getProjectId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmplProj> getAll() {
        String sql = String.format(
                "SELECT %s, %s FROM EMPL_PROJ",
                EMPLOYEE_ID, PROJECT_ID);

        List<EmplProj> emplProjList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                EmplProj emplProj = new EmplProj();

                emplProj.setEmployeeId(resultSet.getLong(EMPLOYEE_ID));
                emplProj.setProjectId(resultSet.getLong(PROJECT_ID));

                emplProjList.add(emplProj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emplProjList;
    }

    @Override
    public EmplProj getByEmployeeIdAndProjectId(long employeeId, long projectId) {
        String sql = String.format(
                "SELECT %s, %s FROM EMPL_PROJ WHERE %s=? AND %s=?",
                EMPLOYEE_ID, PROJECT_ID, EMPLOYEE_ID, PROJECT_ID);

        EmplProj emplProj = null;
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, projectId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            emplProj = new EmplProj();
            emplProj.setEmployeeId(resultSet.getLong(EMPLOYEE_ID));
            emplProj.setProjectId(resultSet.getLong(PROJECT_ID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emplProj;
    }

    @Override
    public void update(EmplProj oldEmplProj, EmplProj newEmplProj) {
        String sql = String.format(
                "UPDATE EMPL_PROJ SET %s=?, %s=? WHERE %s=? AND %s=?",
                EMPLOYEE_ID, PROJECT_ID, EMPLOYEE_ID, PROJECT_ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1 , newEmplProj.getEmployeeId());
            preparedStatement.setLong(2 , newEmplProj.getProjectId());
            preparedStatement.setLong(3 , oldEmplProj.getEmployeeId());
            preparedStatement.setLong(4 , oldEmplProj.getProjectId());

//            preparedStatement.setLong(1, emplProj.getEmployeeId());
//            preparedStatement.setLong(1, emplProj.getProjectId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(EmplProj emplProj) {
        String sql = String.format(
                "DELETE FROM EMPL_PROJ WHERE %s=? AND %s=?",
                EMPLOYEE_ID, PROJECT_ID);

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, emplProj.getEmployeeId());
            preparedStatement.setLong(2, emplProj.getProjectId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
