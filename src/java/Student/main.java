/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Pongsiri
 */
public class main {

    public static void main(String[]args){
        Student s = new Student();
        persist(s);
        update(s);
        remove(s);
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void update(Student emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, emp.getId());
        fromDb.setName(emp.getName());
        fromDb.setGpa(emp.getGpa());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void remove(Student emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }      
    }
    
    public static Student findStudentById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        Student s = em.find(Student.class, id);
        em.close();
        return s;
    }
    
    public static List<Student> findAllStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        List<Student> studentList = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        em.close();
        return studentList;
    }
    
     public static List<Student> findStudentByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Student.findByName");
        query.setParameter("name", name);
        List<Student> studentList = (List<Student>) query.getResultList();
        em.close();
        return studentList;
    }
}
