package terminalassess;

public class Employee {
    private static int nextId = 1001;
    private String id;
    private String name;
    private String position;
    private String salary; 


    public Employee(String name, String position, String salary) {
        this.id = String.valueOf(nextId++);
        this.name = name;
        this.position = position;
        this.salary = salary;
    }


    public Employee(String id, String name, String position, String salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        try {
            int numericId = Integer.parseInt(id);
            if (numericId >= nextId) nextId = numericId + 1;
        } catch (NumberFormatException ignored) { }
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public String getSalary() { return salary; }

    public void setPosition(String position) { this.position = position; }
    public void setSalary(String salary) { this.salary = salary; }
    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
