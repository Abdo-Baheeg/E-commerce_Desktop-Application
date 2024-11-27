package src.entities;

public class Customer extends Person {
    private float balance;
    protected static int numberOfUsers=0;
    public Customer(String name, int age, Gender gender, String address, String phone, String email){
        super(name, age, gender, address, phone, email);
        numberOfUsers++ ;
    }

    public Customer(String username, String password, String dateOfBirth, double balance, String address, String gender) {
        super();
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public int getNumberOfUsers() {
        return numberOfUsers;
    }
}
