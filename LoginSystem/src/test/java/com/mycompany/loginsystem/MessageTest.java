/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.loginsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mulondo Munjodzi
 */
public class MessageTest {
    


    Message msg = new Message();

    // TEST 1: checkRecipientCell

    @Test

    public void testCheckRecipientCell_Valid() {

        assertTrue(msg.checkRecipientCell("+27831234567"));

    }

    @Test

    public void testCheckRecipientCell_Invalid() {

        assertFalse(msg.checkRecipientCell("0831234567"));

    }

    // TEST 2: validateMessage

    @Test

    public void testValidateMessage_Valid() {

        String result = msg.validateMessage("Hello world");

        assertEquals("Message ready to send.", result);

    }

    @Test

    public void testValidateMessage_TooLong() {

        String longMessage = new String(new char[260]).replace("\0", "a");

        String result = msg.validateMessage(longMessage);

        assertTrue(result.contains("exceeds 250 characters"));

    }

    // TEST 3: generateMessageID

    @Test

    public void testGenerateMessageID() {

        String id = msg.generateMessageID();

        assertNotNull(id);

        assertEquals(10, id.length());

    }

    // TEST 4: createMessageHash

    @Test

    public void testCreateMessageHash() {

        String hash = msg.createMessageHash("1234567890", 1, "Hello there");

        assertEquals("12:1:HELLOTHERE", hash);

    }

    // TEST 5: sentMessage

    @Test

    public void testSentMessage_Send() {

        assertEquals("Message successfully sent.", msg.sentMessage(1));

    }

    @Test

    public void testSentMessage_Store() {

        assertEquals("Message successfully stored.", msg.sentMessage(3));

    }

    @Test

    public void testSentMessage_Invalid() {

        assertEquals("Invalid option.", msg.sentMessage(99));

    }

}  