package bank.account.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private int             idAccount;
    private long            balance;
    private Client          client;
    private List<Statement> statementHistory;

    public Account(int idAccount, Long balance, Client client) {
        super();
        this.idAccount = idAccount;
        this.balance = balance;
        this.client = client;
        this.statementHistory = new ArrayList<Statement>();
    }

    // method to withdraw money
    public synchronized void  withdrawal(long amount) throws Exception {
        if (amount < 0)
            throw new Exception("amount must be a positive number");
        System.out.println("withdraw  from  account number : " + this.idAccount + " amount: " + amount);
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println("Balance after withdrawal for this account is : " + balance);
            Statement transaction = new Statement(new Date(), amount, this.balance, StatementType.WITHDRAWAL);
            statementHistory.add(transaction);
        } else {
            throw new Exception("Your balance is less than" + amount + "Transaction failed...!!");

        }
    }

    // method to deposit money
    public synchronized void deposit(long amount) throws Exception {

        if (amount < 0)
            throw new Exception("amount must be a positive number");

        System.out.println("deposit from  account number :  " + this.idAccount + " amount =" + amount);
        balance = balance + amount;
        Statement statement = new Statement(new Date(), amount, this.balance, StatementType.DEPOSITE);
        statementHistory.add(statement);
        System.out.println("Balance after deposit for this account is : " + balance);

    }

    // method to deposit money
    public void printHistory() {
        System.out.println("print History  for the account with  number :  " + this.idAccount);

        for (int i = 0; i < this.statementHistory.size(); i++) {
            System.out.println("**********************************************************");
            System.out.println("Statement Type  = " + statementHistory.get(i).getType());
            System.out.println("Statement Date  = " + statementHistory.get(i).getDate());
            System.out.println("Balance = " + statementHistory.get(i).getBalance());
            System.out.println("Amount = " + statementHistory.get(i).getAmount());
            System.out.println("**********************************************************");

        }

    }

    public int getIdAccount() {
        return idAccount;
    }

    public long getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public List<Statement> getTransactionHistory() {
        return statementHistory;
    }

    public void setTransactionHistory(List<Statement> transactionHistory) {
        this.statementHistory = transactionHistory;
    }

}
