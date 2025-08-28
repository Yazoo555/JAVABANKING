
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class GUI extends JFrame {
    private Transaction transferObject;
    private StringBuilder sbAllData;
    private LinkedList<Account> globalAccounts;
    private WriteAccounts writer;
    private String csvPath;

    private JLabel showAllData;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField accDeposit, depositInput;
    private JTextField accWithdraw, withdrawInput;
    private JTextField acc1Transfer, acc2Transfer, transferAmount;

    public GUI(LinkedList<Account> accounts, String filePath) {
        super("Banking System");
        setLayout(null);

        this.globalAccounts = accounts;
        this.csvPath = filePath;
        this.writer = new WriteAccounts(filePath);
        this.transferObject = new Transaction();
        this.sbAllData = new StringBuilder();

        // ===== Show All Section =====
        showAllData = new JLabel("<Account data appears here>");
        showAllData.setBounds(30, 30, 800, 100);

        showAllButton = new JButton("Show All Accounts");
        showAllButton.setBounds(30, 140, 200, 30);

        // ===== Deposit Section =====
        JLabel depositLabel = new JLabel("Deposit");
        depositLabel.setBounds(30, 190, 100, 25);

        accDeposit = new JTextField("Enter Account #");
        accDeposit.setBounds(30, 220, 150, 25);

        depositInput = new JTextField("Enter Amount");
        depositInput.setBounds(190, 220, 150, 25);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(350, 220, 100, 25);

        // ===== Withdraw Section =====
        JLabel withdrawLabel = new JLabel("Withdraw");
        withdrawLabel.setBounds(30, 260, 100, 25);

        accWithdraw = new JTextField("Enter Account #");
        accWithdraw.setBounds(30, 290, 150, 25);

        withdrawInput = new JTextField("Enter Amount");
        withdrawInput.setBounds(190, 290, 150, 25);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(350, 290, 100, 25);

        // ===== Transfer Section =====
        JLabel transferLabel = new JLabel("Transfer");
        transferLabel.setBounds(30, 330, 100, 25);

        acc1Transfer = new JTextField("From Account #");
        acc1Transfer.setBounds(30, 360, 130, 25);

        acc2Transfer = new JTextField("To Account #");
        acc2Transfer.setBounds(170, 360, 130, 25);

        transferAmount = new JTextField("Amount");
        transferAmount.setBounds(310, 360, 100, 25);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(420, 360, 100, 25);

        // ===== Add Components =====
        add(showAllData); add(showAllButton);
        add(depositLabel); add(accDeposit); add(depositInput); add(depositButton);
        add(withdrawLabel); add(accWithdraw); add(withdrawInput); add(withdrawButton);
        add(transferLabel); add(acc1Transfer); add(acc2Transfer); add(transferAmount); add(transferButton);

        // ===== Action Listeners =====
        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
    }

    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showAllButton) {
                StringBuilder sb = new StringBuilder();
                for (Account acc : globalAccounts) {
                    sb.append(acc.getFirstName()).append(" ")
                      .append(acc.getLastName()).append(" - Acc#")
                      .append(acc.getAccountNum()).append(" - Balance: ")
                      .append(acc.getBalance()).append("<br>");
                }
                showAllData.setText("<html>" + sb.toString() + "</html>");
            }

            if (e.getSource() == depositButton) {
                try {
                    int accNum = Integer.parseInt(accDeposit.getText().trim());
                    int amt = Integer.parseInt(depositInput.getText().trim());
                    for (Account acc : globalAccounts) {
                        if (acc.getAccountNum() == accNum) {
                            acc.deposit(amt);
                            writer.saveAccounts(globalAccounts);
                            break;
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid deposit input.");
                }
            }

            if (e.getSource() == withdrawButton) {
                try {
                    int accNum = Integer.parseInt(accWithdraw.getText().trim());
                    int amt = Integer.parseInt(withdrawInput.getText().trim());
                    for (Account acc : globalAccounts) {
                        if (acc.getAccountNum() == accNum) {
                            if (acc.getBalance() >= amt) {
                                acc.withdraw(amt);
                                writer.saveAccounts(globalAccounts);
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient funds.");
                            }
                            break;
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid withdraw input.");
                }
            }

            if (e.getSource() == transferButton) {
                try {
                    int from = Integer.parseInt(acc1Transfer.getText().trim());
                    int to = Integer.parseInt(acc2Transfer.getText().trim());
                    int amt = Integer.parseInt(transferAmount.getText().trim());

                    Account accFrom = null, accTo = null;

                    for (Account acc : globalAccounts) {
                        if (acc.getAccountNum() == from) accFrom = acc;
                        if (acc.getAccountNum() == to) accTo = acc;
                    }

                    if (accFrom != null && accTo != null) {
                        if (accFrom.getBalance() >= amt) {
                            transferObject.transfer(accFrom, accTo, amt);
                            writer.saveAccounts(globalAccounts);
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient funds for transfer.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid transfer input.");
                }
            }
        }
    }
}
