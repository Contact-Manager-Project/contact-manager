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
//    private void addNewContact() {
//        Files.write(contactFile, contact, StandardOpenOption.APPEND);
//        contact = Files.readAllLines(contactFile);
//        System.out.printf("Name | PhoneNumber\n ----------\n %s", contact);
//    }

    private static void showContacts() {

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

    public static void main(String[] args) {
        printMenu();
//        addNewContact();
        showContacts();

        String directory = "contactBook";
        String filename = "contacts.txt";
        Path contactDirectory = Paths.get(directory);
        Path contactFile = Paths.get(directory, filename);
        List<String> info = readFile(contactFile);
        System.out.println(stringToObject(info));
        System.out.println(info);

        try{





            if (Files.notExists(contactDirectory)){
                Files.createDirectories(contactDirectory);
            }

            if (Files.notExists(contactFile)){
                Files.createFile(contactFile);
            }

            List<String> contact = new ArrayList<>(Arrays.asList("Sonic the Hedgehog | 76642464" , "Scooby-Doo the Brown Dog | 6978379 ", "Fred the Caveman Flintstone | 35468786637 ", "the Alternate TV Intruder | 6163351"));
            Files.write(contactFile, contact);

            do {
                if (input.nextInt() == 1){


                }
                if (input.nextInt() == 2) {
//addNewContact();
                }
            }while (input.nextInt() != 5);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
