package gn.hibernateDemo.jdbc.gn.hibernateDemo;

import gn.hibernateDemo.jdbc.gn.hibernateDemo.entity.Instructor;
import gn.hibernateDemo.jdbc.gn.hibernateDemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the objects
            /*
            Instructor instructor =
                    new Instructor("Chad", "Darby", "darby@lov2code.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");

             */

            Instructor instructor =
                    new Instructor("Madhu", "Patel", "madhu@lov2code.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("http://www.youtube.com", "Guitar");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
