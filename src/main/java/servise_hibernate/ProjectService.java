package servise_hibernate;

import bl.HibernateSessionUtil;
import dao.ProjectDAO;
import entity.Project;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectService extends HibernateSessionUtil implements ProjectDAO {
    @Override
    public void add(Project project) {
        openTransactionSession();

        session.save(project);

        closeTransactionSession();
    }

    @Override
    public List<Project> getAll() {
        openTransactionSession();

        Query<Project> query = session.createQuery("from Project", Project.class);
        List<Project> projectList = query.list();

        closeTransactionSession();
        return projectList;
    }

    @Override
    public Project getById(long id) {
        openTransactionSession();

        Query<Project> query = session.createQuery("from Project P where P.id = :id", Project.class);
        query.setParameter("id", id);

        Project project = query.getSingleResult();

        closeTransactionSession();

        return project;
    }

    @Override
    public void update(Project project) {
        openTransactionSession();

        session.update(project);

        closeTransactionSession();
    }

    @Override
    public void remove(Project project) {
        openTransactionSession();

        session.delete(project);

        closeTransactionSession();
    }
}
