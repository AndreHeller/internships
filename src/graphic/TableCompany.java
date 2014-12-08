/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.Company;



/*******************************************************************************
 * Instances of class {@code TableCompany} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class TableCompany extends JFrame
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final int FRAME_WIDTH = 640;

    private static final int FRAME_HEIGHT = 480;


//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final JFrame mainFrame;

//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private final JTable companyTable = new JTable();

    private final JPanel northPanel = new JPanel();

    private final JPanel infoPanel = new JPanel();

    private final JLabel companyCount = new JLabel("Počet společností: ");

    private final JLabel companyCountText = new JLabel();

    private final JButton actualizeButton = new JButton("Aktualizovat");

    private final JScrollPane scrollpane = new JScrollPane();


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public TableCompany(JFrame mainFrame)
    {
        super("Tabulka společností");
        this.mainFrame = mainFrame;

        setFrame();

        companyTable.setBackground(new Color(255, 255, 204));
        companyTable.setGridColor(new Color(0, 0, 0));
        companyTable.setShowGrid(true);

        infoPanel.add(companyCount);
        infoPanel.add(companyCountText);

        northPanel.setLayout(new BorderLayout(10, 10));
        northPanel.add(infoPanel,BorderLayout.WEST);
        northPanel.add(actualizeButton,BorderLayout.EAST);
        actualizeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                initialize();
            }
        });

        add(northPanel,BorderLayout.NORTH);
        add(scrollpane,BorderLayout.CENTER);
        pack();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     *
     */
    @Override
    public JFrame getParent(){
        return mainFrame;
    }


    /***************************************************************************
     *
     */
    private void setFrame()
    {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // Vystředění oken
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int widthCenter = (int) (width/2)-(FRAME_WIDTH/2);
        int heightCenter = (int) (height/2)-(FRAME_HEIGHT/2);

        setLocation(widthCenter, heightCenter);

        addWindowListener(new WL());

        setVisible(false);

        setLayout(new BorderLayout(10, 10));
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     *
     * @return
     */
    public boolean initialize(){
        if(!Company.NAME_2_COMPANY.isEmpty()){
            String[] header = {"Název", "IČO", "Kontakt", "Ulice", "Město", "PSČ", "Země"};
            String[][] data = new String[Company.NAME_2_COMPANY.size()][header.length];
            int i = 0;

            companyCountText.setText(Company.NAME_2_COMPANY.size()+"");

            for(Company company : Company.NAME_2_COMPANY.values()){

                data[i][0] = company.getName();
                data[i][1] = company.getId()+"";
                data[i][2] = company.getContact();
                data[i][3] = company.getAddress().getStreet();
                data[i][4] = company.getAddress().getCity();
                data[i][5] = company.getAddress().getZIPCode()+"";
                data[i][6] = company.getAddress().getCountry();

                i++;
            }
            companyTable.setModel(new DefaultTableModel(data, header){
                boolean[] canEdit = new boolean []
                {
                    false, false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit [columnIndex];
                };
            });
            scrollpane.setViewportView(companyTable);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Dosud nebyla vytvořena žádná společnost.");
            return false;
        }
    }


//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================

    /***************************************************************************
     * Instance trídy WL (Window Listener) odposlouchávají události okna
     *
     */
    private class WL implements WindowListener
    {
        /***********************************************************************
         * privátní konstruktor
         */
        private WL(){}


        /***********************************************************************
         *
         * @param e událost
         */
        @Override
        public void windowClosing(WindowEvent e){
            TableCompany.this.setVisible(false);
        }


        @Override
        public void windowOpened(WindowEvent e){}
        @Override
        public void windowClosed(WindowEvent e){}
        @Override
        public void windowIconified(WindowEvent e){}
        @Override
        public void windowDeiconified(WindowEvent e){}
        @Override
        public void windowActivated(WindowEvent e){}
        @Override
        public void windowDeactivated(WindowEvent e){}
    }


//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        TableCompany inst = new TableCompany();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
