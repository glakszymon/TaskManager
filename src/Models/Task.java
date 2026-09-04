package Models;

public class Task {
    public int Id;
    public String Name;
    public boolean Status;

    public Task(int id, String name, boolean status)
    {
        Id = id;
        Name = name;
        Status = status;
    }
}
