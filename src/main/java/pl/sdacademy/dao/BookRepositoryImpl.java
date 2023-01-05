package pl.sdacademy.dao;

import pl.sdacademy.HibernateUtil;
import pl.sdacademy.entity.Book;
import pl.sdacademy.entity.Publisher;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;


public class BookRepositoryImpl<TypedQuery> implements Repository<Book, Integer> {




    EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
    Scanner scanner = new Scanner(System.in);
    @Override
    public Book findById( ) {
        System.out.println("Type ID of the book: ");

        int lookingId = scanner.nextInt();
        entityManager.getTransaction().begin();
        Book result = entityManager.find(Book.class, lookingId);
        entityManager.close();
        System.out.println(result);
        return result;
    }
    @Override
    public Book findByISBNorTitle() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.print("Choose your option: ");
            System.out.println("\n");
            System.out.println("1. Find book using ISBN number");
            System.out.println("2. Find book using title");
            System.out.println("3. return to main menu");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    findTheBookByISBN();
                    break;
                case 2:
                    findTheBookByTitle();
                    break;
                case 3:
                    System.out.println("returning to menu");
                    break;

                default:
                    System.out.println("Wrong number");
            }
        } while (option != 3);


        return null;
    }
    public void findTheBookByISBN() {
        System.out.println("Type the ISBN number of the book: ");
        Scanner scanner = new Scanner(System.in);
        String lookingISBN = scanner.nextLine();
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<Book> books = entityManager.createQuery("from Book b where b.ISBN= :t", Book.class)
                .setParameter("t", lookingISBN).getResultList();
        if (books.size() > 0) {
            System.out.println("book arleady in the system");
        } else {
            System.out.println("ther's no such a book in the system");
        }
        entityManager.getTransaction().commit();
    }
    public void findTheBookByTitle() {
        System.out.println("Type the title of the book: ");
        Scanner scanner = new Scanner(System.in);
        String lookingTitle = scanner.nextLine();
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<Book> books = entityManager.createQuery("from Book b where b.title= :t", Book.class)
                .setParameter("t", lookingTitle).getResultList();
        if (books.size() > 0) {
            System.out.println("book arleady in the system");
        } else {
            System.out.println("ther's no such a book in the system");
        }
        entityManager.getTransaction().commit();
    }


    @Override
    public List<Book> findAll() {
        entityManager.getTransaction().begin();
        List<Book> result = entityManager.createQuery("from Book", Book.class).getResultList();
        entityManager.close();
        for (Book book : result) {
            System.out.println(book);
        }
        return result;

    }

    public String bookIsbnChecker() {
        String bookISBN;
        do {
            Scanner scanner = new Scanner(System.in);
            bookISBN = scanner.nextLine();
            if (bookISBN.matches("[0-9]{13}")) {
                System.out.println("Number is correct");
            }
            else {
                System.out.println("Number is wrong, please write correct number using 13 digits only");
            }
        } while (!bookISBN.matches("[0-9]{13}"));

        return bookISBN;
    }


    @Override
    public void addBook() {
        System.out.println("Type book title:");
        Scanner scanner = new Scanner(System.in);
        String book_title = scanner.nextLine();

        System.out.println("Please type book ISBN number (13 digits):");
        String bookISBN = bookIsbnChecker();

        System.out.println("Type pubisher");
        String book_publisher = scanner.nextLine();
        Publisher publisher = new Publisher(book_publisher);

        Book book = new Book(book_title,bookISBN,publisher);
        entityManager.getTransaction().begin();
        entityManager.persist(publisher);

        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void deleteBook( ) {
        System.out.println("Type title of the boook: ");

        String titleToDelete = scanner.nextLine();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from Book b where b.title= :t")
                .setParameter("t", titleToDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
