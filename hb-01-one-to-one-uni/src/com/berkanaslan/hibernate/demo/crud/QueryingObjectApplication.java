package com.berkanaslan.hibernate.demo.crud;

import com.berkanaslan.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryingObjectApplication {
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

            // Query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // Display the students
            printStudents(theStudents);

            // Query students: lastName=Doe
            theStudents = session.createQuery("from Student s where s.lastName ='Doe'").getResultList();
            printStudents(theStudents);

            // Query students: lastName="Doe" OR firstName="Daffy"
            theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName = 'Daffy'").getResultList();
            printStudents(theStudents);

            // Query students: WHERE email LIKE '%gmail.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
            printStudents(theStudents);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printStudents(List<Student> theStudents) {
        for (Student temp : theStudents) {
            System.out.println(temp.toString());
        }
    }
}
