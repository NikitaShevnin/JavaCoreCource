package Less3.hw;

public class OfficeWorker extends Employee {
    private double fixedMonthlySalary;

    public OfficeWorker(String name, double fixedMonthlySalary) {
        super(name);
        this.fixedMonthlySalary = fixedMonthlySalary;
    }

    @Override
    public double calculateAverageMonthlySalary() {
        return fixedMonthlySalary;
    }
}
