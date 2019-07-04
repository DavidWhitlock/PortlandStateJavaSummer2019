package edu.pdx.cs410J.whitlock;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.lang.Human;

import java.util.List;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  private final double gpa;
  private final Gender gender;
  private final List<String> classes;

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
  public Student(String name, List<String> classes, double gpa, Gender gender) {
    super(name);

    if (name == null) {
      throw new NullPointerException("Name cannot be null");
    }

    if (gpa < 0.0) {
      throw new GPAOutOfBoundsException("GPA cannot be less than zero");

    } else if (gpa > 4.0) {
      throw new GPAOutOfBoundsException("GPA cannot be more than 4.0");
    }

    this.gpa = gpa;

    this.gender = gender;

    this.classes = List.copyOf(classes);
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
    String s = getName() + " has a GPA of " + gpa + " and is taking " + this.classes.size() + " classes: ";
    s += formatClasses(this.classes);
    return s;
  }

  @VisibleForTesting
  static String formatClasses(List<String> classes) {
    int numberOfClasses = classes.size();
    if (numberOfClasses == 0) {
      return "";

    } else if (numberOfClasses == 1) {
      return classes.get(0);

    } else if (numberOfClasses == 2) {
      return classes.get(0) + " and " + classes.get(1);

    } else {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < numberOfClasses - 1; i++) {
        String className = classes.get(i);
        sb.append(className);
        sb.append(", ");
      }

      sb.append("and ");
      sb.append(classes.get(numberOfClasses - 1));
      return sb.toString();
    }
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
      case "male":
        return Gender.MALE;
      case "other":
        return Gender.OTHER;
      default:
        return null;
    }
  }

  public Gender getGender() {
    return gender;
  }

  @VisibleForTesting
  List<String> getClasses() {
    return this.classes;
  }
}