package Less3.hw;

abstract class Employee {

    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract double calculateAverageMonthlySalary();
}
