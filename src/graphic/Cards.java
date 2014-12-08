/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package graphic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;





/*******************************************************************************
 * Instances of class {@code Gui} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class Cards extends JFrame
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final int FRAME_WIDTH = 800;

    private static final int FRAME_HEIGHT = 600;

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final JFrame mainFrame;

    final PanelStudent studentPanel = new PanelStudent(this);

    final PanelCompany companyPanel = new PanelCompany(this);

    final PanelFellowship fellowshipPanel = new PanelFellowship(this);

//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Cards(JFrame mainFrame)
    {
        super("Stáž pro studen");

        this.mainFrame = mainFrame;


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

        setLayout(new GridLayout(1, 3, 30, 15));

        add(studentPanel);
        add(companyPanel);
        add(fellowshipPanel);

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

//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
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
            Cards.this.setVisible(false);
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
//        Cards inst = new Cards();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
