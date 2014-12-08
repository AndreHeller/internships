/* The file is saved in UTF-8 codepage.
 * Check: Â«StereotypeÂ», Section mark-Â§, Copyright-Â©, Alpha-Î±, Beta-Î˛, Smile-â�ş
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
import logic.Address;

import logic.Company;



/*******************************************************************************
 * Instances of class {@code StudentPanel} represent ...
 *
 * @author  AndrĂ© HELLER
 * @version 0.00 â€” mm/20yy
 */
public class PanelCompany extends Box implements IFormPanel
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final String companyLabel = "Karta společnosti:";

//== VARIABLE CLASS ATTRIBUTES =================================================

    private static boolean isEdited = false;


//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final Cards cards;

    private final JPanel mainCompanyPanel = new JPanel(new BorderLayout());

    private final JPanel inputPanel = new JPanel(new GridLayout(7, 2, 0, 0));

    private final JPanel editPanel = new JPanel(new GridLayout(2, 2, 15, 5));

    private final JPanel confirmPanel = new JPanel(new GridLayout(2, 2, 15, 5));


    private final JLabel name = new JLabel("Název společnosti");

    private final JLabel idNumber = new JLabel("IČO");

    private final JLabel contact = new JLabel("Kontakt");

    private final JLabel street = new JLabel("Ulice");

    private final JLabel city = new JLabel("Město");

    private final JLabel ZIPCode = new JLabel("PSČ");

    private final JLabel country = new JLabel("Země");

    private final JTextField nameText = new JTextField();

    private final JTextField icoNumberText = new JTextField();

    private final JTextField contactText = new JTextField();

    private final JTextField streetText = new JTextField();

    private final JTextField cityText = new JTextField();

    private final JTextField ZIPCodeText = new JTextField();

    private final JTextField countryText = new JTextField();


    private final JLabel editCompany = new JLabel("Úprava společnosti: ");

    private final JComboBox editCompanyBox = new JComboBox();

    private final JButton editButton = new JButton("Upravit");

    private final JButton deleteButton = new JButton("Smazat");


    private final JButton confirmButton = new JButton("Potvrdit vytvoření/úpravu");

//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private Company company;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public PanelCompany(Cards cards)
    {
        super(1);
        this.cards = cards;

        add(new JLabel(companyLabel));

        inputPanel.add(name);
        inputPanel.add(nameText);
        inputPanel.add(idNumber);
        inputPanel.add(icoNumberText);
        inputPanel.add(contact);
        inputPanel.add(contactText);

        inputPanel.add(street);
        inputPanel.add(streetText);
        inputPanel.add(city);
        inputPanel.add(cityText);
        inputPanel.add(ZIPCode);
        inputPanel.add(ZIPCodeText);
        inputPanel.add(country);
        inputPanel.add(countryText);

        editPanel.add(editCompany);
        editPanel.add(editCompanyBox);
        editPanel.add(deleteButton);
        deleteButton.addActionListener(new DeleteListener());
        editPanel.add(editButton);
        editButton.addActionListener(new EditListener());

        confirmPanel.add(confirmButton);
        confirmButton.addActionListener(new CreateListener());

        mainCompanyPanel.add(inputPanel, BorderLayout.NORTH);
        mainCompanyPanel.add(editPanel, BorderLayout.SOUTH);

        inputPanel.setVisible(true);
        editPanel.setVisible(true);
        confirmPanel.setVisible(true);
        mainCompanyPanel.setVisible(true);

        add(mainCompanyPanel);
        add(confirmPanel);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    @Override
    public void initialize()
    {
        changeCompanyBox();
    }


    /***************************************************************************
     *
     */
    private void changeCompanyBox()
    {
        if(!Company.NAME_2_COMPANY.isEmpty()){
            for(Company initCompany : Company.NAME_2_COMPANY.values()){
                editCompanyBox.addItem(initCompany.getName());
            }
            editCompanyBox.setEnabled(true);
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
        else {
            editCompanyBox.setEnabled(false);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
        cards.fellowshipPanel.changeCompanyBox();
    }


    /***************************************************************************
     *
     */
    public void clearTextFields(){
        nameText.setText("");
        icoNumberText.setText("");
        contactText.setText("");
        streetText.setText("");
        cityText.setText("");
        ZIPCodeText.setText("");
        countryText.setText("");
        editCompanyBox.removeAllItems();
    }


    /***************************************************************************
     *
     */
    private void setTextFields()
    {
        nameText.setText(company.getName());
        icoNumberText.setText(company.getId()+"");
        contactText.setText(company.getContact());

        streetText.setText(company.getAddress().getStreet());
        cityText.setText(company.getAddress().getCity());
        ZIPCodeText.setText(company.getAddress().getZIPCode()+"");
        countryText.setText(company.getAddress().getCountry());
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
            String createName = nameText.getText().trim();
            String createICONumber = icoNumberText.getText().trim();
            String createContact = contactText.getText().trim();
            String createStreet = streetText.getText().trim();
            String createCity = cityText.getText().trim();
            String createZIPCode = ZIPCodeText.getText().trim();
            String createCountry = countryText.getText().trim();


            if(createName.isEmpty() || createICONumber.isEmpty() || createContact.isEmpty() ||
               createStreet.isEmpty() || createCity.isEmpty() || createZIPCode.isEmpty() || createCountry.isEmpty()){
                JOptionPane.showMessageDialog(mainCompanyPanel, "Nekteré z povinných dat nebyly zadány.");
            }
            else{
                if(isEdited){
                    int confirm = JOptionPane.showConfirmDialog(mainCompanyPanel, "Určitě chcete uložit úpravy společnosti " + company.getName());
                    if(confirm == 0){
                        try {
                            int localId = Integer.parseInt(createICONumber);
                            int localZIPCode = Integer.parseInt(createZIPCode);

                            company.setName(createName);
                            company.setId(localId);
                            company.setContact(createContact);

                            Address address = company.getAddress();
                            address.setStreet(createStreet);
                            address.setCity(createCity);
                            address.setZIPCode(localZIPCode);
                            address.setCountry(createCountry);

                            company.setAddress(address);
                            isEdited = false;
                            company = null;
                            clearTextFields();
                        }
                        catch (NumberFormatException ex){
                            FormatException();
                        }
                    }
                    else{
                        setTextFields();
                    }
                }
                else {
                    try {
                        Company initCompany = new Company(createName, Integer.parseInt(createICONumber), createContact, createStreet, createCity, Integer.parseInt(createZIPCode), createCountry);
                        Company.NAME_2_COMPANY.put(initCompany.getName(), initCompany);
                        clearTextFields();
                    }
                    catch(NumberFormatException ex){
                        FormatException();
                    }
                }
                changeCompanyBox();
            }
        }


        /***********************************************************************
         *
         */
        private void FormatException()
        {
            JOptionPane.showMessageDialog(mainCompanyPanel, "IČO nebo PSČ nebylo zadáno v číselném formátu!");
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
            String editCompany = editCompanyBox.getSelectedItem().toString();
            company = Company.NAME_2_COMPANY.get(editCompany);

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
            String editCompany = editCompanyBox.getSelectedItem().toString();
            company = Company.NAME_2_COMPANY.get(editCompany);
            int confirm = JOptionPane.showConfirmDialog(mainCompanyPanel, "Opravdu chcete smazat společnost " + company.getName());

            if(confirm == 0){
                Company remove = Company.NAME_2_COMPANY.remove(company.getName());
                clearTextFields();
                changeCompanyBox();
                isEdited = false;
                company = null;
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
