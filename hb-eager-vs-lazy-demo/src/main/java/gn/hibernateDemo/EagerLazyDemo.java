package gn.hibernateDemo;

import gn.hibernateDemo.entity.Course;
import gn.hibernateDemo.entity.Instructor;
import gn.hibernateDemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

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

            // get the instructor from db
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("luv2code: Instructor: " + instructor);

            // retrieve courses from db
            System.out.println("luv2code: Courses: " + instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            // close the session
            System.out.println("Closing session...");
            session.close();

            // option 1: call getter method while session is open

            // get courses from db
            System.out.println("luv2code: Courses: " + instructor.getCourses());

            System.out.println("luv2code: Done!");

        } finally {
            // add clean up code
            session.close();

            factory.close();
        }
    }
}
