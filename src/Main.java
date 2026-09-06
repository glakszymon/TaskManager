import Models.Converter;
import Models.DataComunicator;
import Models.Task;

void main(String[] args) throws IOException {
    if (args.length == 0) {
        IO.println("Użycie: task-manager <komenda> [opcje]");
        return;
    }

    String command = args[0];

    switch (command) {
        case "add":
            handleAdd(args);
            break;
        case "update":
            handleUpdate(args);
            break;
        case "delete":
            handleDelete(args);
            break;
        case "mark-in-progress":
            handleInProgres(args);
            break;
        case "mark-done":
            handleDone(args);
            break;
        case "list":
            handleList(args);
            break;
        case "--help":
            handleHelp();
            break;
        default:
            IO.println("Nieznana komenda: " + command);
    }
}

private static void handleHelp()
{
    IO.println("Task Manager");
    IO.println("Użycie: task-manager <komenda> [opcje]");
    IO.println("Commands:");
    IO.println("add \"[task name]\" | Dodaj nowy rekord");
    IO.println("update [task id] \"[task new name]\" | Aktualizuj rekord o podanym id na nowa nazwe");
    IO.println("delete [task id] | Usuń rekord o podanym id");
    IO.println("mark-in-progress [task id] | Zmień status na in-progress rekordu o podanym id");
    IO.println("mark-done [task id] | Zmień status na done rekordu o podanym id");
    IO.println("list | Wypisz wszystkie rekordy");
    IO.println("list done | Wypisz rekordy z statusem done");
    IO.println("list in-progress | Wypsz wszystkie rekordy z statusem in-progress");
}

private static void handleDone(String[] args) throws IOException {
    var tasks = Load();
    var id = Integer.parseInt(args[1]);
    tasks.get(id).Status = "done";
    Save(tasks);
}

private static void handleInProgres(String[] args) throws IOException {
    var tasks = Load();
    var id = Integer.parseInt(args[1]);
    tasks.get(id).Status = "in-progress";
    Save(tasks);
}

private static void handleList(String[] args) {
    var tasks = Load();

    if (args.length < 2) {
        for (Task t : tasks) {
            IO.println(t.Id + ": --Name: " + t.Name + " --Status: " + t.Status);
        }
        return;
    }

    String statusFilter = args[1];
    for (Task t : tasks) {
        if (Objects.equals(t.Status, statusFilter)) {
            IO.println(t.Id + ": --Name: " + t.Name + " --Status: " + t.Status);
        }
    }
}

private static void handleUpdate(String[] args) throws IOException {
    var tasks = Load();

    int id = Integer.parseInt(args[1]);
    tasks.get(id).Name = args[2];

    Save(tasks);

    IO.println("Updated task (ID= " + id + ")");
}

private static void handleDelete(String[] args) throws IOException {
    var tasks = Load();
    int id = Integer.parseInt(args[1]);
    tasks.remove(id);

    for (int i = 0; i < tasks.size(); ++i) {
        tasks.get(i).Id = i;
    }

    Save(tasks);
    IO.println("Deleted task (ID=" + id + ")");
}

private static void handleAdd(String[] args) throws IOException {
    var tasks = Load();

    String name = args[1];
    var record = new Task();
    record.Name = name;
    record.Status = "todo";
    record.Id = tasks.getLast().Id + 1;

    tasks.add(record);
    Save(tasks);

    IO.println("Task added successfully (ID: " + record.Id + ")");
}

private static List<Task> Load() {
    var db = new DataComunicator();
    var converter = new Converter();
    var s = db.ReadTasks();

    return converter.StringToTasksList(s);
}

private static void Save(List<Task> tasks) throws IOException {
    var db = new DataComunicator();
    var converter = new Converter();

    var e = converter.TaskListToString(tasks);
    db.SaveFile(e);
}