package bank.account.model;

import java.util.Date;

public class Statement {
    private Date   date;
    private long   amount;
    private long   balance;
    private StatementType type;

    public Statement( Date date, long amount, long balance, StatementType type) {
        super();
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

   

    public StatementType getType() {
        return type;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

}
