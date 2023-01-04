package pl.sdacademy.ui;

import pl.sdacademy.Book;
import pl.sdacademy.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class BookFinder {
    public void isBookExists() {

        System.out.println("Type the title of the boook: ");
        Scanner scanner = new Scanner(System.in);
        String lookingTitle = scanner.nextLine();
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<Book> books = entityManager.createQuery("from Book b where b.title= :t", Book.class)
                .setParameter("t", lookingTitle).getResultList();

        if (books.size() > 0) {
            System.out.println("book arleady in the system");
        } else {
            System.out.println("ther's no such a book in the system you idiot");
        }
        entityManager.getTransaction().commit();

    }

    public void deleteBook() {
        System.out.println("Type book title for delete:");
        Scanner scanner = new Scanner(System.in);
        String titleToDelete = scanner.nextLine();
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("DELETE from Book b where b.title= :t")
                .setParameter("t", titleToDelete).executeUpdate();

        entityManager.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}