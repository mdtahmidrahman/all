/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author admin
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VarsityAdmissionForm
{
    private JFrame frame;
    private JTextField nameField, fatherNameField, motherNameField, permanentAddressField, presentAddressField;
    private JCheckBox sameAddressCheckBox;
    private JTextField phoneNumberField, emailField;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private JComboBox<String> bloodGroupComboBox, dayComboBox, monthComboBox, yearComboBox;
    private JRadioButton singleRadioButton, marriedRadioButton;
    private JTextField spouseNameField;
    private JTable table;
    private DefaultTableModel tableModel;

    public VarsityAdmissionForm(int width, int height)
    {
        // Pass the width and height to the frame setup
        createAndShowGUI(width, height);
    }

    private void createAndShowGUI(int width, int height)
    {
        frame = new JFrame("Varsity Admission Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);  // Set the frame size based on the passed parameters
        frame.setLayout(new BorderLayout());

        // Add a spacer panel to create some space from the top of the frame
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(0, 20));  // 20px of space from the top
        frame.add(spacerPanel, BorderLayout.NORTH);

        // Panel to hold the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(13, 2, 10, 10));

        // Form Fields
        nameField = new JTextField();
        fatherNameField = new JTextField();
        motherNameField = new JTextField();
        permanentAddressField = new JTextField();
        presentAddressField = new JTextField();
        sameAddressCheckBox = new JCheckBox("Present Address same as Permanent Address");
        phoneNumberField = new JTextField();
        emailField = new JTextField();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");
        bloodGroupComboBox = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        dayComboBox = new JComboBox<>(createDays());
        monthComboBox = new JComboBox<>(createMonths());
        yearComboBox = new JComboBox<>(createYears());
        singleRadioButton = new JRadioButton("Single");
        marriedRadioButton = new JRadioButton("Married");
        spouseNameField = new JTextField();

        // Group the gender radio buttons
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        // Group the marital status radio buttons
        ButtonGroup maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(singleRadioButton);
        maritalStatusGroup.add(marriedRadioButton);

        // Add components to the form panel
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Father's Name:"));
        formPanel.add(fatherNameField);
        formPanel.add(new JLabel("Mother's Name:"));
        formPanel.add(motherNameField);
        formPanel.add(new JLabel("Permanent Address:"));
        formPanel.add(permanentAddressField);
        formPanel.add(sameAddressCheckBox);
        formPanel.add(presentAddressField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);
        formPanel.add(genderPanel);
        formPanel.add(new JLabel("Blood Group:"));
        formPanel.add(bloodGroupComboBox);
        formPanel.add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel();
        dobPanel.add(dayComboBox);
        dobPanel.add(monthComboBox);
        dobPanel.add(yearComboBox);
        formPanel.add(dobPanel);
        formPanel.add(new JLabel("Marital Status:"));
        JPanel maritalStatusPanel = new JPanel();
        maritalStatusPanel.add(singleRadioButton);
        maritalStatusPanel.add(marriedRadioButton);
        formPanel.add(maritalStatusPanel);
        formPanel.add(new JLabel("Spouse's Name (if Married):"));
        formPanel.add(spouseNameField);

        // Table to display the data
        String[] columnNames = {"Name", "Father's Name", "Mother's Name", "Permanent Address", "Present Address",
                "Phone Number", "Email", "Gender", "Blood Group", "Date of Birth", "Marital Status", "Spouse's Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate Email
                String email = emailField.getText();
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(frame, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If 'Married' is selected, ensure the spouse name is filled
                if (marriedRadioButton.isSelected() && spouseNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Spouse's Name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Prepare the data
                String name = nameField.getText();
                String fatherName = fatherNameField.getText();
                String motherName = motherNameField.getText();
                String permanentAddress = permanentAddressField.getText();
                String presentAddress = sameAddressCheckBox.isSelected() ? permanentAddress : presentAddressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String gender = maleRadioButton.isSelected() ? "Male" : femaleRadioButton.isSelected() ? "Female" : "Other";
                String bloodGroup = (String) bloodGroupComboBox.getSelectedItem();
                String dob = dayComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem();
                String maritalStatus = singleRadioButton.isSelected() ? "Single" : "Married";
                String spouseName = maritalStatus.equals("Married") ? spouseNameField.getText() : "";

                // Add row to the table
                tableModel.addRow(new Object[]{name, fatherName, motherName, permanentAddress, presentAddress, phoneNumber, email, gender, bloodGroup, dob, maritalStatus, spouseName});
            }
        });

        // Add components to the frame
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(submitButton, BorderLayout.SOUTH);

        // Show the window
        frame.setVisible(true);

        // Listeners to handle conditional field activation
        singleRadioButton.addActionListener(e -> handleMaritalStatusChange());
        marriedRadioButton.addActionListener(e -> handleMaritalStatusChange());
        sameAddressCheckBox.addActionListener(e -> handleAddressChange());
    }

    // Handle marital status change
    private void handleMaritalStatusChange() {
        if (singleRadioButton.isSelected()) {
            spouseNameField.setEnabled(false); // Disable spouse name if Single
        } else if (marriedRadioButton.isSelected()) {
            spouseNameField.setEnabled(true);  // Enable spouse name if Married
        }
    }

    // Handle address change (when "Present Address same as Permanent Address" is checked)
    private void handleAddressChange() {
        presentAddressField.setEnabled(!sameAddressCheckBox.isSelected());  // Disable present address if same address is checked
        if (sameAddressCheckBox.isSelected()) {
            presentAddressField.setText(permanentAddressField.getText());  // Copy permanent address to present address
        }
    }

    private String[] createDays() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    private String[] createMonths() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    private String[] createYears() {
        String[] years = new String[29];
        for (int i = 0; i < 29; i++) {
            years[i] = String.valueOf(1980 + i);
        }
        return years;
    }
}
