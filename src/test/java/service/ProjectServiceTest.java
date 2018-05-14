package service;

import bl.Util;
import entity.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static testData.ProjectData.*;

public class ProjectServiceTest {

    private ProjectService projectService = new ProjectService();

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        projectService.add(PROJECT_1);
        projectService.add(PROJECT_2);
        projectService.add(PROJECT_3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_PROJECT() {
        Util.clearAllTables();

        projectService.add(PROJECT_1);
        projectService.add(PROJECT_2);
        projectService.add(PROJECT_3);

        List<Project> expected = new ArrayList<>();
        expected.add(PROJECT_1);
        expected.add(PROJECT_2);
        expected.add(PROJECT_3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_PROJECT() {
        List<Project> expected = new ArrayList<>();
        expected.add(PROJECT_1);
        expected.add(PROJECT_2);
        expected.add(PROJECT_3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getById_PROJECT() {
        Project actual1 = projectService.getById(1);
        Project actual2 = projectService.getById(2);
        Project actual3 = projectService.getById(3);

        assertEquals(PROJECT_1, actual1);
        assertEquals(PROJECT_2, actual2);
        assertEquals(PROJECT_3, actual3);
    }

    @Test
    public void update_PROJECT() {
        Project project1 = new Project(1, "updateTitle 1");
        Project project2 = new Project(2, "updateTitle 2");
        Project project3 = new Project(3, "updateTitle 3");

        projectService.update(project1);
        projectService.update(project2);
        projectService.update(project3);

        Project actual1 = projectService.getById(1);
        Project actual2 = projectService.getById(2);
        Project actual3 = projectService.getById(3);

        assertEquals(project1, actual1);
        assertEquals(project2, actual2);
        assertEquals(project3, actual3);
    }

    @Test
    public void remove_PROJECT() {
        projectService.remove(PROJECT_1);

        List<Project> expected = new ArrayList<>();
        expected.add(PROJECT_2);
        expected.add(PROJECT_3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }
}