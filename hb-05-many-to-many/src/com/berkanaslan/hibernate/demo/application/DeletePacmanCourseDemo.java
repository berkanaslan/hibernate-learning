package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeletePacmanCourseDemo {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            // Get the Pacman course from the database
            int courseId = 13;
            Course tempCourse = session.get(Course.class, courseId);

            // Delete the course
            System.out.println("Deleting course: " + tempCourse);

            session.delete(tempCourse);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
