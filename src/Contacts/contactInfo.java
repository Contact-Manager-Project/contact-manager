package Contacts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static void main(String[] args) {
        printMenu();

        try{

            String directory = "contactBook";
            String filename = "contacts.txt";

            Path contactDirectory = Paths.get(directory);
            Path contactFile = Paths.get(directory, filename);

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
                    contact = Files.readAllLines(contactFile);
                    System.out.printf("Name | PhoneNumber\n ----------\n %s", contact);
                } else if (input.nextInt() == 5){
                    break;
                }
            }while (true);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
