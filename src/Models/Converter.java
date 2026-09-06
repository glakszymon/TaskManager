package Models;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public String TaskListToString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            String taskTemplate = """
                  {
                    "id": %d,
                    "name": "%s",
                    "status": "%s"
                  }""";

            sb.append(taskTemplate.formatted(t.Id, escapeJson(t.Name), escapeJson(t.Status)));

            if (i < tasks.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append("]");
        return sb.toString();
    }

    public List<Task> StringToTasksList(String text) {
        List<Task> result = new ArrayList<>();
        List<String> recordsInString = ExtractRecords(text);

        for (String r : recordsInString) {
            if (!r.isBlank()) {
                result.add(recordToData(r));
            }
        }

        return result;
    }

    private List<String> ExtractRecords(String text) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == '{') {
                StringBuilder temp = new StringBuilder();
                for (int j = i; j < text.length(); ++j) {
                    temp.append(text.charAt(j));
                    if (text.charAt(j) == '}') {
                        i = j;
                        res.add(temp.toString());
                        break;
                    }
                }
            }
        }

        return res;
    }


    private Task recordToData(String rec) {
        int idIndex = rec.indexOf("\"id\":");
        int commaIdIndex = rec.indexOf(",", idIndex);
        String rawId = rec.substring(idIndex + 5, commaIdIndex).trim();
        int id = Integer.parseInt(rawId);

        int nameIndex = rec.indexOf("\"name\":");
        int commaNameIndex = rec.indexOf(",", nameIndex);
        String rawName = rec.substring(nameIndex + 9, commaNameIndex).trim();
        if (rawName.endsWith("\"")) {
            rawName = rawName.substring(0, rawName.length() - 1);
        }

        int statusIndex = rec.indexOf("\"status\":");
        int braceIndex = rec.indexOf("}", statusIndex);
        String rawStatus = rec.substring(statusIndex + 11, braceIndex).trim();
        if (rawStatus.endsWith("\"")) {
            rawStatus = rawStatus.substring(0, rawStatus.length() - 1);
        }

        var t = new Task();
        t.Name = rawName;
        t.Id = id;
        t.Status = rawStatus;
        return t;
    }

    private String escapeJson(String input) {
        return input == null ? "" : input.replace("\"", "\\\"");
    }
}