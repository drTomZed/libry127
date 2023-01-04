package pl.sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

import static pl.sdacademy.HibernateUtil.getSessionFactory;

/**
 * @author mszyrner
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello jędrna dupa!");
        Session session = getSessionFactory().openSession();


        //Check databse vaersion
        String sql = "Select version()";

        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(result);

        session.close();
        HibernateUtil.shutdown();

    }
    /*
    package pl.sdacademy;


import pl.sdacademy.ui.ConsoleInterface;

public class Main {
    public static void main(String[] args) {

    ConsoleInterface consoleInterface= new ConsoleInterface();
    consoleInterface.chooseOption();

    }
}
    */


}