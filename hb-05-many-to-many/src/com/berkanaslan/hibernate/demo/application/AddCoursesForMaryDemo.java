package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AddCoursesForMaryDemo {
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

            // Get the student Mary from database
            int studentId = 2;
            Student tempStudent1 = session.get(Student.class, studentId);

            System.out.println("\n Loaded student: " + tempStudent1.toString());
            System.out.println("Courses: " + tempStudent1.getCourses());

            // Create more courses
            Course tempCourse1 = new Course("Rubik's Cube: How to speed cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // Add student to courses
            tempCourse1.addStudent(tempStudent1);
            tempCourse2.addStudent(tempStudent1);

            // Save the courses
            System.out.println("Saving courses...");

            session.save(tempCourse1);
            session.save(tempCourse2);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
