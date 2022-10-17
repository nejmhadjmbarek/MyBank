package bank.account.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private int           idBank;
    private List<Account> acounts;

    public Bank(int idBank) {
        super();
        this.idBank = idBank;
        acounts = new ArrayList<Account>();
    }

    public void addAccount(Account account) {
        if (account != null) {
            acounts.add(account);

        }
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public List<Account> getAcounts() {
        return acounts;
    }

    public void setAcounts(List<Account> acounts) {
        this.acounts = acounts;
    }

}
