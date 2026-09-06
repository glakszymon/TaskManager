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

private static void handleHelp() {
    IO.println("==================================================");
    IO.println("                TASK MANAGER CLI                  ");
    IO.println("==================================================");
    IO.println("Użycie: java -jar TaskManager.jar <komenda> [argumenty]\n");
    IO.println("Dostępne komendy:");
    IO.println("  add \"[nazwa]\"                - Dodaje nowe zadanie");
    IO.println("  update [id] \"[nowa nazwa]\"   - Modyfikuje nazwę zadania o podanym ID");
    IO.println("  delete [id]                  - Usuwa zadanie o podanym ID");
    IO.println("  mark-in-progress [id]        - Zmienia status zadania na 'in-progress'");
    IO.println("  mark-done [id]               - Zmienia status zadania na 'done'");
    IO.println("  list                         - Wyświetla wszystkie zadania");
    IO.println("  list [status]                - Wyświetla zadania o podanym statusie (np. todo, done)");
    IO.println("  --help                       - Wyświetla tę instrukcję pomocy");
    IO.println("==================================================");
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