import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        StudentDataManager model = new StudentDataManager();

        view.processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.logArea.setText("");
                int sortType = view.getSelectedSortType();

                try {

                    for (int i = 1; i <= 3; i++) {
                        String inputFile = "src/data/students" + i + ".txt";
                        view.log("Зчитування " + inputFile);
                        model.loadFromFile(inputFile);
                    }

                    Map<String, List<Student>> groups = model.splitByMark();

                    String[] outputFiles = {"src/data/high_mark.txt", "src/data/medium_mark.txt", "src/data/low_mark.txt"};
                    int idx = 0;

                    for (String key : new String[]{"high", "medium", "low"}) {
                        List<Student> list = groups.get(key);
                        model.sortList(list, sortType);
                        model.writeToFile(outputFiles[idx], list);
                        view.log("Записано " + outputFiles[idx] + " (" + list.size() + " студентів)");
                        idx++;
                    }
                    view.log("Обробка завершена.");
                } catch (IOException ex) {
                    view.log("Помилка: " + ex.getMessage());
                }
            }
        });
    }
}
