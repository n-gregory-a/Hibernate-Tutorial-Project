package gn.hibernateDemo;

import gn.hibernateDemo.entity.Course;
import gn.hibernateDemo.entity.Instructor;
import gn.hibernateDemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get a course
            int id = 10;
            Course course= session.get(Course.class, id);

            // delete a course
            System.out.println("Deleting course: " + course);

            session.delete(course);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            // add clean up code
            session.close();

            factory.close();
        }
    }
}
