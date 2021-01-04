package com.berkanaslan.hibernate.demo.crud;

import com.berkanaslan.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateObjectApplication {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create a session

        try (Session session = sessionFactory.getCurrentSession();) {
            // Create a student object
            Student tempStudent = new Student("Bugs", "Bunny", "bugs@bunny.com");

            // Start a transaction
            session.beginTransaction();

            // Save the student object
            session.save(tempStudent);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
