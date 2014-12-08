/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package graphic;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import logic.Company;





/*******************************************************************************
 * Instances of class {@code Search} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class Gui
{
//== CONSTANT CLASS ATTRIBUTES =================================================

//    private static final Gui INSTANCE = new Gui();

    private static final int FRAME_WIDTH = 450;

    private static final int FRAME_HEIGHT = 300;


//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================

    static {
        setLookAndFeel();
    }


//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final JFrame mainFrame = new JFrame("Stáže pro studenty");

    final Cards CARDS = new Cards(mainFrame);


    final TableStudent TABLE_STUDENT = new TableStudent(mainFrame);

    final TableCompany TABLE_COMPANY = new TableCompany(mainFrame);

    final TableFellowship TABLE_FELLOWSHIP = new TableFellowship(mainFrame);


    private final JMenuBar mainMenu = new JMenuBar();

    private final MenuCards menuCards = new MenuCards(this, CARDS);

    private final MenuHelp menuHelp = new MenuHelp(this);


    private final JPanel content = new JPanel();

    private final JPanel searchPanel = new JPanel(new BorderLayout(20,5));

    private final JPanel searchTextPanel = new JPanel(new GridLayout(5, 1, 5, 5));

    private final JPanel searchCheckPanel = new JPanel(new GridLayout(5, 1, 5, 5));

    private final JPanel searchBoxPanel = new JPanel(new GridLayout(5, 1, 5, 5));

    private final JPanel tablePanel = new JPanel(new GridLayout(3, 1, 5,5));


    private final JLabel countryText = new JLabel("Dle země");

    private final Checkbox countryCheck = new Checkbox();

    private final JComboBox countryBox = new JComboBox();

    private final JLabel companyText = new JLabel("Dle společnosti");

    private final Checkbox companyCheck = new Checkbox();

    private final JComboBox<Company> companyBox = new JComboBox<>();

    private final JLabel vacancyText = new JLabel("Dle volných míst");

    private final Checkbox vacancyCheck = new Checkbox();

    private final JComboBox vacancyBox = new JComboBox();

    private final JLabel lenghtText = new JLabel("Dle délky stáže");

    private final Checkbox lenghtCheck = new Checkbox();

    private final JComboBox lenghtBox = new JComboBox();


    private final JButton searchButton = new JButton("Hledej");


    private final JButton tableStudent = new JButton("Zobrazit studenty");

    private final JButton tableCompany = new JButton("Zobrazit společnosti");

    private final JButton tableFellowship = new JButton("Zobrazit stáže");


//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Gui()
    {
        createFrame();

        createMenu();

        //Pripraveno pro pozdejší vývoj aplikace
        createSearchPanel();
        searchPanel.setVisible(false);

        createTablePanel();

        doOther();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     *
     */
    public JFrame getParent(){
        return mainFrame;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    public void initialize(){
        CARDS.studentPanel.initialize();
        CARDS.companyPanel.initialize();
        CARDS.fellowshipPanel.initialize();
    }


//== PRIVATE AND AUXILIARY CLASS METHODS =======================================

    /***************************************************************************
     *
     */
    private static void setLookAndFeel()
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }


//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    /***************************************************************************
     *
     */
    private void createFrame()
    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // Vystředění oken
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int widthCenter = (int) (width/2)-(FRAME_WIDTH/2);
        int heightCenter = (int) (height/2)-(FRAME_HEIGHT/2);

        mainFrame.setLocation(widthCenter, heightCenter);
    }


    /***************************************************************************
     *
     */
    private void createMenu()
    {
        mainFrame.setJMenuBar(mainMenu);
        mainMenu.add(menuCards);
        mainMenu.add(menuHelp);
    }


    /***************************************************************************
     *
     */
    private void createSearchPanel()
    {
        searchTextPanel.add(countryText);
        searchCheckPanel.add(countryCheck);
        searchBoxPanel.add(countryBox);
        searchTextPanel.add(companyText);
        searchCheckPanel.add(companyCheck);
        searchBoxPanel.add(companyBox);
        searchTextPanel.add(lenghtText);
        searchCheckPanel.add(lenghtCheck);
        searchBoxPanel.add(lenghtBox);
        searchTextPanel.add(vacancyText);
        searchCheckPanel.add(vacancyCheck);
        searchBoxPanel.add(vacancyBox);

        searchBoxPanel.add(searchButton);

        searchPanel.add(searchTextPanel, BorderLayout.WEST);
        searchPanel.add(searchCheckPanel, BorderLayout.CENTER);
        searchPanel.add(searchBoxPanel, BorderLayout.EAST);

        searchPanel.setBorder(BorderFactory.createTitledBorder("Vyhledávání stáží"));
    }


    /***************************************************************************
     *
     */
    private void createTablePanel()
    {
        tablePanel.add(tableStudent);
        tablePanel.add(tableCompany);
        tablePanel.add(tableFellowship);


        tableStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(TABLE_STUDENT.initialize()){
                    TABLE_STUDENT.setVisible(true);
                }
            }
        });

        tableCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(TABLE_COMPANY.initialize()){
                    TABLE_COMPANY.setVisible(true);
                }
            }
        });

        tableFellowship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(TABLE_FELLOWSHIP.initialize()){
                    TABLE_FELLOWSHIP.setVisible(true);
                }
            }
        });


        tablePanel.setBorder(BorderFactory.createTitledBorder("Zobrazit tabulky"));
    }


    /**************************************************************************
     *
     */
    private void doOther()
    {
        content.add(searchPanel, BorderLayout.CENTER);
        content.add(tablePanel, BorderLayout.EAST);

        mainFrame.add(content);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HlavniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HlavniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HlavniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HlavniFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        Gui inst = new Gui();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }






}
