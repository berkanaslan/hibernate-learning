package com.berkanaslan.hibernate.demo.crud;

import com.berkanaslan.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateObjectApplication {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create a session
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            // Start a transaction
            session.beginTransaction();

            int studentId = 1;

            Student myStudent = session.get(Student.class, studentId);
            myStudent.setFirstName("Scooby");
            session.update(myStudent);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
