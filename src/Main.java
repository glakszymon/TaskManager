import Models.Converter;
import Models.DataComunicator;
import Models.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Task> tasks = new ArrayList<>();
        for(int i = 1; i <= 4; ++i)
        {
            var t = new Task(i, "task "+i, false);
            tasks.add(t);
        }

        var con = new Converter();
        var file = con.TaskListToString(tasks);
        var db = new DataComunicator();
        db.SaveFile(file);

        var s = db.ReadTasks();
        System.out.print(s);

        var t = con.StringToTasksList(s);
    }

    private void AddAction()
    {

    }
}