/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logic;



/*******************************************************************************
 * Instances of enumerated type {@code TypStudia} represents ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public enum TypeOfStudy
{
//== VALUES OF THE ENUMERATION TYPE ============================================

    bachelor("Bakalářský"),

    master("Magisterský"),

    doctor("Doktorský");


//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final String nameCZ;


//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================

    public static TypeOfStudy getInstance(String nameCZ){
        switch (nameCZ){
            case "Bakalářský" :  return bachelor;
            case "Magisterský" : return master;
            case "Doktorský" : return doctor;
            default : return null;
        }
    }
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    private TypeOfStudy(String nameCZ)
    {
        this.nameCZ = nameCZ;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================


    /***************************************************************************
     *
     * @return
     */
    public String getNameCZ(){
        return nameCZ;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
