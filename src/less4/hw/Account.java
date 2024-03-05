package less4.hw;

import java.util.Scanner;

public class Account {
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }
        this.balance = initialBalance;
    }

    public static double getUserInput (String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    public void withdrawFundsManually() {
        double amount = getUserInput("Введите сумму для снятия средств");
        try {
            withdraw(amount);
            System.out.println("Средства успешно сняты");
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage() + ". Текущий баланс: " + e.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void displayBalance(Account account) {
        System.out.println("Итоговый баланс счета: $" + account.getBalance());
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > this.balance) {
            throw new InsufficientFundsException("Недостаточно средств", this.balance);
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
