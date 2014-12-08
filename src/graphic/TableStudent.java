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

import logic.Student;





/*******************************************************************************
 * Instances of class {@code TableStudent} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class TableStudent extends JFrame
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final int FRAME_WIDTH = 640;

    private static final int FRAME_HEIGHT = 480;


//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final JFrame mainFrame;

//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private final JTable studentTable = new JTable();

    private final JPanel northPanel = new JPanel();

    private final JPanel infoPanel = new JPanel();

    private final JLabel studentCount = new JLabel("Počet studentů: ");

    private final JLabel studentCountText = new JLabel();

    private final JButton actualizeButton = new JButton("Aktualizovat");

    private final JScrollPane scrollpane = new JScrollPane();

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public TableStudent(JFrame mainFrame)
    {
        super("Tabulka studentů");
        this.mainFrame = mainFrame;

        setFrame();

        studentTable.setBackground(new Color(255, 255, 204));
        studentTable.setGridColor(new Color(0, 0, 0));
        studentTable.setShowGrid(true);

        infoPanel.add(studentCount);
        infoPanel.add(studentCountText);

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
        if(!Student.NAME_2_STUDENT.isEmpty()){
            String[] header = {"XNAME", "Jméno", "Příjmení", "Kontakt", "Typ studia", "Stáž"};
            String[][] data = new String[Student.NAME_2_STUDENT.size()][header.length];
            int i = 0;

            studentCountText.setText(Student.NAME_2_STUDENT.size()+"");

            for(Student student : Student.NAME_2_STUDENT.values()){

                data[i][0] = student.getAllXName();
                data[i][1] = student.getFirstName();
                data[i][2] = student.getLastName();
                data[i][3] = student.getContact();
                data[i][4] = student.getTypeOfStudy().getNameCZ();
                if(student.getFellowship() != null){
                    data[i][5] = student.getFellowship().getName();
                }
                else{
                    data[i][5] = "";
                }
                i++;
            }
            studentTable.setModel(new DefaultTableModel(data, header){
                boolean[] canEdit = new boolean []
                {
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit [columnIndex];
                };
            });
            scrollpane.setViewportView(studentTable);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Dosud nebyl vytvořen žádný student.");
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
            TableStudent.this.setVisible(false);
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
//        TableStudent inst = new TableStudent();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
