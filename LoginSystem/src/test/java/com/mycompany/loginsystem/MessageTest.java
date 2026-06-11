/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.loginsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
/**
 *
 * @author Mulondo Munjodzi
 */
public class MessageTest {


    private final Message msg = new Message();

    @Test
    void testCheckRecipientCellValid() {
        assertTrue(msg.checkRecipientCell("+27831234567"));
    }

    @Test
    void testCheckRecipientCellInvalid() {
        assertFalse(msg.checkRecipientCell("0831234567"));
    }

    @Test
    void testValidateMessageValid() {
        assertEquals(
                "Message ready to send.",
                msg.validateMessage("Hello")
        );
    }

    @Test
    void testValidateMessageTooLong() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 251; i++) {
            sb.append("a");
        }

        String result = msg.validateMessage(sb.toString());

        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    @Test
    void testGenerateMessageID() {

        String id = msg.generateMessageID();

        assertNotNull(id);
        assertEquals(10, id.length());
    }

    @Test
    void testCreateMessageHash() {

        String hash = msg.createMessageHash(
                "1234567890",
                1,
                "Hello World"
        );

        assertEquals("12:1:HELLOWORLD", hash);
    }

    @Test
    void testSentMessageSend() {
        assertEquals(
                "Message successfully sent.",
                msg.sentMessage(1)
        );
    }

    @Test
    void testSentMessageDisregard() {
        assertEquals(
                "Message disregarded.",
                msg.sentMessage(2)
        );
    }

    @Test
    void testSentMessageStore() {
        assertEquals(
                "Message successfully stored.",
                msg.sentMessage(3)
        );
    }

    @Test
    void testSentMessageInvalid() {
        assertEquals(
                "Invalid option.",
                msg.sentMessage(100)
        );
    }

    @Test
    void testGetLongestMessage() throws Exception {

        Field field =
                Message.class.getDeclaredField("storedMessages");

        field.setAccessible(true);

        ArrayList<String> messages =
                (ArrayList<String>) field.get(msg);

        messages.add("Hi");
        messages.add("This is the longest message");
        messages.add("Hello");

        assertEquals(
                "This is the longest message",
                msg.getLongestMessage()
        );
    }

    @Test
    void testSearchMessageID() throws Exception {

        addStoredMessage(
                "1234567890",
                "12:1:HELLOWORLD",
                "+27831234567",
                "Hello World"
        );

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        msg.searchMessageID("1234567890");

        assertTrue(
                output.toString().contains("Hello World")
        );
    }

    @Test
    void testSearchRecipient() throws Exception {

        addStoredMessage(
                "1234567890",
                "12:1:HELLOWORLD",
                "+27831234567",
                "Hello World"
        );

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        msg.searchRecipient("+27831234567");

        assertTrue(
                output.toString().contains("Hello World")
        );
    }

    @Test
    void testDeleteMessage() throws Exception {

        addStoredMessage(
                "1234567890",
                "12:1:HELLOWORLD",
                "+27831234567",
                "Hello World"
        );

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        msg.deleteMessage("12:1:HELLOWORLD");

        assertTrue(
                output.toString().contains("successfully deleted")
        );
    }

    private void addStoredMessage(
            String id,
            String hash,
            String recipient,
            String message) throws Exception {

        Field storedMessages =
                Message.class.getDeclaredField("storedMessages");
        Field storedRecipients =
                Message.class.getDeclaredField("storedRecipients");
        Field storedIDs =
                Message.class.getDeclaredField("storedIDs");
        Field storedHashes =
                Message.class.getDeclaredField("storedHashes");

        storedMessages.setAccessible(true);
        storedRecipients.setAccessible(true);
        storedIDs.setAccessible(true);
        storedHashes.setAccessible(true);

        ((ArrayList<String>) storedMessages.get(msg)).add(message);
        ((ArrayList<String>) storedRecipients.get(msg)).add(recipient);
        ((ArrayList<String>) storedIDs.get(msg)).add(id);
        ((ArrayList<String>) storedHashes.get(msg)).add(hash);
    }
}