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
import logic.Company;
import logic.Countries;

import logic.Fellowship;


/*******************************************************************************
 * Instances of class {@code StudentPanel} represent ...
 *
 * @author  AndrĂ© HELLER
 * @version 0.00 â€” mm/20yy
 */
public class PanelFellowship extends Box implements IFormPanel
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final String fellowshipLabel = "Karta stáže:";

//== VARIABLE CLASS ATTRIBUTES =================================================

    private static boolean isEdited = false;

//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final Cards cards;

    private final JPanel mainFellowshipPanel = new JPanel(new BorderLayout());

    private final JPanel inputPanel = new JPanel(new GridLayout(6, 2, 15, 5));

    private final JPanel editPanel = new JPanel(new GridLayout(2, 2, 15, 5));

    private final JPanel confirmPanel = new JPanel(new GridLayout(2, 2, 15, 5));


    private final JLabel name = new JLabel("Název stáže");

    private final JLabel company = new JLabel("Společnost");

    private final JLabel time = new JLabel("Délka stáže (týdny)");

    private final JLabel vacancy = new JLabel("Volná místa");

    private final JLabel country = new JLabel("Země");

    private final JTextField nameText = new JTextField();

    private final JComboBox companyBox = new JComboBox<>();

    private final JTextField timeText = new JTextField();

    private final JTextField vacancyText = new JTextField();

    private final JComboBox countryBox = new JComboBox<>(Countries.values());


    private final JLabel editFellowship = new JLabel("Úprava stáže: ");

    private final JComboBox editFellowshipBox = new JComboBox<>();

    private final JButton editButton = new JButton("Upravit");

    private final JButton deleteButton = new JButton("Smazat");


    private final JButton confirmButton = new JButton("Potvrdit vytvoření/úpravu");

//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private Fellowship fellowship;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public PanelFellowship(Cards cards)
    {
        super(1);

        this.cards = cards;

        add(new JLabel(fellowshipLabel));

        inputPanel.add(name);
        inputPanel.add(nameText);
        inputPanel.add(company);
        inputPanel.add(companyBox);
        inputPanel.add(time);
        inputPanel.add(timeText);
        inputPanel.add(vacancy);
        inputPanel.add(vacancyText);
        inputPanel.add(country);
        inputPanel.add(countryBox);

        editPanel.add(editFellowship);
        editPanel.add(editFellowshipBox);
        editPanel.add(deleteButton);
        deleteButton.addActionListener(new DeleteListener());
        editPanel.add(editButton);
        editButton.addActionListener(new EditListener());

        confirmPanel.add(confirmButton);
        confirmButton.addActionListener(new CreateListener());

        mainFellowshipPanel.add(inputPanel, BorderLayout.NORTH);
        mainFellowshipPanel.add(editPanel, BorderLayout.SOUTH);

        inputPanel.setVisible(true);
        editPanel.setVisible(true);
        confirmPanel.setVisible(true);
        mainFellowshipPanel.setVisible(true);

        add(mainFellowshipPanel);
        add(confirmPanel);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    @Override
    public void initialize()
    {
        changeCompanyBox();
        changeFellowshipBox();
    }


    /***************************************************************************
     *
     */
    void changeCompanyBox()
    {
        companyBox.removeAllItems();
        if(!Company.NAME_2_COMPANY.isEmpty()){
            for(Company initCompany : Company.NAME_2_COMPANY.values()){
                companyBox.addItem(initCompany.getName());
                companyBox.setEnabled(true);
            }
        }
        else {
            companyBox.addItem("Nejprve vytvořte");
            companyBox.setEnabled(false);
        }
    }


    /***************************************************************************
     *
     */
    void changeFellowshipBox(){
        editFellowshipBox.removeAllItems();
        if(!Fellowship.NAME_2_FELLOWSHIP.isEmpty()){
            for(Fellowship initFellowship : Fellowship.NAME_2_FELLOWSHIP.values()){
                editFellowshipBox.addItem(initFellowship.getName());
                editFellowshipBox.setEnabled(true);
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        }
        else {
            editFellowshipBox.setEnabled(false);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
        cards.studentPanel.changeFellowshipBox();
    }


    /***************************************************************************
     *
     */
    private void clearTextFields(){
        nameText.setText("");
        timeText.setText("");
        vacancyText.setText("");
        editFellowshipBox.removeAllItems();
    }


    /***************************************************************************
     *
     */
    private void setTextFields()
    {
        nameText.setText(fellowship.getName());
        timeText.setText(fellowship.getLenght()+"");
        vacancyText.setText(fellowship.getVacancy()+"");
        countryBox.getModel().setSelectedItem(fellowship.getCountry());

        if(fellowship.getCompany() != null){
            companyBox.getModel().setSelectedItem(fellowship.getCompany().getName());
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
            String createName = nameText.getText().trim();
            String createTime = timeText.getText().trim();
            String createVacancy = vacancyText.getText().trim();
            Countries createCountry = (Countries) countryBox.getSelectedItem();

            String companyName = (String) companyBox.getSelectedItem();
            Company createCompany = Company.NAME_2_COMPANY.get(companyName);

            if(!Company.NAME_2_COMPANY.isEmpty()){//společnost už existuje
                if(createName.isEmpty() || createTime.isEmpty() || createVacancy.isEmpty()){
                    JOptionPane.showMessageDialog(mainFellowshipPanel, "Nekteré z povinných dat nebyly zadány.");
                }
                else{
                    if(isEdited){
                        int confirm = JOptionPane.showConfirmDialog(mainFellowshipPanel, "Určitě chcete uložit úpravy stáže " + fellowship.getName());
                        if(confirm == 0){
                            try{
                                fellowship.setName(createName);
                                fellowship.setLenght(Integer.parseInt(createTime));
                                fellowship.setCompany(createCompany);
                                fellowship.setVacancy(Integer.parseInt(createVacancy));
                                fellowship.setCountry(createCountry);
                                isEdited = false;
                                fellowship = null;
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
                                Fellowship initFellowship = new Fellowship(createName, createCountry, Integer.parseInt(createTime), Integer.parseInt(createVacancy), createCompany);
                                Fellowship.NAME_2_FELLOWSHIP.put(initFellowship.getName(), initFellowship);
                                clearTextFields();

                            }
                            catch(NumberFormatException ex){
                                FormatException();
                            }
                    }
                    changeFellowshipBox();
                }
            }
            else {
                JOptionPane.showMessageDialog(mainFellowshipPanel, "Než vytvoříte stáž, musíte vytvořit společnost zaštitující stáž!");
            }
        }


        /***********************************************************************
         *
         */
        private void FormatException()
        {
            JOptionPane.showMessageDialog(mainFellowshipPanel, "IČO nebo PSČ nebylo zadáno v číselném formátu!");
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
            String editFellowship = editFellowshipBox.getSelectedItem().toString();
            fellowship = Fellowship.NAME_2_FELLOWSHIP.get(editFellowship);

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
            String editFellowship = editFellowshipBox.getSelectedItem().toString();
            fellowship = Fellowship.NAME_2_FELLOWSHIP.get(editFellowship);
            int confirm = JOptionPane.showConfirmDialog(mainFellowshipPanel, "Opravdu chcete smazat stáž " + fellowship.getName());

            if(confirm == 0){
                Fellowship remove = Fellowship.NAME_2_FELLOWSHIP.remove(fellowship.getName());
                clearTextFields();
                changeFellowshipBox();
                isEdited = false;
                fellowship = null;
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
