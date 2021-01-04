package com.berkanaslan.hibernate.demo.crud;

import com.berkanaslan.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteObjectApplication {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create a session
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            // Create a student object
            Student tempStudent = new Student("Daffy", "Duck", "duffy@mail.com");

            // Start a transaction
            session.beginTransaction();

            int studentId = 4;

            session.createQuery("delete from Student where id=" + studentId).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
