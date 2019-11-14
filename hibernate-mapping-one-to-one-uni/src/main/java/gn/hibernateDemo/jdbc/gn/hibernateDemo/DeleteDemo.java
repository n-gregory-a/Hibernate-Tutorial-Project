package gn.hibernateDemo.jdbc.gn.hibernateDemo;

import gn.hibernateDemo.jdbc.gn.hibernateDemo.entity.Instructor;
import gn.hibernateDemo.jdbc.gn.hibernateDemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

            // start a transaction
            session.beginTransaction();

            // get instructor by the primary key / id

            int id = 2;

            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Found instructor: " + instructor);

            // delete the instructor
            if (instructor != null) {
                System.out.println("Deleting: " + instructor);

                // Note: will ALSO delete associated "details" object
                // because of CascadeType.All
                //
                session.delete(instructor);
            }


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
