import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    JComboBox<String> sortComboBox;
    JButton processButton;
    JTextArea logArea;

    public View() {
        super("Обробка студентів");

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Виберіть тип сортування:"));
        sortComboBox = new JComboBox<>(new String[]{
                "За Mark, Group, Name",
                "За Group, Name, Mark"
        });
        topPanel.add(sortComboBox);

        processButton = new JButton("Обробити файли");
        topPanel.add(processButton);

        add(topPanel, BorderLayout.NORTH);

        logArea = new JTextArea(15, 50);
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void log(String message) {
        logArea.append(message + "\n");
    }

    public int getSelectedSortType() {
        return sortComboBox.getSelectedIndex() + 1;
    }
}
