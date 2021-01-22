package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.Instructor;
import com.berkanaslan.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructorDetailApplication {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            // Start a transaction
            session.beginTransaction();

            int id = 3;

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            // Remove the associated object referance
            // Break bi-directional link

            instructorDetail.getInstructor().setInstructorDetail(null);

            System.out.println("Instructor Detail: " + instructorDetail.toString());
            System.out.println("Instructor: " + instructorDetail.getInstructor());

            // Now let's delete the instructor detail
            session.delete(instructorDetail);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
