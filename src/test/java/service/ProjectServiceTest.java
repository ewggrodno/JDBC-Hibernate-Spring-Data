package service;

import bl.Util;
import entity.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectServiceTest {

    private ProjectService projectService = new ProjectService();

    private Project project1 = null;
    private Project project2 = null;
    private Project project3 = null;

    @Before
    public void setUp() throws Exception {
        Util.clearAllTables();

        project1 = new Project(1, "Title 1");
        project2 = new Project(2, "Title 2");
        project3 = new Project(3, "Title 3");

        projectService.add(project1);
        projectService.add(project2);
        projectService.add(project3);
    }

    @After
    public void tearDown() throws Exception {
        Util.clearAllTables();
    }

    @Test
    public void add_PROJECT() {
        Util.clearAllTables();

        projectService.add(project1);
        projectService.add(project2);
        projectService.add(project3);

        List<Project> expected = new ArrayList<>();
        expected.add(project1);
        expected.add(project2);
        expected.add(project3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getAll_PROJECT() {
        List<Project> expected = new ArrayList<>();
        expected.add(project1);
        expected.add(project2);
        expected.add(project3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    public void getById_PROJECT() {
        Project actual1 = projectService.getById(1);
        Project actual2 = projectService.getById(2);
        Project actual3 = projectService.getById(3);

        assertEquals(project1, actual1);
        assertEquals(project2, actual2);
        assertEquals(project3, actual3);
    }

    @Test
    public void update_PROJECT() {
        project1 = new Project(1, "updateTitle 1");
        project2 = new Project(2, "updateTitle 2");
        project3 = new Project(3, "updateTitle 3");

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
        projectService.remove(project1);

        List<Project> expected = new ArrayList<>();
        expected.add(project2);
        expected.add(project3);

        List<Project> actual = projectService.getAll();

        assertEquals(expected, actual);
    }
}