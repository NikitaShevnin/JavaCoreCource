package Less3.hw;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];

        employees[0] = new Freelancer("John", 10.5);
        employees[1] = new OfficeWorker("Alice", 5000);
        employees[2] = new OfficeWorker("Bob", 4000);

        for (Employee employee : employees) {
            System.out.println("Employee: " + employee.name);
            System.out.println("Average Monthly Salary: $" + employee.calculateAverageMonthlySalary());
            System.out.println("----");
        }
    }
}
