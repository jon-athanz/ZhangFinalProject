import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainGUIWindow extends JFrame {
    private JFrame mainFrame;
    private JFrame notesFrame;
    private JTextArea noteTextArea;
    private JLabel title;
    private Map<String, String> notesMap;
    private JPanel West;

    public MainGUIWindow() {
        notesMap = new HashMap<>();

        // Main GUI window
        mainFrame = new JFrame("Note Taking Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        title = new JLabel();
        title.setText("Note Taking 101");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(new Color(0));
        title.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JButton addButton = new JButton("Add Note");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNotesWindow();
            }
        });
        mainFrame.add(addButton, BorderLayout.WEST);
        mainFrame.add(title, BorderLayout.NORTH);

        JButton viewButton = new JButton("View Notes");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNotes();
            }
        });
        mainFrame.add(viewButton, BorderLayout.EAST);

        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
    }

    private void openNotesWindow() {
        // Notes GUI window
        notesFrame = new JFrame("Notes");
        notesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        notesFrame.setLayout(new BorderLayout());

        noteTextArea = new JTextArea();
        notesFrame.add(noteTextArea, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Note");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNote();
            }
        });
        notesFrame.add(saveButton, BorderLayout.SOUTH);

        notesFrame.pack();
        notesFrame.setSize(400, 400);
        notesFrame.setVisible(true);
    }

    private void viewNotes() {
        String[] noteTitles = notesMap.keySet().toArray(new String[0]);
        if (noteTitles.length == 0) {
            JOptionPane.showMessageDialog(mainFrame, "No notes available.", "View Notes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String selectedNoteTitle = (String) JOptionPane.showInputDialog(mainFrame, "Select a note to view:", "View Notes",
                    JOptionPane.QUESTION_MESSAGE, null, noteTitles, noteTitles[0]);

            if (selectedNoteTitle != null) {
                String selectedNoteContent = notesMap.get(selectedNoteTitle);
                JOptionPane.showMessageDialog(mainFrame, selectedNoteContent, selectedNoteTitle, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    private void saveNote() {
        String noteTitle = JOptionPane.showInputDialog(mainFrame, "Enter a title for the note:");
        String noteContent = noteTextArea.getText();
        notesMap.put(noteTitle, noteContent);
        notesFrame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUIWindow();
            }
        });
    }
}