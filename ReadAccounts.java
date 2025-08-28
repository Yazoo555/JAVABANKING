
import java.io.*;
import java.util.LinkedList;

public class ReadAccounts {
    private BufferedReader reader;
    private String url;

    public ReadAccounts(String URL) {
        this.url = URL;
    }

    public LinkedList<String> getFirstNames() {
        LinkedList<String> firstNames = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                firstNames.add(parts[0].trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstNames;
    }

    public LinkedList<String> getLastNames() {
        LinkedList<String> lastNames = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                lastNames.add(parts[1].trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() {
        LinkedList<Integer> accounts = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                accounts.add(Integer.parseInt(parts[2].trim()));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public LinkedList<Integer> getBalances() {
        LinkedList<Integer> balances = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                balances.add(Integer.parseInt(parts[3].trim()));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balances;
    }
}
