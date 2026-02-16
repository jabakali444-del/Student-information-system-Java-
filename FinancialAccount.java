
public class FinancialAccount {
    private double balance;

    public FinancialAccount() {
        this.balance = 0;
    }

    public void chargeCourse(Course course) {
        double cost = course.getName().toLowerCase().contains("lab") ? 170 : 510;
        balance += cost;
    }

    public double getBalance() {
        return balance;
    }
}

