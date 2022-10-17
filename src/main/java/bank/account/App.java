package bank.account;

import bank.account.model.Account;
import bank.account.model.Bank;
import bank.account.model.Client;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Bank!");
        Bank myBank = new Bank(1);
        Account account = new Account(1, 100l, new Client(1, "Nejmeddine Elhadjmbarek"));
        myBank.addAccount(account);
        account.deposit(5);
        account.withdrawal(5);
        account.printHistory();

    }
}
