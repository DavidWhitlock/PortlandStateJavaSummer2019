package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
                                                                                    
/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  private Gender gender;

  /**
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male", "female", or "other", case insensitive)
   */                                                                               
  public Student(String name, ArrayList<String> classes, double gpa, Gender gender) {
    super(name);

    if (name == null) {
      throw new NullPointerException("Name cannot be null");
    }

    if (gpa < 0.0) {
      throw new GPAOutOfBoundsException("GPA cannot be less than zero");

    } else if (gpa > 4.0) {
      throw new GPAOutOfBoundsException("GPA cannot be more than 4.0");
    }

    this.gender = gender;
  }

  /**
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    }

    String gpaString = args[2];
    try {
      double gpa = Double.parseDouble(gpaString);

    } catch (NumberFormatException ex) {
      System.err.println("Invalid gpa: " + gpaString);
    }

    String genderString = args[1];
    Gender gender = genderFromString(genderString);
    if (gender == null) {
      System.err.println("Unsupported gender");
      System.exit(1);
    }


    System.exit(0);
  }

  private static Gender genderFromString(String genderString) {
    switch (genderString.toLowerCase()) {
      case "female":
        return Gender.FEMALE;
//      case "male":
//        return Gender.MALE;
//      case "other":
//        return Gender.OTHER;
      default:
        return null;
    }
  }

  public Gender getGender() {
    return gender;
  }

}