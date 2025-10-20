package Agona06;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class StudentRepository {

    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(student); // merge вместо persist. При persist ломалось, когда у student были persist course
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Student> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.find(Student.class, id);
            return Optional.ofNullable(student);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Student> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("FROM Agona06.Student", Student.class);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.find(Student.class, id);
            if (student != null) {
                session.remove(student);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Set<Course> getCoursesForStudent(Long studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.find(Student.class, studentId);
            if (student != null) {
                // Инициализируем ленивую коллекцию
                student.getCourses().size();
                return student.getCourses();
            }
            return new HashSet<Course>();
        }
    }
}
