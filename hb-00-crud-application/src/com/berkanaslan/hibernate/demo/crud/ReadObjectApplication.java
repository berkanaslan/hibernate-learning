package com.berkanaslan.hibernate.demo.crud;

import com.berkanaslan.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadObjectApplication {
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

            // Save the student object
            session.save(tempStudent);

            // Commit transaction
            session.getTransaction().commit();

            // Read object from database
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get completed: " + myStudent.toString());

            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
