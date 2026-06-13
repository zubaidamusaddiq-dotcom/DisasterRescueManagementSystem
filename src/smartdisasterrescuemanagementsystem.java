import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Disaster {
    int id;
    String type;
    String city;
    int severity;

    public Disaster(int id, String type, String city, int severity) {
        this.id = id;
        this.type = type;
        this.city = city;
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Type: " + type +
                ", City: " + city +
                ", Severity: " + severity;
    }
}

public class smartdisasterrescuemanagementsystem extends JFrame {


    ArrayList<Disaster> disasterList = new ArrayList<>();
    LinkedList<String> victims = new LinkedList<>();
    Stack<String> history = new Stack<>();
    Queue<String> rescueQueue = new LinkedList<>();
    HashMap<String, ArrayList<Disaster>> cityMap = new HashMap<>();


    JTextField idField, typeField, cityField, severityField;
    JTextArea outputArea;

    public smartdisasterrescuemanagementsystem() {

        setTitle("Smart Disaster Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Disaster ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("City:"));
        cityField = new JTextField();
        inputPanel.add(cityField);

        inputPanel.add(new JLabel("Severity:"));
        severityField = new JTextField();
        inputPanel.add(severityField);

        JButton addBtn = new JButton("Add Disaster");
        JButton viewBtn = new JButton("View Disasters");

        inputPanel.add(addBtn);
        inputPanel.add(viewBtn);

        add(inputPanel, BorderLayout.NORTH);


        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();

        JButton victimBtn =
                new JButton("Add Victim");

        JButton rescueBtn =
                new JButton("Add Rescue Request");

        JButton processBtn =
                new JButton("Process Rescue");

        JButton historyBtn =
                new JButton("View History");

        buttonPanel.add(victimBtn);
        buttonPanel.add(rescueBtn);
        buttonPanel.add(processBtn);
        buttonPanel.add(historyBtn);

        add(buttonPanel, BorderLayout.SOUTH);


        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int id =
                            Integer.parseInt(idField.getText());

                    String type =
                            typeField.getText();

                    String city =
                            cityField.getText();

                    int severity =
                            Integer.parseInt(
                                    severityField.getText());

                    Disaster d =
                            new Disaster(id, type, city, severity);


                    disasterList.add(d);


                    cityMap.putIfAbsent(city,
                            new ArrayList<>());

                    cityMap.get(city).add(d);


                    history.push("Added Disaster: " + type);

                    outputArea.append(
                            "Disaster Added Successfully!\n");

                    idField.setText("");
                    typeField.setText("");
                    cityField.setText("");
                    severityField.setText("");

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Input!"
                    );
                }
            }
        });


        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                outputArea.setText("");

                for (Disaster d : disasterList) {
                    outputArea.append(d + "\n");
                }
            }
        });


        victimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String victim =
                        JOptionPane.showInputDialog(
                                "Enter Victim Name");

                victims.add(victim);

                history.push("Victim Added: " + victim);

                outputArea.append(
                        "Victim Added Successfully!\n");
            }
        });


        rescueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String request =
                        JOptionPane.showInputDialog(
                                "Enter Rescue Request");


                rescueQueue.offer(request);

                history.push(
                        "Rescue Request Added");

                outputArea.append(
                        "Rescue Request Added!\n");
            }
        });


        processBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!rescueQueue.isEmpty()) {

                    outputArea.append(
                            "Processed: "
                                    + rescueQueue.poll()
                                    + "\n");

                } else {

                    outputArea.append(
                            "No Rescue Requests!\n");
                }
            }
        });


        historyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                outputArea.append(
                        "\n===== ACTION HISTORY =====\n");

                for (String action : history) {
                    outputArea.append(action + "\n");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new smartdisasterrescuemanagementsystem();
    }
}