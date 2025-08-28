
import java.io.*;
import java.util.LinkedList;

public class WriteAccounts {
    private String filePath;

    public WriteAccounts(String filePath) {
        this.filePath = filePath;
    }

    public void saveAccounts(LinkedList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Account acc : accounts) {
                String line = acc.getFirstName() + "," +
                              acc.getLastName() + "," +
                              acc.getAccountNum() + "," +
                              acc.getBalance();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
