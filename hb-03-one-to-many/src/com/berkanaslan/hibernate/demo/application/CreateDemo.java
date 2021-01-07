package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.Instructor;
import com.berkanaslan.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create a session
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            // Start a transaction
            session.beginTransaction();

            Instructor tempInstructor = new Instructor("Berkan", "Aslan", "hello@berkanaslan.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("@berkanaslan", "Coffee");

            // Associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // Save the instructor
            // Note: This will ALSO save the details object
            // Because of CascadeType.ALL
            session.save(tempInstructor);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
