package src.entities;

public class Admin extends Person {
    private String workingHours;
    private String role;

  public Admin(String name, int age, Gender gender, String address, String phone, String username, String password) {
      super(name, age, gender, address, phone, username,password);
  }
  public String getWorkingHours() {
      return workingHours;
  }
  public void setWorkingHours(String workingHours) {
      this.workingHours = workingHours;
  }
  public String getRole() {
      return role;
  }
  public void setRole(String role) {
      this.role = role;
  }

}

