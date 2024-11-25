package source.people;

public class Admin extends Person {
    private String workingHours;
    private String role;

  Admin(String name, int age, Gender gender, String address, String phone, String email){
      super(name, age, gender, address, phone, email);
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

