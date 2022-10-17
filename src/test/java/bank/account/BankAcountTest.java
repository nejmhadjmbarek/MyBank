package bank.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import bank.account.model.Account;
import bank.account.model.Client;

public class BankAcountTest {
    private final static int NUM_THREADS    = 10;
    private final static int NUM_ITERATIONS = 10;
    private final static String EXPECTED_MESSAGE_POSTIVE_AMOUNT="amount must be a positive number";
    private final static String EXPECTED_MESSAGE_NotAuthorizedBalance="Your balance is less than";

    @Test
    public void testDepositMethod() throws Exception {
        Account account = new Account(1, 100l, new Client(1, "Nejmeddine Elhadjmbarek"));
        account.deposit(100);
        assertEquals(account.getBalance(), 200);
    }

    @Test
    public void testDepositMethodWhenExceptionThrown_() {
        Account account = new Account(1, 100l, new Client(1, "Nejmeddine Elhadjmbarek"));

        Exception exception = assertThrows(Exception.class, () -> {
            account.deposit(-1);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(EXPECTED_MESSAGE_POSTIVE_AMOUNT));
    }

    @Test
    public void testWithdrawalMethod() throws Exception {
        Account account = new Account(1, 100l, new Client(1, "Nejmeddine Elhadjmbarek"));
        account.withdrawal(100);
        assertEquals(account.getBalance(), 0);
    }
    
    
    @Test
    public void testWithdrawalMethodWhenExceptionThrown_() {
        Account account = new Account(1, 100l, new Client(1, "Nejmeddine Elhadjmbarek"));

        Exception exceptionPostiveAmount = assertThrows(Exception.class, () -> {
            account.withdrawal(-1);
        });
        String actualMessageExceptionPostiveAmount = exceptionPostiveAmount.getMessage();
        assertTrue(actualMessageExceptionPostiveAmount.contains(EXPECTED_MESSAGE_POSTIVE_AMOUNT));
        
        
        
        Exception exceptionAmoountNotAuthorizedBalance = assertThrows(Exception.class, () -> {
            account.withdrawal(101);
        });
        
        String actualMessageExceptionNotAuthorizedBalance = exceptionAmoountNotAuthorizedBalance.getMessage();
        assertTrue(actualMessageExceptionNotAuthorizedBalance.contains(EXPECTED_MESSAGE_NotAuthorizedBalance));
        
    }
    
    
   

    @Test
    public void testSynchronizedWithdrawal() throws Exception {
        // This test will likely perform differently on different platforms.
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        long sum = (NUM_THREADS * ((NUM_ITERATIONS * (NUM_ITERATIONS - 1) / 2)));
        final Account account = new Account(1, sum, new Client(1, "DJo"));

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < NUM_ITERATIONS; i++) {
                        try {
                            account.withdrawal(i);
                        } catch (Exception e) {

                        }
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(account.getBalance(), 0);

    }

    @Test
    public void testSynchronizedDeposit() throws Exception {
        // This test will likely perform differently on different platforms.
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        final Account account = new Account(1, 0l, new Client(1, "nejmeddine hadjmbarek"));
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < NUM_ITERATIONS; i++) {
                        try {
                            account.deposit(i);
                        } catch (Exception e) {

                        }
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(account.getBalance(), NUM_THREADS * ((NUM_ITERATIONS * (NUM_ITERATIONS - 1) / 2)));

    }

}
