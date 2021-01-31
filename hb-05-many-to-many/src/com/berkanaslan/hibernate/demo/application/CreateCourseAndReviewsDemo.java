package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.Course;
import com.berkanaslan.hibernate.demo.entity.Instructor;
import com.berkanaslan.hibernate.demo.entity.InstructorDetail;
import com.berkanaslan.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            // Create a course
            Course tempCourse = new Course("Pacman - How to score one million points?");

            // Add some reviews
            tempCourse.addReview(new Review("Great course!"));
            tempCourse.addReview(new Review("Cool job, well done."));
            tempCourse.addReview(new Review("What a dumb course, you're an idiot!"));

            // Save the course ... and leverage the cascade all
            session.save(tempCourse);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
