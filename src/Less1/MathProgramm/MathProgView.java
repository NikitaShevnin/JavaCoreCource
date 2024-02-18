package Less1.MathProgramm;

import Less1.MathProgramm.Math.Addition;
import Less1.MathProgramm.Math.MathOperations;
import java.util.Scanner;

public class MathProgView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        int sum = Addition.add(a, b);
        int difference = MathOperations.subtract(a, b);
        int multiplication = MathOperations.Multiplication(a, b);
        int division = MathOperations.division(a, b);

        System.out.println("Результаты вычислений: ");
        System.out.println("Сумма чисел: " + sum);
        System.out.println("Разность чисел: " + difference);
        System.out.println("Умножение чисел: " + multiplication);
        System.out.println("Вычитание чисел: " + division);
    }
}
