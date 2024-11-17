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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VarsityAdmissionForm
{
    public JFrame frame;
    private JTextField nameField, fatherNameField, motherNameField, permanentAddressField, presentAddressField;
    private JCheckBox sameAddressCheckBox;
    private JTextField phoneNumberField, emailField;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private JComboBox<String> bloodGroupComboBox, dayComboBox, monthComboBox, yearComboBox;
    private JRadioButton singleRadioButton, marriedRadioButton;
    private JTextField spouseNameField;
    private JRadioButton undergraduateRadioButton, graduateRadioButton;
    private JComboBox<String> departmentComboBox;
    private JTable table;
    private DefaultTableModel tableModel;

    public VarsityAdmissionForm(int width, int height) throws IOException
    {
        frame = new JFrame("Varsity Admission Form");
        createAndShowGUI(width, height);
        loadDataFromFile();
    }

    private void createAndShowGUI(int width, int height)
    {
        frame.setLayout(new BorderLayout());

        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(0, 20));
        frame.add(spacerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(15, 2, 10, 10));

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
        undergraduateRadioButton = new JRadioButton("Undergraduate");
        graduateRadioButton = new JRadioButton("Graduate");
        departmentComboBox = new JComboBox<>();

        frame.add(formPanel, BorderLayout.NORTH);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        ButtonGroup maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(singleRadioButton);
        maritalStatusGroup.add(marriedRadioButton);

        ButtonGroup educationGroup = new ButtonGroup();
        educationGroup.add(undergraduateRadioButton);
        educationGroup.add(graduateRadioButton);

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
        formPanel.add(new JLabel("Education Level:"));
        JPanel educationPanel = new JPanel();
        educationPanel.add(undergraduateRadioButton);
        educationPanel.add(graduateRadioButton);
        formPanel.add(educationPanel);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentComboBox);

        String[] columnNames = {"Name", "Father's Name", "Mother's Name", "Permanent Address", "Present Address",
                "Phone Number", "Email", "Gender", "Blood Group", "Date of Birth", "Marital Status",
                "Spouse's Name", "Education Level", "Department"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");

        submitButton.addActionListener(e -> handleSubmit());
        resetButton.addActionListener(e -> handleReset());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        singleRadioButton.addActionListener(e -> handleMaritalStatusChange());
        marriedRadioButton.addActionListener(e -> handleMaritalStatusChange());
        sameAddressCheckBox.addActionListener(e -> handleAddressChange());
        undergraduateRadioButton.addActionListener(e -> updateDepartmentOptions());
        graduateRadioButton.addActionListener(e -> updateDepartmentOptions());
    }

    private void handleMaritalStatusChange()
    {
        spouseNameField.setEnabled(marriedRadioButton.isSelected());
    }

    private void handleAddressChange()
    {
        presentAddressField.setEnabled(!sameAddressCheckBox.isSelected());
        if (sameAddressCheckBox.isSelected())
            presentAddressField.setText(permanentAddressField.getText());
    }

    private void updateDepartmentOptions()
    {
        if (undergraduateRadioButton.isSelected())
            departmentComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                    "B.Sc. in Software Engineering", "B.Sc. in Computer Science and Engineering", "B.Sc. in Electronic and Electrical Engineering",
                "B.S.S. in Economics", "B.B.A. in Business Administration", "LL.B. in  Law and Justice", "B.A. in English"}));
        else if (graduateRadioButton.isSelected())
            departmentComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                    "M.A. in English", "M.S.S. in Economics", "LL.M. in Law and Justice"}));
    }

    private String[] createDays()
    {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
        return days;
    }

    private String[] createMonths()
    {
        return new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    }

    private String[] createYears()
    {
        String[] years = new String[29];
        for (int i = 0; i < 29; i++) years[i] = String.valueOf(1980 + i);
        return years;
    }

    private void handleSubmit()
    {
        String name = nameField.getText();
        String fatherName = fatherNameField.getText();
        String motherName = motherNameField.getText();
        String permanentAddress = permanentAddressField.getText();
        String presentAddress = sameAddressCheckBox.isSelected() ? permanentAddress : presentAddressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        if (!email.contains("@") && !email.contains(".com"))
        {
            JOptionPane.showMessageDialog(frame, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String gender = maleRadioButton.isSelected() ? "Male" : femaleRadioButton.isSelected() ? "Female" : "Other";
        String bloodGroup = (String) bloodGroupComboBox.getSelectedItem();
        String dob = dayComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem();
        String maritalStatus = singleRadioButton.isSelected() ? "Single" : marriedRadioButton.isSelected() ? "Married" : "";
        String spouseName = spouseNameField.getText();
        String educationLevel = undergraduateRadioButton.isSelected() ? "Undergraduate" : "Graduate";
        String department = (String) departmentComboBox.getSelectedItem();

        String[] rowData = {
                name, fatherName, motherName, permanentAddress, presentAddress,
                phoneNumber, email, gender, bloodGroup, dob, maritalStatus,
                spouseName, educationLevel, department
        };
        tableModel.addRow(rowData);

        saveDataToFile(rowData);
    }

    private void saveDataToFile(String[] rowData)
    {
        try (Formatter formatter = new Formatter("admission_data.txt"))
        {
            formatter.format("%s%n", String.join(",", rowData));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() throws IOException
    {
        try (Scanner scanner = new Scanner(Paths.get("admission_data.txt")))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No previous data found.");
        }
    }

    private void handleReset()
    {
        nameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        permanentAddressField.setText("");
        presentAddressField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        otherRadioButton.setSelected(false);
        bloodGroupComboBox.setSelectedIndex(0);
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        singleRadioButton.setSelected(false);
        marriedRadioButton.setSelected(false);
        spouseNameField.setText("");
        undergraduateRadioButton.setSelected(false);
        graduateRadioButton.setSelected(false);
        departmentComboBox.setModel(new DefaultComboBoxModel<>());
    }
}
