package pl.sdacademy.ui;

import pl.sdacademy.Book;
import pl.sdacademy.HibernateUtil;
import pl.sdacademy.Publisher;

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

    }

    public void addBook() {
        System.out.println("Type book title:");
        Scanner scanner = new Scanner(System.in);
        String book_title = scanner.nextLine();

        System.out.println("Type book ISBN:");
        String book_ISBN = scanner.nextLine();

        System.out.println("Type pubisher");
        String book_publisher = scanner.nextLine();
        Publisher publisher = new Publisher(book_publisher);



        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

        Book book = new Book();
        book.setTitle(book_title);
        book.setISBN(book_ISBN);
        book.setPublisher(publisher);

        entityManager.getTransaction().begin();
        entityManager.persist(publisher);

        entityManager.persist(book);
//        Book result = entityManager.find(Book.class, 1);
//        System.out.println(result.getTitle() + " " + result.getTitle());


        entityManager.getTransaction().commit();

    }


}