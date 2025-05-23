import java.io.*;
import java.util.*;

public class Student {
    private int number;
    private String group;
    private String name;
    private double mark;

    public Student(int number, String group, String name, double mark) {
        this.number = number;
        this.group = group;
        this.name = name;
        this.mark = mark;
    }

    public int getNumber() { return number; }
    public String getGroup() { return group; }
    public String getName() { return name; }
    public double getMark() { return mark; }

    @Override
    public String toString() {
        return number + " " + group + " " + name + " " + mark;
    }
}

class StudentDataManager {
    private List<Student> students = new ArrayList<>();

    public void loadFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\s+");
            if (parts.length < 4) continue;
            try {
                int number = Integer.parseInt(parts[0]);
                String group = parts[1];
                String name = parts[2];
                double mark = Double.parseDouble(parts[3]);
                students.add(new Student(number, group, name, mark));
            } catch (NumberFormatException e) {
            }
        }
        br.close();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void writeToFile(String filePath, List<Student> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Student s : list) {
            bw.write(s.toString());
            bw.newLine();
        }
        bw.close();
    }

    public Map<String, List<Student>> splitByMark() {
        Map<String, List<Student>> map = new HashMap<>();
        map.put("high", new ArrayList<>());
        map.put("medium", new ArrayList<>());
        map.put("low", new ArrayList<>());

        for (Student s : students) {
            if (s.getMark() >= 4.5) {
                map.get("high").add(s);
            } else if (s.getMark() >= 4.0) {
                map.get("medium").add(s);
            } else {
                map.get("low").add(s);
            }
        }
        return map;
    }

    public void sortList(List<Student> list, int sortType) {
        if (sortType == 1) {
            list.sort(Comparator
                    .comparingDouble(Student::getMark).reversed()
                    .thenComparing(Student::getGroup)
                    .thenComparing(Student::getName));
        } else if (sortType == 2) {
            list.sort(Comparator
                    .comparing(Student::getGroup)
                    .thenComparing(Student::getName)
                    .thenComparingDouble(Student::getMark).reversed());
        }
    }
}
