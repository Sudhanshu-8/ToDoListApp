
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToDoListApp {

    public static void main(String[] args) {
        // Initialize the frame
        JFrame frame = new JFrame("To-Do List");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Task list model and list
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Task input field
        JTextField taskInputField = new JTextField();
        taskInputField.setPreferredSize(new Dimension(200, 30));
        // Buttons
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton markButton = new JButton("Mark Completed");
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(markButton);
        // Add components to the frame
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
        frame.add(taskInputField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInputField.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskInputField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Task cannot be empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a task to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        markButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = taskListModel.get(selectedIndex);
                    taskListModel.set(selectedIndex, "[Completed] " + task);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a task to mark as completed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Show the frame
        frame.setVisible(true);
    }
}
