/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Fellowship;
import logic.Student;
import logic.TypeOfStudy;



/*******************************************************************************
 * Instances of class {@code StudentPanel} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class PanelStudent extends Box implements IFormPanel
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final String studentLabel = "Karta studenta:";

    private static final String noFellowship = "ŽÁDNÁ STÁŽ";

//== VARIABLE CLASS ATTRIBUTES =================================================

    private static boolean isEdited = false;

//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final Cards cards;

    private final JPanel mainStudentPanel = new JPanel(new BorderLayout());

    private final JPanel inputPanel = new JPanel(new GridLayout(6, 2, 15, 5));

    private final JPanel editPanel = new JPanel(new GridLayout(2, 2, 15, 5));

    private final JPanel confirmPanel = new JPanel(new GridLayout(2, 2, 15, 5));


    private final JLabel firstName = new JLabel("Jméno");

    private final JLabel lastName = new JLabel("Příjmení");

    private final JLabel contact = new JLabel("Kontakt");

    private final JLabel xname = new JLabel("XName");

    private final JLabel typeOfStudy = new JLabel("Typ studia");

    private final JLabel fellowship = new JLabel("Stáž");

    private final JComboBox fellowshipBox = new JComboBox<>();

    private final JTextField firstNameText = new JTextField();

    private final JTextField lastNameText = new JTextField();

    private final JTextField contactText = new JTextField();

    private final JLabel xnameText = new JLabel();

    private final JComboBox typeOfStudyBox = new JComboBox<>();


    private final JLabel editStudent = new JLabel("Úprava studenta: ");

    private final JComboBox editStudentBox = new JComboBox();

    private final JButton editButton = new JButton("Upravit");

    private final JButton deleteButton = new JButton("Smazat");


    private final JButton confirmButton = new JButton("Potvrdit vytvoření/úpravu");


//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private Student student;


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public PanelStudent(Cards cards)
    {
        super(1);
        this.cards = cards;

        add(new JLabel(studentLabel));

        inputPanel.add(firstName);
        inputPanel.add(firstNameText);
        inputPanel.add(lastName);
        inputPanel.add(lastNameText);
        inputPanel.add(contact);
        inputPanel.add(contactText);
        inputPanel.add(typeOfStudy);
        inputPanel.add(typeOfStudyBox);
        inputPanel.add(fellowship);
        inputPanel.add(fellowshipBox);
        inputPanel.add(xname);
        inputPanel.add(xnameText);

        editPanel.add(editStudent);
        editPanel.add(editStudentBox);
        editPanel.add(deleteButton);
        deleteButton.addActionListener(new DeleteListener());
        editPanel.add(editButton);
        editButton.addActionListener(new EditListener());

        confirmPanel.add(confirmButton);
        confirmButton.addActionListener(new CreateListener());

        mainStudentPanel.add(inputPanel, BorderLayout.NORTH);
        mainStudentPanel.add(editPanel, BorderLayout.SOUTH);

        inputPanel.setVisible(true);
        editPanel.setVisible(true);
        confirmPanel.setVisible(true);
        mainStudentPanel.setVisible(true);

        add(mainStudentPanel);
        add(confirmPanel);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     *
     */
    @Override
    public void initialize()
    {
        typeOfStudyBox.removeAllItems();
        for(TypeOfStudy initTypeOfStudy : TypeOfStudy.values()){
            typeOfStudyBox.addItem(initTypeOfStudy.getNameCZ());
        }

        changeFellowshipBox();
        changeStudentBox();

    }


    /***************************************************************************
     *
     */
    void changeFellowshipBox()
    {
        fellowshipBox.removeAllItems();
        fellowshipBox.addItem(noFellowship);
        if(!Fellowship.NAME_2_FELLOWSHIP.isEmpty()){
            for(Fellowship initFellowship : Fellowship.NAME_2_FELLOWSHIP.values()){
                if(initFellowship.getVacancy() > 0){
                    fellowshipBox.addItem(initFellowship.getName());
                }
            }
        }
    }


    /***************************************************************************
     *
     */
    void changeStudentBox(){
        if(!Student.NAME_2_STUDENT.isEmpty()){
            editStudentBox.removeAllItems();
            for(Student initStudent : Student.NAME_2_STUDENT.values()){
                editStudentBox.addItem(initStudent.getAllXName());
            }
            editStudentBox.setEnabled(true);
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
        else {
            editStudentBox.setEnabled(false);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }


    /***************************************************************************
     *
     */
    private void clearTextFields(){
        firstNameText.setText("");
        lastNameText.setText("");
        contactText.setText("");
        xnameText.setText("");
        changeFellowshipBox();
    }


    /***************************************************************************
     *
     */
    private void setTextFields()
    {
        firstNameText.setText(student.getFirstName());
        lastNameText.setText(student.getLastName());
        contactText.setText(student.getContact());
        xnameText.setText(student.getAllXName());
        typeOfStudyBox.getModel().setSelectedItem(student.getTypeOfStudy().getNameCZ());
        if(student.getFellowship() != null){
            fellowshipBox.getModel().setSelectedItem(student.getFellowship().getName());
        }
        else{
            fellowshipBox.getModel().setSelectedItem(noFellowship);
        }
    }


//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================

    private class CreateListener implements ActionListener
    {
        /***********************************************************************
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String createFirstName = firstNameText.getText().trim();
            String createLastName = lastNameText.getText().trim();
            TypeOfStudy createTypeOfStudy = TypeOfStudy.getInstance(typeOfStudyBox.getSelectedItem().toString());
            String createContact = contactText.getText().trim();

            Fellowship createFellowship = null;
            if(!Fellowship.NAME_2_FELLOWSHIP.isEmpty()){
                String fellowshipName = (String) fellowshipBox.getSelectedItem();
                if(!fellowshipName.equals(noFellowship)){
                    createFellowship = Fellowship.NAME_2_FELLOWSHIP.get(fellowshipName);
                }
            }

            if(createFirstName.isEmpty() || createLastName.isEmpty() || createContact.isEmpty()){
                JOptionPane.showMessageDialog(mainStudentPanel, "Nekteré z povinných dat nebyly zadány.");
            }
            else if(createLastName.length() < 3){
                JOptionPane.showMessageDialog(mainStudentPanel, "Příjmení musí mít nejméně tři znaky.");
            }
            else{
                if(isEdited){
                    int confirm = JOptionPane.showConfirmDialog(mainStudentPanel, "Určitě chcete uložit úpravy studenta " + student.getAllName());
                    if(confirm == 0){
                        increaseFellowshipVacany(createFellowship);
                        student.setFirstName(createFirstName);
                        student.setLastName(createLastName);
                        student.setContact(createContact);
                        student.setTypeOfStudy(createTypeOfStudy);
                        student.setFellowship(createFellowship);
                        isEdited = false;
                        student = null;
                        clearTextFields();
                    }
                    else{
                        setTextFields();
                    }
                }
                else {
                    Student initStudent = new Student(createFirstName, createLastName, createContact, createTypeOfStudy, createFellowship);
                    Student.NAME_2_STUDENT.put(initStudent.getAllXName(), initStudent);
                    clearTextFields();
                    increaseFellowshipVacany(createFellowship);
                }
                changeStudentBox();
            }
        }


        private void increaseFellowshipVacany(Fellowship createFellowship)
        {
            if(createFellowship != null){
                createFellowship.setVacancy(createFellowship.getVacancy()-1);
            }
        }

    }


    private class EditListener implements ActionListener
    {
        /***********************************************************************
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String editStudent = editStudentBox.getSelectedItem().toString();
            student = Student.NAME_2_STUDENT.get(editStudent);
            setTextFields();

            isEdited = true;
        }
    }


    private class DeleteListener implements  ActionListener
    {
        /***********************************************************************
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String editStudent = editStudentBox.getSelectedItem().toString();
            student = Student.NAME_2_STUDENT.get(editStudent);
            int confirm = JOptionPane.showConfirmDialog(mainStudentPanel, "Opravdu chcete smazat studenta " + student.getAllName() + " | " + student.getAllXName());

            if(confirm == 0){
                Student remove = Student.NAME_2_STUDENT.remove(student.getAllXName());
                clearTextFields();
                changeStudentBox();
                isEdited = false;
                student = null;
            }
        }

    }


//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        StudentPanel inst = new StudentPanel();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }



}
