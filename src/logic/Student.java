/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logic;

import java.util.HashMap;
import java.util.Map;



/*******************************************************************************
 * Instances of class {@code Student} represent ...
 *
 * @author Patrik Hoffmann a Jiří Červený
 * @version 0.00 — mm/20yy
 */
public class Student
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    public static final Map<String, Student> NAME_2_STUDENT = new HashMap<>();

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private String firstName;

    private String lastName;

    private String contact;

    private String xname;

    private TypeOfStudy typeOfStudy;

    private Fellowship fellowship;


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Student(String firstName, String lastName, String contact, TypeOfStudy typeOfStudy, Fellowship fellowship)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.typeOfStudy = typeOfStudy;
        this.fellowship = fellowship;


        String initXName = "x"+lastName.substring(0, 3)+firstName.charAt(0);
        initXName = initXName.toLowerCase();
        int xnameCount = 0;

        for(Student student : NAME_2_STUDENT.values()){
            if(student.getNameFromXName().equals(initXName)){
                xnameCount++;
            }
        }

        if(xnameCount/10 == 0){
            this.xname = initXName+"0"+xnameCount;
        }
        else {
            this.xname = initXName+xnameCount;
        }
    }

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     *
     * @return
     */
    public String getFirstName(){
        return firstName;
    }


    /***************************************************************************
     *
     * @return
     */
    public String getLastName(){
        return lastName;
    }


    /***************************************************************************
     *
     * @return
     */
    public String getAllName(){
        return firstName + " " + lastName;
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
    public TypeOfStudy getTypeOfStudy(){
        return typeOfStudy;
    }


    /***************************************************************************
     *
     * @return
     */
    public Fellowship getFellowship(){
        return fellowship;
    }


    /***************************************************************************
     *
     * @return
     */
    public String getNameFromXName(){
        return xname.substring(0, 5);
    }


    /***************************************************************************
     *
     * @return
     */
    public String getNumberFromXName(){
        return xname.substring(5);
    }


    /***************************************************************************
     *
     * @return
     */
    public String getAllXName(){
        return xname;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
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
    public void setTypeOfStudy(TypeOfStudy typeOfStudy){
        this.typeOfStudy = typeOfStudy;
    }


    /***************************************************************************
     *
     * @return
     */
    public void setFellowship(Fellowship fellowship){
        this.fellowship = fellowship;
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
//        Student inst = new Student();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
