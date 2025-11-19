/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progapplication3;

/**
 *
 * @author RC_Student_Lab
 */


public class Message {

    private String recipient;
    private String messageText;
    private String messageID;
    private String messageHash;

    public Message(String recipient, String messageText, int totalCount) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = String.format("%010d", totalCount);
        this.messageHash = generateHash();
    }

    public Message(String id, int totalCount, String recipient, String text, String hash) {
        this.messageID = id;
        this.recipient = recipient;
        this.messageText = text;
        this.messageHash = hash;
    }

    private String generateHash() {
        return recipient.hashCode() + ":" + messageText.hashCode();
    }

    // Getters
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }

    public void printMessageDetailsGUI() {
        javax.swing.JOptionPane.showMessageDialog(null,
                "ID: " + messageID + "\nRecipient: " + recipient + "\nMessage: " + messageText + "\nHash: " + messageHash);
    }
}
