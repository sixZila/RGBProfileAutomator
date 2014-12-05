package rgb.profile.automator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutomatorView extends JFrame {

    private JLabel nodesLabel;
    private JTextField nodesField;
    private JButton startButton;
    private JFileChooser fileChooser;

    public void initialize() {
        JPanel buttonPanel = new JPanel(new GridLayout());
        JPanel inputPanel = new JPanel(new SpringLayout());

        nodesLabel = new JLabel("Number of Nodes (Total - 1): ");
        nodesField = new JTextField("0");
        startButton = new JButton("Start Distributing");
        fileChooser = new JFileChooser();

        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        buttonPanel.setBorder(padding);

        fileChooser.setFileFilter(new FileNameExtensionFilter("Lighting Files (*.lght)", "lght"));
        nodesLabel.setLabelFor(nodesField);

        inputPanel.add(nodesLabel);
        inputPanel.add(nodesField);
        buttonPanel.add(startButton);

        SpringUtilities.makeCompactGrid(inputPanel, 1, 2, 6, 6, 6, 6);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(250, 95);
        setVisible(true);
        setResizable(false);
        //pack();
        setLocationRelativeTo(null);
    }

    public Settings getFile() {
        int output = fileChooser.showOpenDialog(this);

        if (output == JFileChooser.APPROVE_OPTION) {
            int nodes = Integer.parseInt(nodesField.getText());
            return new Settings(fileChooser.getSelectedFile(), nodes);
        } else {
            return null;
        }
    }

    public boolean checkInput() {
        String error = "";
        String nodes = nodesField.getText();
        if (nodes.equals("")) {
            error = "Nodes field is Empty.";

        } else {
            try {
                Integer.parseInt(nodes);
                return true;
            } catch (Exception e) {
                error = "The value placed is not a whole number.";
            }
        }

        JOptionPane.showMessageDialog(this, error, "Notice", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void setStartButtonAction(ActionListener al) {
        startButton.addActionListener(al);
    }

    class Settings {

        private final File file;
        private final int nodes;

        public Settings(File file, int nodes) {
            this.file = file;
            this.nodes = nodes;
        }

        public File getFile() {
            return file;
        }

        public int getNodes() {
            return nodes;
        }

    }

}
