package pl.sdacademy;

import pl.sdacademy.dao.BookRepositoryImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        int option;
        Scanner scanner = new Scanner(System.in);
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton deleteButton = new JButton("Delete book");
        JButton addButton = new JButton("Add new book");
        JButton findByIdButton = new JButton("Find book using id number");
        JButton findByISBNorTitleButton = new JButton("Find book using title or ISBN");
        JButton findAllButton = new JButton("Show all books from base");
        JButton quitButton = new JButton("Quit this shit");

        frame.add(deleteButton);
        frame.add(addButton);
        frame.add(findByIdButton);
        frame.add(findByISBNorTitleButton);
        frame.add(findAllButton);
        frame.add(quitButton);

        frame.setSize(300, 300);
        frame.setVisible(true);

        BookRepositoryImpl bookRepositoryImpl = new BookRepositoryImpl();

        deleteButton.addActionListener(e -> bookRepositoryImpl.deleteBook());
        addButton.addActionListener(e -> bookRepositoryImpl.addBook());
        findByIdButton.addActionListener(e -> bookRepositoryImpl.findById());
        findByISBNorTitleButton.addActionListener(e -> bookRepositoryImpl.findByISBNorTitle());
        findAllButton.addActionListener(e -> bookRepositoryImpl.findAll());
        quitButton.addActionListener(e -> {
            System.out.println("End of this APPLICATION");
            System.exit(0);
            HibernateUtil.shutdown();
        });
    }
}
