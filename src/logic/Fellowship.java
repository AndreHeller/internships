/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;





/*******************************************************************************
 * Instances of class {@code Fellowship} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class Fellowship
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    public static final Map<String, Fellowship> NAME_2_FELLOWSHIP = new HashMap<>();

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private String name;

    private Countries country;

    private int lenght;

    private int vacancy;

    private Company company;


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Fellowship(String name, Countries country, int lenght, int vacancy, Company company)
    {
        this.name = name;
        this.country = country;
        this.lenght = lenght;
        this.vacancy = vacancy;
        this.company = company;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     *
     * @return
     */
    public String getName(){
        return name;
    }


    /***************************************************************************
     *
     * @return
     */
    public Countries getCountry(){
        return country;
    }


    /***************************************************************************
     *
     * @return
     */
    public int getLenght(){
        return lenght;
    }


    /***************************************************************************
     *
     * @return
     */
    public int getVacancy(){
        return vacancy;
    }


    /***************************************************************************
     *
     * @return
     */
    public Company getCompany(){
        return company;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setName(String name){
        this.name = name;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setCountry(Countries country){
        this.country = country;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setLenght(int lenght){
        this.lenght = lenght;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setVacancy(int vacancy){
        this.vacancy = vacancy;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setCompany(Company company){
        this.company = company;
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
//        Fellowship inst = new Fellowship();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
