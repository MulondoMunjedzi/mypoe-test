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


public class Message {
    


    static Scanner scanner = new Scanner(System.in);

    // CHECK RECIPIENT NUMBER

    public boolean checkRecipientCell(String recipient) {

        return recipient.startsWith("+")

                && recipient.length() <= 13;

    }

    // VALIDATE MESSAGE

    public String validateMessage(String message) {

        if (message.length() <= 250) {

            return "Message ready to send.";

        }

        int extra = message.length() - 250;

        return "Message exceeds 250 characters by "

                + extra +

                ", please reduce the size.";

    }

    // GENERATE MESSAGE ID

    public String generateMessageID() {

        Random random = new Random();

        long number =

                1000000000L +

                (long)(random.nextDouble() * 9000000000L);

        return String.valueOf(number);

    }

    // CREATE MESSAGE HASH

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

    // SEND MESSAGE OPTIONS

    public String sentMessage(int option) {

        switch (option) {

            case 1:

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete the message.";

            case 3:

                return "Message successfully stored.";

            default:

                return "Invalid option.";

        }

    }

    // MAIN METHOD

    public static void main(String[] args) {

        Message user = new Message();

        System.out.println("Welcome to QuickChat.");

        boolean running = true;

        while (running) {

            System.out.println("\n===== MENU =====");

            System.out.println("1. Send Messages");

            System.out.println("2. Show recently sent messages");

            System.out.println("3. Quit");

            System.out.println("Choose option:");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("How many messages would you like to send?");

                    int totalMessages = scanner.nextInt();

                    scanner.nextLine();

                    int sentMessages = 0;

                    for (int i = 0; i < totalMessages; i++) {

                        System.out.println("\n===== MESSAGE " + (i + 1) + " =====");

                        // RECIPIENT

                        System.out.println("Enter recipient number:");

                        String recipient = scanner.nextLine();

                        if (user.checkRecipientCell(recipient)) {

                            System.out.println("Cell phone number successfully captured.");

                        } else {

                            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");

                            continue;

                        }

                        // MESSAGE

                        System.out.println("Enter your message:");

                        String message = scanner.nextLine();

                        String validationResult =

                                user.validateMessage(message);

                        if (!validationResult.equals("Message ready to send.")) {

                            System.out.println(validationResult);

                            continue;

                        } else {

                            System.out.println("Message sent");

                        }

                        // GENERATE ID

                        String messageID =

                                user.generateMessageID();

                        // CREATE HASH

                        String messageHash =

                                user.createMessageHash(

                                        messageID,

                                        i,

                                        message);

                        // SEND OPTIONS

                        System.out.println("\nChoose an option:");

                        System.out.println("1. Send Message");

                        System.out.println("2. Disregard Message");

                        System.out.println("3. Store Message");

                        int option = scanner.nextInt();

                        scanner.nextLine();

                        String result =

                                user.sentMessage(option);

                        System.out.println(result);

                        // DISPLAY DETAILS

                        if (option == 1) {

                            sentMessages++;

                            System.out.println("\n===== MESSAGE DETAILS =====");

                            System.out.println("Message ID: " + messageID);

                            System.out.println("Message Hash: " + messageHash);

                            System.out.println("Recipient: " + recipient);

                            System.out.println("Message: " + message);

                        }

                    }

                    System.out.println("\nTotal messages sent: "

                            + sentMessages);

                    break;

                case 2:

                    System.out.println("Coming Soon.");

                    break;

                case 3:

                    System.out.println("Goodbye.");

                    running = false;

                    break;

                default:

                    System.out.println("Invalid option.");

            }

        }

        scanner.close();

    }

}
