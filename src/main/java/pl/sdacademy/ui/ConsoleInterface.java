package pl.sdacademy.ui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInterface {

    public void chooseOption() {

        Scanner scanner = new Scanner(System.in);
        String greetings = "Welcome in our best dupa Libray. Type number of acction what you want to do:  \n" +
                "1 - Check is there a book in the system \n" +
                "2 - Delete book using title \n" +
                "3 - adding new funkcjons";

        System.out.println(greetings);
        String userSelection = scanner.nextLine();
        Pattern templateRegex = Pattern.compile("\\d+");
        Matcher matcher = templateRegex.matcher(userSelection);

        if (matcher.matches()) {
            int number = Integer.valueOf(userSelection);
            if (number == 1) {
                BookFinder bookFinder = new BookFinder();
                bookFinder.isBookExists();
            } else if (number == 2) {
                BookFinder bookFinder = new BookFinder();
                bookFinder.deleteBook();
            } else if (number == 3) {
                BookFinder bookFinder = new BookFinder();
                bookFinder.addBook();
            } else {
                System.out.println("Number what you put is not correct");
            }
        } else {
            System.out.println("Data what you type in is not correct");
        }

    }


}