public class Employee {

    public Employee() {
    }

    public Employee(String name, int salary, String dob) {
        this.name = name;
        this.salary = salary;
        this.dob = dob;
    }

    String name;
    private int age;
    private int salary;
    private String dob;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
        // functionToCalculateAge(dob);
    }

    public  static void main(String[] args){
        Employee emp = new Employee("John Doe", 50000, "1993-01-01");
        emp.salary = 60000; // Direct access to salary within the class
        System.out.println("Employee Name: " + emp.getName());
        System.out.println("Employee Age: " + emp.getAge());
        // System.out.println("Employee Salary: " + emp.getSalary());
        System.out.println("Employee DOB: " + emp.getDob());
    }
}
