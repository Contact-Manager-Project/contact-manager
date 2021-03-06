package Contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class contactInfo {
    private static Scanner input = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\n" +
                "1 - View contacts.\n" +
                "2 - Add a new contact.\n" +
                "3 - Search a contact by name.\n" +
                "4 - Delete an existing contact.\n" +
                "5 - Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5): ");

    }

    public static List<String> readFile(Path contactFile) {
        List<String> contactStrings = new ArrayList<>();
        try {
            contactStrings = Files.readAllLines(contactFile);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return contactStrings;
    }

    private static void showContacts(List<String> info) {
        System.out.println("Name | Phonenumber");
        System.out.println("-------");
        for (int i = 0; i < info.size(); i++) {
            System.out.println(info.get(i));
        }
    }

    public static List<Contact> stringToObject(List<String> contactStrings) {
        List<Contact> createdObject = new ArrayList<>();

        for (int i = 0; i < contactStrings.size(); i++) {
          String[] resultOfSplittingStrings =  contactStrings.get(i).split("\\| ");
            System.out.println(resultOfSplittingStrings);
            System.out.println(resultOfSplittingStrings[0]);
            System.out.println(resultOfSplittingStrings[1]);

            Contact contactInfo = new Contact(resultOfSplittingStrings[0], resultOfSplittingStrings[1]);
            createdObject.add(contactInfo);
        }
        return createdObject;
    }

    public static String addContact() {
        Scanner firstInput = new Scanner(System.in);
        Scanner secondInput = new Scanner(System.in);
        System.out.println("Please enter in a name: ");
        String name = firstInput.nextLine();
        System.out.println("Please enter in a phone number: ");
        String phoneNumber = secondInput.next();
        Contact newContact = new Contact(name, phoneNumber);
        return newContact.toString();
    }

    public static void searchContact(List<String> info) {
        System.out.println("Please enter  a name: ");
        String searcher = input.nextLine();
        for (int i = 0; i < info.size(); i++) {
            if (info.get(i).equalsIgnoreCase(searcher)){
                System.out.println(info.get(i));
            }
        }
    }

    public static void deleteContact(List<String> info, Path file) throws IOException {
        List<String> newList = new ArrayList<>();
        System.out.println("Please enter a name: ");
        String searcher = input.nextLine();
        for (int i = 0; i < info.size(); i++) {
            if (info.get(i).contains(searcher)){
                System.out.println(info.get(i) + " has been deleted.");
                continue;
            }
            newList.add(info.get(i));
        }
        Files.write(file, newList);
    }

    public static void main(String[] args) {
        printMenu();

        String directory = "contactBook";
        String filename = "contacts.txt";
        Path contactDirectory = Paths.get(directory);
        Path contactFile = Paths.get(directory, filename);
        List<String> info = readFile(contactFile);

        try{
            if (Files.notExists(contactDirectory)){
                Files.createDirectories(contactDirectory);
            }
            if (Files.notExists(contactFile)){
                Files.createFile(contactFile);
            }

//            List<String> contact = new ArrayList<>(Arrays.asList("Sonic the Hedgehog | 76642464" , "Scooby-Doo the Brown Dog | 6978379 ", "Fred the Caveman Flintstone | 35468786637 ", "the Alternate TV Intruder | 6163351"));
//            Files.write(contactFile, contact);

            String inputString;
            do {
                inputString = input.nextLine();
                if (inputString.equals("1")){
                    info = readFile(contactFile);
                    showContacts(info);
                }
                if (inputString.equals("2")) {
                    String contactToAdd = addContact();
                    Files.write(contactFile, Arrays.asList(contactToAdd), StandardOpenOption.APPEND);
                    System.out.println("New contact added.");
                }
                if (inputString.equals("3")){
                    info = readFile(contactFile);
                    searchContact(info);
                }
                if (inputString.equals("4")){
                    info = readFile(contactFile);
                    deleteContact(info, contactFile);
                }
                if (inputString.equals("5")){
                    System.out.println("Have a nice day!");
                }
            }while (!inputString.equals("5"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
