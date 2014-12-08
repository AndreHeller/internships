/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logic;



/*******************************************************************************
 * Instances of class {@code Adress} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class Address
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private String street;

    private String city;

    private int ZIPCode;

    private String country;

    
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Address(String street, String city, int ZIPCode, String country)
    {
        this.street = street;
        this.city = city;
        this.ZIPCode = ZIPCode;
        this.country = country;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     *
     * @return
     */
    public String getStreet(){
        return street;
    }


    /***************************************************************************
     *
     * @return
     */
    public String getCity(){
        return city;
    }


    /***************************************************************************
     *
     * @return
     */
    public int getZIPCode(){
        return ZIPCode;
    }


    /***************************************************************************
     *
     * @return
     */
    public String getCountry(){
        return country;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setStreet(String street){
        this.street = street;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setCity(String city){
        this.city = city;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setZIPCode(int ZIPCode){
        this.ZIPCode = ZIPCode;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setCountry(String country){
        this.country = country;
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
//        Adress inst = new Adress();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
