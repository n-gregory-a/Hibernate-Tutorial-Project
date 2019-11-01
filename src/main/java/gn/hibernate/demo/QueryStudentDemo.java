package gn.hibernate.demo;

import gn.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {


            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(students);

            // query students: lastName="Doe"
            students = session.createQuery("from Student  s where s.lastName='Doe'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Doe");
            displayStudents(students);

            // query students: lastName='Doe' OR firstName='Daffy'
            students = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
            displayStudents(students);

            // query students where email LIKE '%luv2code.com'
            students = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have email LIKE luv2code.com");
            displayStudents(students);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student tempStudent : students) {
            System.out.println(tempStudent);
        }
    }
}
