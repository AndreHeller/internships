/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package main;

import graphic.Gui;



/*******************************************************************************
 * Instace trídy {@code Api} je jedináček (Singleton). Představuje hlavní
 * aplikační rozhraní, připravené na budoucí implemetnaci dalších služeb.
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 1.00 — 06/2013
 */
public class Api
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Vytvořená instance jedináčka */
    private static final Api INSTANCE = new Api();

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Tovární metoda vracející jedináčka
     *
     * @return odkaz na jedináčka
     */
    public static Api getInstance(){
        return INSTANCE;
    }

    /***************************************************************************
     * Privátní konstuktor, zabraňující vytvoření intancí zvenku.
     */
    private Api()
    {
        start();
    }

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    /***************************************************************************
     * Startovní inicializační metoda. Vytvoří instanci Gui a následně
     * ho inicializuje
     */
    public static void start(){
        Gui gui = new Gui();
        gui.initialize();
    }


//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        Api inst = getInstance();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
