package gn.hibernateDemo;

import gn.hibernateDemo.entity.Course;
import gn.hibernateDemo.entity.Instructor;
import gn.hibernateDemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // option 2: Hibernate query with HQL

            // get the instructor from db
            int id = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                        + "join fetch i.courses "
                                        + "where i.id=:theInstructorId",
                                      Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", id);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("luv2code: Instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();

            // close the session
            System.out.println("Closing session...");
            session.close();

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
