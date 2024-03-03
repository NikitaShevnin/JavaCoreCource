package Less3.hw;

public class Freelancer extends Employee {
    private double hourlyRate;

    public Freelancer(String name, double hourlyRate) {
        super(name);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateAverageMonthlySalary() {
        return 20.8 * 8 * hourlyRate;
    }
}
