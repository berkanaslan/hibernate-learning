package com.berkanaslan.hibernate.demo.application;

import com.berkanaslan.hibernate.demo.entity.Course;
import com.berkanaslan.hibernate.demo.entity.Instructor;
import com.berkanaslan.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class FetchJoinDemo {
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

            // Hibernate Query with HQL
            Query<Instructor> query;

            query = session.createQuery("select  i from Instructor i join fetch i.courses where i.id =: theInstructorId", Instructor.class);

            // Set parameter on the query
            query.setParameter("theInstructorId", id);

            // Execute the query and get instructor
            Instructor instructor = query.getSingleResult();
            System.out.println("Courses: " + instructor.getCourses());

            session.getTransaction().commit();

            System.out.println("Courses: " + instructor.getCourses());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
