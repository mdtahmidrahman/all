/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author admin
 */
import java.io.IOException;
import javax.swing.JFrame;

public class AdmissionFormMain
{
    public static void main(String[] args) throws IOException
    {
        VarsityAdmissionForm admissionForm = new VarsityAdmissionForm(1100, 900);
        admissionForm.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admissionForm.frame.setSize(1100, 900);
        admissionForm.frame.setVisible(true);
    }
}
