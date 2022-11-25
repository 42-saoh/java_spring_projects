package exceptionandcollection;

import java.util.*;

public class ExceptionAndCollection {

    static void insertBook(ArrayList<String> bookList, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter the book name");
            bookList.add(bookScanner.nextLine());
            System.out.println("Do you want to add more books? (y or any key)");
            if (!bookScanner.nextLine().equalsIgnoreCase("y"))
                break ;
        }
    }

    static void insertBook(HashMap<String, Integer> bookMap, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter the book name");
            String bookName = bookScanner.nextLine();
            if (bookMap.containsKey(bookName)) {
                System.out.println("Book already exists");
                continue ;
            }
            while (true) {
                try {
                    System.out.println("Enter the book price");
                    bookMap.put(bookName, Integer.parseInt(bookScanner.nextLine()));
                    break ;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price");
                }
            }
            System.out.println("Do you want to add more books? (y or any key)");
            if (!bookScanner.nextLine().equalsIgnoreCase("y"))
                break ;
        }
    }

    static void findBook(ArrayList<String> bookList, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter the book name to search");
            String bookName = bookScanner.nextLine();
            if (bookList.contains(bookName)) {
                System.out.println("Book is exist");
            } else {
                System.out.println("Book not found");
            }
            System.out.println("Do you want to search more books? (y or any key)");
            if (!bookScanner.nextLine().equalsIgnoreCase("y"))
                break ;
        }
    }

    static void findBook(HashMap<String, Integer> bookMap, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter the book name to search");
            String bookName = bookScanner.nextLine();
            if (bookMap.containsKey(bookName)) {
                System.out.println("Book is exist and the price is " + bookMap.get(bookName));
            } else {
                System.out.println("Book not found");
            }
            System.out.println("Do you want to search more books? (y or any key)");
            if (!bookScanner.nextLine().equalsIgnoreCase("y"))
                break ;
        }
    }

    static void deleteBook(ArrayList<String> bookList, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter the book name to delete");
            String bookName = bookScanner.nextLine();
            if (bookList.contains(bookName)) {
                bookList.remove(bookName);
                System.out.println("Book is deleted");
            } else {
                System.out.println("Book not found");
            }
            System.out.println("Do you want to delete more books? (y or any key)");
            if (!bookScanner.nextLine().equalsIgnoreCase("y"))
                break ;
        }
    }

    static void deleteBook(HashMap<String, Integer> bookMap, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter the book name to delete");
            String bookName = bookScanner.nextLine();
            if (bookMap.containsKey(bookName)) {
                bookMap.remove(bookName);
                System.out.println("Book is deleted");
            } else {
                System.out.println("Book not found");
            }
            System.out.println("Do you want to delete more books? (y or any key)");
            if (!bookScanner.nextLine().equalsIgnoreCase("y"))
                break ;
        }
    }

    static void selectList(ArrayList<String> bookList, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter your choice \n1. Insert book \n2. Find book \n3. Delete book \n4. Exit");
            try {
                int choice = Integer.parseInt(bookScanner.nextLine());
                if (choice == 4)
                    break ;
                else if (choice == 1)
                    insertBook(bookList, bookScanner);
                else if (choice == 2)
                    findBook(bookList, bookScanner);
                else if (choice == 3)
                    deleteBook(bookList, bookScanner);
                else
                    System.out.println("Invalid choice");
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }
    }

    static void selectMap(HashMap<String, Integer> bookMap, Scanner bookScanner) {
        while (true) {
            System.out.println("Enter your choice \n1. Insert book \n2. Find book \n3. Delete book \n4. Exit");
            try {
                int choice = Integer.parseInt(bookScanner.nextLine());
                if (choice == 4)
                    break ;
                else if (choice == 1)
                    insertBook(bookMap, bookScanner);
                else if (choice == 2)
                    findBook(bookMap, bookScanner);
                else if (choice == 3)
                    deleteBook(bookMap, bookScanner);
                else
                    System.out.println("Invalid choice");
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        Scanner bookScanner = new Scanner(System.in);
        ArrayList<String> bookList = new ArrayList<>();
        HashMap<String, Integer> bookMap = new HashMap<>();
        while (true) {
            System.out.println("Enter your choice \n1. Book Array \n2. Book Map \n3. Exit");
            try {
                int choice = Integer.parseInt(bookScanner.nextLine());
                if (choice == 3)
                    break ;
                else if (choice == 1)
                    selectList(bookList, bookScanner);
                else if (choice == 2)
                    selectMap(bookMap, bookScanner);
                else
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
        bookList.forEach(s -> System.out.println("Book name: " + s));
        bookMap.forEach((s, p) -> System.out.println("Book Name : " + s + ", Price : " + p));
    }
}
