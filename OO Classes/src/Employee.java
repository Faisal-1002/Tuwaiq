public class Employee {

    private String id;
    private String name;
    private double salary;

    public Employee(String id, String name, double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getSalary(){
        return salary;
    }
    public double getAnnualSalary(){
        return getSalary()*12;
    }
    public double raisedSalary(double percent){
        salary = salary + (salary*(percent/100));
        return salary;
    }
    public String toString(){
        return ("ID : " + id + ", Name : " + name + ", Balance : " + salary);
    }
}
