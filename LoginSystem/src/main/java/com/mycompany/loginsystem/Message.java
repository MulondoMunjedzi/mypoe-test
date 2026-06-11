/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginsystem;

/**
 *
 * @author Mulondo Munjodzi
 */

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Message {
    

    static Scanner scanner = new Scanner(System.in);

    
    // PART 3: DATA  ARRAYS STORAGE
    

    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> sentRecipients = new ArrayList<>();
    private ArrayList<String> sentIDs = new ArrayList<>();
    private ArrayList<String> sentHashes = new ArrayList<>();

    ArrayList<String> storedMessages = new ArrayList<>();
    ArrayList<String> storedRecipients = new ArrayList<>();
    private ArrayList<String> storedIDs = new ArrayList<>();
    private ArrayList<String> storedHashes = new ArrayList<>();

    private ArrayList<String> disregardedMessages = new ArrayList<>();

    
    // VALIDATION (PART 2 + PART 3)
    

    public boolean checkRecipientCell(String recipient) {
        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    public String validateMessage(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        int extra = message.length() - 250;

        return "Message exceeds 250 characters by " + extra +
                ", please reduce the size.";
    }

    
    // MESSAGE ID GENERATION (PART 2)
    

    public String generateMessageID() {
        Random random = new Random();

        long number =
                1000000000L +
                (long)(random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    
    // MESSAGE HASH CREATION (PART 2)
    

    public String createMessageHash(String messageID,
                                    int messageNumber,
                                    String message) {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + (firstWord + lastWord).toUpperCase();
    }

    
    // MESSAGE OPTION RESPONSE (PART 2)
    

    public String sentMessage(int option) {

        switch (option) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Message disregarded.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    
    // PART 3: DISPLAY STORED MESSAGES
    

    public void displayStoredMessages() {

        for (int i = 0; i < storedMessages.size(); i++) {

            System.out.println("\n===== STORED MESSAGE =====");
            System.out.println("Recipient: " + storedRecipients.get(i));
            System.out.println("Message ID: " + storedIDs.get(i));
            System.out.println("Message Hash: " + storedHashes.get(i));
            System.out.println("Message: " + storedMessages.get(i));
        }
    }

    
    // PART 3: LONGEST MESSAGE
    

    public String getLongestMessage() {

        String longest = "";

        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        return longest;
    }

    
    // PART 3: SEARCH BY MESSAGE ID
    

    public void searchMessageID(String id) {

        for (int i = 0; i < storedIDs.size(); i++) {

            if (storedIDs.get(i).equals(id)) {

                System.out.println("Recipient: " + storedRecipients.get(i));
                System.out.println("Message: " + storedMessages.get(i));
                return;
            }
        }

        System.out.println("Message not found.");
    }

    
    // PART 3: SEARCH BY RECIPIENT
    

    public void searchRecipient(String number) {

        boolean found = false;

        for (int i = 0; i < storedRecipients.size(); i++) {

            if (storedRecipients.get(i).equals(number)) {

                System.out.println(storedMessages.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found.");
        }
    }

    
    // PART 3: DELETE MESSAGE BY HASH
    

    public void deleteMessage(String hash) {

        for (int i = 0; i < storedHashes.size(); i++) {

            if (storedHashes.get(i).equals(hash)) {

                System.out.println(storedMessages.get(i) +
                        " successfully deleted.");

                storedMessages.remove(i);
                storedRecipients.remove(i);
                storedIDs.remove(i);
                storedHashes.remove(i);

                return;
            }
        }

        System.out.println("Hash not found.");
    }

    
    // PART 3: SENT MESSAGE REPORT
    

    public void displayReport() {

        System.out.println("\n===== SENT MESSAGE REPORT =====");

        for (int i = 0; i < sentMessages.size(); i++) {

            System.out.println("Hash: " + sentHashes.get(i));
            System.out.println("Recipient: " + sentRecipients.get(i));
            System.out.println("Message: " + sentMessages.get(i));
            System.out.println("----------------------------");
        }
    }

    
    // MAIN METHOD
    

    public static void main(String[] args) {

        Message user = new Message();

        boolean running = true;

        while (running) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages (PART 3)");

            System.out.println("Choose option:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("How many messages would you like to send?");
                    int totalMessages = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < totalMessages; i++) {

                        System.out.println("Enter recipient:");
                        String recipient = scanner.nextLine();

                        System.out.println("Enter message:");
                        String message = scanner.nextLine();

                        String messageID = user.generateMessageID();
                        String messageHash = user.createMessageHash(messageID, i, message);

                        System.out.println("Choose option:");
                        System.out.println("1 Send");
                        System.out.println("2 Disregard");
                        System.out.println("3 Store");

                        int option = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(user.sentMessage(option));

                        
                        // PART 3: SEND MESSAGE
                        
                        if (option == 1) {

                            user.sentMessages.add(message);
                            user.sentRecipients.add(recipient);
                            user.sentIDs.add(messageID);
                            user.sentHashes.add(messageHash);

                            System.out.println("\n===== MESSAGE DETAILS =====");
                            System.out.println("Message ID: " + messageID);
                            System.out.println("Message Hash: " + messageHash);
                            System.out.println("Recipient: " + recipient);
                            System.out.println("Message: " + message);
                        }

                        
                        // PART 3: DISREGARD MESSAGE
                        
                        else if (option == 2) {

                            user.disregardedMessages.add(message);
                        }

                        
                        // PART 3: STORE MESSAGE
                        
                        else if (option == 3) {

                            user.storedMessages.add(message);
                            user.storedRecipients.add(recipient);
                            user.storedIDs.add(messageID);
                            user.storedHashes.add(messageHash);
                        }
                    }

                    break;

                case 2:
                    System.out.println("Coming soon.");
                    break;

                case 3:
                    running = false;
                    break;

                
                // PART 3: STORED MESSAGE MENU
                
                case 4:

                    System.out.println("1 Display Stored Messages");
                    System.out.println("2 Longest Message");
                    System.out.println("3 Search ID");
                    System.out.println("4 Search Recipient");
                    System.out.println("5 Delete Message");
                    System.out.println("6 Report");

                    int opt = scanner.nextInt();
                    scanner.nextLine();

                    switch (opt) {

                        case 1:
                            user.displayStoredMessages();
                            break;

                        case 2:
                            System.out.println(user.getLongestMessage());
                            break;

                        case 3:
                            System.out.println("Enter ID:");
                            user.searchMessageID(scanner.nextLine());
                            break;

                        case 4:
                            System.out.println("Enter recipient:");
                            user.searchRecipient(scanner.nextLine());
                            break;

                        case 5:
                            System.out.println("Enter hash:");
                            user.deleteMessage(scanner.nextLine());
                            break;

                        case 6:
                            user.displayReport();
                            break;
                    }

                    break;
            }
        }
    }

    
}