/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;







/*******************************************************************************
 * Instances of class {@code Spolecnost} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class Company
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    public static final Map<String, Company> NAME_2_COMPANY = new HashMap<>();

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private String name;

    private int id;

    private String contact;

    private Address address;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Company(String name, int id, String contact, String street, String city, int ZIPCode, String country)
    {
        this.name = name;
        this.id = id;
        this.contact = contact;
        this.address = new Address(street, city, ZIPCode, country);
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
    public int getId(){
        return id;
    }


    /***************************************************************************
     *
     * @return
     */
    public String getContact(){
        return contact;
    }


    /***************************************************************************
     *
     * @return
     */
    public Address getAddress(){
        return address;
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
    public void setId(int id){
        this.id = id;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setContact(String contact){
        this.contact = contact;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setAddress(Address address){
        this.address = address;
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
//        Spolecnost inst = new Spolecnost();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
