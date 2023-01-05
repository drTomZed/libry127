package pl.sdacademy;

import pl.sdacademy.dao.BookRepositoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("1. Delete book");
            System.out.println("2. AFind book using id number");
            System.out.println("4. Show all books from baze");
            System.out.println("0. Quit this shit");
            System.out.print("Choose your option: ");
            option = scanner.nextInt();
            BookRepositoryImpl bookRepositoryImpl = new BookRepositoryImpl();
            switch (option) {
                case 1:
                    bookRepositoryImpl.deleteBook();
                    break;
                case 2:
                    bookRepositoryImpl.addBook();
                    break;
                case 3:
                    bookRepositoryImpl.findById();
                    break;
                case 4:
                    bookRepositoryImpl.findAll();
                    break;
                case 0:
                    System.out.println("End of this bulshit");
                    break;
                default:
                    System.out.println("Wrong number IDIOT");
            }
        } while (option != 0);
        HibernateUtil.shutdown();
    }
}
