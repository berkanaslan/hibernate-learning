package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.Course;
import com.berkanaslan.hibernate.demo.entity.Instructor;
import com.berkanaslan.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadInstructorCoursesDemo {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            // Get courses fot the instructor

            System.out.println("Courses: " + instructor.getCourses());



            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
