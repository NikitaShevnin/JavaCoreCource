package Less4.hw;

import static Less4.hw.Account.displayBalance;
import static Less4.hw.Account.getUserInput;

public class Main {
    public static void main(String[] args) {

        double initialBalance = getUserInput("Введите начальный баланс счета");
        Account account = new Account(initialBalance);
        account.withdrawFundsManually();
        displayBalance(account);

        try {
            account.deposit(2500);
            System.out.println("Баланс счета после депозита: " + account.getBalance());
            account.withdraw(2000);
            System.out.println("Баланс счета после снятия средств: " + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage() + ". Текущий баланс: " + e.getBalance());
        }
    }
}
