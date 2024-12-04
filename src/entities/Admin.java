package src.entities;

import java.io.Serializable;

public class Admin extends Person {
    private int workingHours;
    private String role;
    private int salary;

  public Admin(String name, int age, Gender gender, String address, String phone, String username, String password) {
      super(name, age, gender, address, phone, username,password);
  }
  public Admin() {
      super();
  }
  public Admin(String name, String username, String password) {
      super(name,username,password);
  }
  public int getWorkingHours() {
      return workingHours;
  }
  public void setWorkingHours(int workingHours) {
      this.workingHours = workingHours;
  }
  public String getRole() {
      return role;
  }
  public void setRole(String role) {
      this.role = role;
  }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

