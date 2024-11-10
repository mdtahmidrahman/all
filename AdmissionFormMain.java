/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author admin
 */
import javax.swing.SwingUtilities;

public class AdmissionFormMain {
    public static void main(String[] args)
    {
        // Use SwingUtilities.invokeLater to ensure GUI components are created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new VarsityAdmissionForm(600, 700));
    }
}


