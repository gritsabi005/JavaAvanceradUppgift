import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Calendar implements ActionListener{
    private JFrame frame;
    private JPanel[] dayPanels = new JPanel[7];
    private JLabel[] dateLabels = new JLabel[7];
    private JLabel[] sizeableEventsLabels = new JLabel[7];
    private JTextField[] eventTextFields = new JTextField[7];
    private JButton[] createEventButtons = new JButton[7];
    private String[] dayNames = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

    public Calendar() {
        frame = new JFrame("Mini Calendar");
        frame.setSize(1000, 800);
        frame.setLayout(new GridLayout(1, 7, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        for (int i = 0; i < 7; i++) {
            dayPanels[i] = new JPanel();
            dayPanels[i].setLayout(new BorderLayout());
            dayPanels[i].setBorder(new EmptyBorder(7, 7, 7, 7));
            frame.add(dayPanels[i]);

            dateLabels[i] = new JLabel(dayNames[i]); dayPanels[i].add(dateLabels[i], BorderLayout.NORTH);

            sizeableEventsLabels[i] = new JLabel(); dayPanels[i].add(sizeableEventsLabels[i], BorderLayout.CENTER);

            JPanel textAndButtonPanel = new JPanel(new BorderLayout()); dayPanels[i].add(textAndButtonPanel, BorderLayout.SOUTH);

            eventTextFields[i] = new JTextField(); textAndButtonPanel.add(eventTextFields[i], BorderLayout.NORTH);

            createEventButtons[i] = new JButton("Skapa"); textAndButtonPanel.add(createEventButtons[i], BorderLayout.SOUTH);

            createEventButtons[i].addActionListener(this);
        }

        Days setDates = new Days();
        setDates.getThisWeekDate(dayPanels, dateLabels);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 7; i++) {
            if (e.getSource() == createEventButtons[i]) {
                String eventText = eventTextFields[i].getText();
                if (!eventText.isEmpty()) {
                    String existingText = sizeableEventsLabels[i].getText().isEmpty() ? "" : sizeableEventsLabels[i].getText() + "<br>";
                    sizeableEventsLabels[i].setText("<html><div style='text-align: center; width: 100%; word-wrap: break-word;'>" + existingText + eventText + "</div>");
                }
                eventTextFields[i].setText("");
            }
        }
        frame.revalidate();
        frame.repaint();
    }
}
