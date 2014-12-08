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
import logic.Fellowship;






/*******************************************************************************
 * Instances of class {@code TableFellowship} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class TableFellowship extends JFrame
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final int FRAME_WIDTH = 640;

    private static final int FRAME_HEIGHT = 480;


//== VARIABLE CLASS ATTRIBUTES =================================================

    private final JFrame mainFrame;

//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final JTable fellowshipTable = new JTable();

    private final JPanel northPanel = new JPanel();

    private final JPanel infoPanel = new JPanel();

    private final JLabel fellowshipCount = new JLabel("Počet společností: ");

    private final JLabel fellowshipCountText = new JLabel();

    private final JButton actualizeButton = new JButton("Aktualizovat");

    private final JScrollPane scrollpane = new JScrollPane();

//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public TableFellowship(JFrame mainFrame)
    {
        super("Tabulka společností");
        this.mainFrame = mainFrame;

        setFrame();

        fellowshipTable.setBackground(new Color(255, 255, 204));
        fellowshipTable.setGridColor(new Color(0, 0, 0));
        fellowshipTable.setShowGrid(true);

        infoPanel.add(fellowshipCount);
        infoPanel.add(fellowshipCountText);

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
        if(!Fellowship.NAME_2_FELLOWSHIP.isEmpty()){
            String[] header = {"Název", "Společnost", "Délka (týdny)", "Volná místa", "Země"};
            String[][] data = new String[Fellowship.NAME_2_FELLOWSHIP.size()][header.length];
            int i = 0;

            fellowshipCountText.setText(Fellowship.NAME_2_FELLOWSHIP.size()+"");

            for(Fellowship fellowship : Fellowship.NAME_2_FELLOWSHIP.values()){

                data[i][0] = fellowship.getName();
                data[i][1] = fellowship.getCompany().getName();
                data[i][2] = fellowship.getLenght()+"";
                data[i][3] = fellowship.getVacancy()+"";
                data[i][4] = fellowship.getCountry().name();

                i++;
            }
            fellowshipTable.setModel(new DefaultTableModel(data, header){
                boolean[] canEdit = new boolean []
                {
                    false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit [columnIndex];
                };
            });
            scrollpane.setViewportView(fellowshipTable);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Dosud nebyla vytvořena žádná stáž.");
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
            TableFellowship.this.setVisible(false);
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
//        TableFellowship inst = new TableFellowship();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
