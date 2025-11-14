import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Message> messages = new ArrayList<>();

        BoardMessage b1 = new BoardMessage("Friend1", "Important update", "2025-05-01", "Students", "URGENT","Exam update");
        BoardMessage b2 = new BoardMessage("Friend2","BBQ on friday","Class","BBQ");

        messages.add(b1);
        messages.add(b2);

        ArrayList<File> filelist = new ArrayList<>();
        filelist.add(new File("report", "pdf"));
        filelist.add(new File("image", "jpg"));

        EmailMessage e1 = new EmailMessage("Teacher", "Exam solution","2025-04-15","Student","Solution",filelist);
        EmailMessage e2 = new EmailMessage("Friend3","Let's meet tomorrow", "Me","Meet up");

        messages.add(e1);
        messages.add(e2);

        try {
            SmsMessage s1 = new SmsMessage("Bank","Your code is 1234","Me","123456789");
            SmsMessage s2 = new SmsMessage("Dad","I went to get milk","Me", "987654321");

            messages.add(s1);
            messages.add(s2);
            
        } catch (PhoneNumberException ex) {
            System.out.println(ex.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("1. Add message");
            System.out.println("2. Delete message");
            System.out.println("3. Print all messages");
            System.out.println("4. Show number of messages that contain given words");
            System.out.println("5. Print only digital messages");
            System.out.println("6. Show preview of all messages");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMessage(messages, scanner);
                    break;
                case 2:
                    deleteMessage(messages, scanner);
                    break;
                case 3:
                    printAllMessages(messages);
                    break;
                case 4:
                    countMessagesWithWords(messages, scanner);
                    break;
                case 5:
                    printDigitalMessages(messages);
                    break;
                case 6:
                    printPreviews(messages);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void addMessage(ArrayList<Message> messages, Scanner scanner) {
        System.out.println("1. BoardMessage");
        System.out.println("2. EmailMessage");
        System.out.println("3. SmsMessage");
        System.out.print("Your choice: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid option");
            scanner.nextLine();
            return;
        }

        int type = scanner.nextInt();
        scanner.nextLine();

        try {
            System.out.print("Sender: ");
            String sender = scanner.nextLine();
            System.out.print("Content: ");
            String content = scanner.nextLine();
            System.out.print("Receiver: ");
            String receiver = scanner.nextLine();

            switch (type) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Priority (LOW / REGULAR / URGENT): ");
                    String pri = scanner.nextLine();
                    BoardMessage bm = new BoardMessage(sender, content, receiver, title);
                    if (pri != null && !pri.isBlank()) {
                        bm.setPriority(pri);
                    }
                    messages.add(bm);
                    System.out.println("BoardMessage added");
                    break;

                case 2: 
                    System.out.print("Subject: ");
                    String sub = scanner.nextLine();
                    EmailMessage em = new EmailMessage(sender, content, receiver, sub);
                    messages.add(em);
                    System.out.println("EmailMessage added");
                    break;

                case 3: 
                    System.out.print("Phone number: ");
                    String phone = scanner.nextLine();
                    SmsMessage sm = new SmsMessage(sender, content, receiver, phone);
                    messages.add(sm);
                    System.out.println("SmsMessage added");
                    break;

                default:
                    System.out.println("Unknown type");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating message: " + e.getMessage());
        } catch (PhoneNumberException e) {
            System.out.println("Error with phone number: " + e.getMessage());
        }
    }

    private static void deleteMessage(ArrayList<Message> messages, Scanner scanner) {
        if (messages.isEmpty()) {
            System.out.println("No messages to delete");
            return;
        }

        System.out.println("Current messages:");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println(i + ": " + messages.get(i).generatePreview());
        }

        System.out.print("Enter index to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid option");
            scanner.nextLine();
            return;
        }

        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx < 0 || idx >= messages.size()) {
            System.out.println("Index out of range.");
        } else {
            messages.remove(idx);
            System.out.println("Message removed.");
        }
    }

    private static void printAllMessages(ArrayList<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
            return;
        }

        for (Message m : messages) {
            System.out.println("-----");
            System.out.println(m.toString());
        }
    }

    private static void countMessagesWithWords(ArrayList<Message> messages, Scanner scanner) {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
            return;
        }

        System.out.print("Enter words separated by spaces: ");
        String line = scanner.nextLine();
        if (line == null || line.isBlank()) {
            System.out.println("No words given.");
            return;
        }

        String[] parts = line.split("\\s+");
        ArrayList<String> words = new ArrayList<>();
        for (String w : parts) {
            words.add(w);
        }

        int count = 0;
        for (Message m : messages) {
            if (m.find(words)) {
                count++;
            }
        }

        System.out.println("Number of messages that contain at least one of the words: " + count);
    }

    private static void printDigitalMessages(ArrayList<Message> messages) {
        boolean any = false;

        for (Message m : messages) {
            if (m instanceof IDigital) {
                any = true;
                System.out.println("-----");
                System.out.println(m.toString());
                System.out.println(((IDigital) m).printCommunicationMethod());
            }
        }

        if (!any) {
            System.out.println("No digital messages.");
        }
    }

    private static void printPreviews(ArrayList<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("No messages.");
            return;
        }

        for (Message m : messages) {
            System.out.println(m.generatePreview());
        }
    }
}