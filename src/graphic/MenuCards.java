/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;





/*******************************************************************************
 * Instances of class {@code MenuCards} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class MenuCards extends JMenu
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final Gui gui;

    public final Cards cards;

//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private JMenuItem cardsEditing;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public MenuCards(Gui gui, Cards cards)
    {
        super("Karty");
        setMnemonic('K');

        this.gui = gui;
        this.cards = cards;

        createCardsEditingItem();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    private void createCardsEditingItem()
    {
        cardsEditing = new JMenuItem("Zobrazení a úprava karet");
        cardsEditing.setMnemonic('Z');
        cardsEditing.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                cards.setVisible(true);
            }
        });
        add(cardsEditing);
    }


//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        MenuCards inst = new MenuCards();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
