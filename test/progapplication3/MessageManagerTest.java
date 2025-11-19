/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package  progapplication3;

import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MessageManager class
 * @author RC_Student_Lab
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageManagerTest {

    private MessageManager manager;

    @BeforeAll
    public void initAll() {
        // Could initialize shared resources if needed
    }

    @AfterAll
    public void tearDownAll() {
        // Cleanup after all tests
    }

    @BeforeEach
    public void setUp() {
        manager = new MessageManager();
    }

    @AfterEach
    public void tearDown() {
        // Cleanup after each test
    }

    @Test
    public void testStartMessaging() {
        // GUI-driven method; not practical for automated testing
        assertDoesNotThrow(() -> manager.startMessaging());
    }

    @Test
    public void testDisplaySendersAndRecipients() {
        assertDoesNotThrow(() -> manager.displaySendersAndRecipients());
    }

    @Test
    public void testDisplayLongestMessage() {
        assertDoesNotThrow(() -> manager.displayLongestMessage());
    }

    @Test
    public void testSearchByID() {
        assertDoesNotThrow(() -> manager.searchByID());
    }

    @Test
    public void testSearchByRecipient() {
        assertDoesNotThrow(() -> manager.searchByRecipient());
    }

    @Test
    public void testDeleteByHash() {
        assertDoesNotThrow(() -> manager.deleteByHash());
    }

    @Test
    public void testDisplayReport() {
        assertDoesNotThrow(() -> manager.displayReport());
    }

    @Test
    public void testLoadStoredMessagesJSON() {
        assertDoesNotThrow(() -> manager.loadStoredMessagesJSON());
        ArrayList<Message> stored = manager.getStoredMessages();
        assertNotNull(stored, "Stored messages should not be null");
    }

    @Test
    public void testAppendMessageToJSON() {
        Message msg = new Message("+27643437181", "Test JSON append", 1);
        assertDoesNotThrow(() -> manager.appendMessageToJSON(msg));
    }

    @Test
    public void testGetSentMessages() {
        ArrayList<Message> sent = manager.getSentMessages();
        assertNotNull(sent, "Sent messages should not be null");
    }

    @Test
    public void testGetStoredMessages() {
        ArrayList<Message> stored = manager.getStoredMessages();
        assertNotNull(stored, "Stored messages should not be null");
    }
}
