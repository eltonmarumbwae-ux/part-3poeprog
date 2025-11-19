/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progapplication3;

/**
 *
 * @author RC_Student_Lab
 */


import javax.swing.*;
import java.nio.file.*;
import java.util.ArrayList;

public class MessageManager {

    private int totalMessages = 0;

    private final ArrayList<Message> sentMessages = new ArrayList<>();
    private final ArrayList<Message> storedMessages = new ArrayList<>();
    private final ArrayList<Message> disregardedMessages = new ArrayList<>();

    // === MAIN GUI ENTRY ===
    public void startMessaging() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat Part 3!", "QuickChat", JOptionPane.INFORMATION_MESSAGE);
        part3Menu();
    }

    // === PART 3 MENU ===
    public void part3Menu() {
        while (true) {
            String[] items = {
                    "Send / Disregard / Store Message",
                    "Display Senders & Recipients",
                    "Display Longest Sent Message",
                    "Search by Message ID",
                    "Search by Recipient",
                    "Delete by Message Hash",
                    "Display Sent Messages Report",
                    "Load Stored Messages JSON",
                    "Quit"
            };
            String choice = (String) JOptionPane.showInputDialog(null, "Part 3 Menu:", "QuickChat Part 3",
                    JOptionPane.PLAIN_MESSAGE, null, items, items[0]);
            if (choice == null || choice.equals("Quit")) return;

            switch (choice) {
                case "Send / Disregard / Store Message" -> addNewMessage();
                case "Display Senders & Recipients" -> displaySendersAndRecipients();
                case "Display Longest Sent Message" -> displayLongestMessage();
                case "Search by Message ID" -> searchByID();
                case "Search by Recipient" -> searchByRecipient();
                case "Delete by Message Hash" -> deleteByHash();
                case "Display Sent Messages Report" -> displayReport();
                case "Load Stored Messages JSON" -> loadStoredMessagesJSON();
            }
        }
    }

    // === SEND / STORE / DISREGARD ===
    private void addNewMessage() {
        totalMessages++;
        String recipient = JOptionPane.showInputDialog("Enter recipient (+27xxxxxxxxx):");
        if (recipient == null || !recipient.matches("\\+27\\d{9}")) { JOptionPane.showMessageDialog(null,"Invalid recipient."); return; }

        String text = JOptionPane.showInputDialog("Enter message (<=250 chars):");
        if (text == null || text.length() > 250) { JOptionPane.showMessageDialog(null,"Message too long."); return; }

        Message msg = new Message(recipient, text, totalMessages);

        String[] options = {"Send", "Disregard", "Store"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option for this message:", "Message Action",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> sentMessages.add(msg);
            case 1 -> disregardedMessages.add(msg);
            case 2 -> { storedMessages.add(msg); appendMessageToJSON(msg); }
            default -> JOptionPane.showMessageDialog(null, "No action taken.");
        }

        msg.printMessageDetailsGUI();
    }

    // === DISPLAY ===
    public void displaySendersAndRecipients() {
        if (sentMessages.isEmpty()) { JOptionPane.showMessageDialog(null,"No sent messages."); return; }
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) sb.append("Recipient: ").append(m.getRecipient()).append(" | Message: ").append(m.getMessageText()).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void displayLongestMessage() {
        if (sentMessages.isEmpty()) { JOptionPane.showMessageDialog(null,"No sent messages."); return; }
        Message longest = sentMessages.get(0);
        for (Message m : sentMessages) if (m.getMessageText().length() > longest.getMessageText().length()) longest = m;
        JOptionPane.showMessageDialog(null,"Longest message:\n" + longest.getMessageText());
    }

    public void searchByID() {
        String id = JOptionPane.showInputDialog("Enter Message ID:");
        for (Message m : sentMessages) {
            if (m.getMessageID().equals(id)) {
                JOptionPane.showMessageDialog(null,"Recipient: "+m.getRecipient()+"\nMessage: "+m.getMessageText());
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Message ID not found.");
    }

    public void searchByRecipient() {
        String rec = JOptionPane.showInputDialog("Enter Recipient (+27xxxxxxxxx):");
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) if (m.getRecipient().equals(rec)) sb.append(m.getMessageText()).append("\n");
        for (Message m : storedMessages) if (m.getRecipient().equals(rec)) sb.append(m.getMessageText()).append("\n");
        JOptionPane.showMessageDialog(null, sb.length() == 0 ? "No messages found." : sb.toString());
    }

    public void deleteByHash() {
        String hash = JOptionPane.showInputDialog("Enter Message Hash:");
        boolean removed = sentMessages.removeIf(m -> m.getMessageHash().equals(hash)) || storedMessages.removeIf(m -> m.getMessageHash().equals(hash));
        JOptionPane.showMessageDialog(null, removed ? "Message deleted." : "Hash not found.");
    }

    public void displayReport() {
        if (sentMessages.isEmpty()) { JOptionPane.showMessageDialog(null,"No sent messages."); return; }
        StringBuilder sb = new StringBuilder("=== SENT MESSAGES REPORT ===\n\n");
        for (Message m : sentMessages) {
            sb.append("Hash: ").append(m.getMessageHash()).append("\n");
            sb.append("Recipient: ").append(m.getRecipient()).append("\n");
            sb.append("Message: ").append(m.getMessageText()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // === JSON ===
    public void loadStoredMessagesJSON() {
        try {
            Path path = Paths.get("messages.json");
            String raw = Files.exists(path) ? Files.readString(path) : JSONArray.getSampleJSON();
            raw = raw.replace("[","").replace("]","");
            String[] entries = raw.split("\\},\\s*\\{");
            for(String e: entries){
                e = e.replace("{","").replace("}","").trim();
                String id = safeExtract(e,"MessageID");
                String hash = safeExtract(e,"MessageHash");
                String rec = safeExtract(e,"Recipient");
                String text = safeExtract(e,"Message");
                storedMessages.add(new Message(id, ++totalMessages, rec, text, hash));
            }
        } catch(Exception ex){ JOptionPane.showMessageDialog(null,"JSON load error: "+ex.getMessage()); }
    }

    public void appendMessageToJSON(Message msg){
        try{
            Path path = Paths.get("messages.json");
            ArrayList<Message> all = new ArrayList<>(storedMessages);
            all.add(msg);
            StringBuilder sb = new StringBuilder("[\n");
            for(int i=0;i<all.size();i++){
                Message m = all.get(i);
                sb.append("  {\n");
                sb.append("    \"MessageID\":\"").append(m.getMessageID()).append("\",\n");
                sb.append("    \"MessageHash\":\"").append(m.getMessageHash()).append("\",\n");
                sb.append("    \"Recipient\":\"").append(m.getRecipient()).append("\",\n");
                sb.append("    \"Message\":\"").append(m.getMessageText()).append("\"\n");
                sb.append("  }");
                if(i<all.size()-1) sb.append(",");
                sb.append("\n");
            }
            sb.append("]\n");
            Files.writeString(path,sb.toString());
        }catch(Exception ex){ throw new RuntimeException("JSON append failed: "+ex.getMessage()); }
    }

    private String safeExtract(String block, String key){
        int start = block.indexOf("\""+key+"\":");
        if(start==-1) return "";
        start = start + key.length()+4;
        int end = block.indexOf("\"",start);
        if(end==-1) end = block.length();
        return block.substring(start,end);
    }

    // === GETTERS FOR TESTS ===
    public ArrayList<Message> getSentMessages() { return sentMessages; }
    public ArrayList<Message> getStoredMessages() { return storedMessages; }
}

