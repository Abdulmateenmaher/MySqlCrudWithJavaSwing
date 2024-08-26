package crud.entity;

public class Department {
    private int id;
    private String name;
    private  String description;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.description = name;
    }

    public Department() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override

    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", description=" + description + ", tazkira No=" + description + "]";
    }

}
