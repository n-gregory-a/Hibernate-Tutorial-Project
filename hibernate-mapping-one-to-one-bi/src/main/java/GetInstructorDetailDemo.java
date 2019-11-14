
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

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

            // get the instructor detail object
            int id = 1;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            // print the instructor detail
            System.out.println("instructorDetail: " + instructorDetail);

            // print the associated instructor
            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
