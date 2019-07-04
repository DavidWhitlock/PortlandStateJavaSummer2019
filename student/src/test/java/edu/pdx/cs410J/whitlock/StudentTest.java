package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  private Student createStudentNamed(String name) {
    return new Student(name, new ArrayList<>(), 0.0, Gender.OTHER);
  }

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = createStudentNamed(name);
    assertThat(pat.getName(), equalTo(name));
  }

  @Test(expected = NullPointerException.class)
  public void whenNameIsNullThrowANullPointerException() {
    String name = null;
    createStudentNamed(name);
  }


  @Test(expected = GPAOutOfBoundsException.class)
  public void whenGPAIsLessThanZeroThrowGPAOutOfBoundsException() {
    double gpa = -1.0;
    new Student("name", new ArrayList<>(), gpa, Gender.OTHER);
  }

  @Test(expected = GPAOutOfBoundsException.class)
  public void whenGPAIsGreaterThanFourThrowGPAOutOfBoundsException() {
    double gpa = 4.1;
    new Student("name", new ArrayList<>(), gpa, Gender.OTHER);
  }

  @Test
  public void whenGenderIsFemaleStudentIsFemale() {
    Student student = new Student("name", new ArrayList<>(), 1.0, Gender.FEMALE);
    assertThat(student.getGender(), equalTo(Gender.FEMALE));
  }

  @Test
  public void whenGenderIsMaleStudentIsMale() {
    Student student = new Student("name", new ArrayList<>(), 1.0, Gender.MALE);
    assertThat(student.getGender(), equalTo(Gender.MALE));
  }

  @Test
  public void whenGenderIsOtherStudentIsOther() {
    Student student = new Student("name", new ArrayList<>(), 1.0, Gender.OTHER);
    assertThat(student.getGender(), equalTo(Gender.OTHER));
  }

  @Test
  public void toStringContainsStudentName() {
    String name = "Name";
    Student student = createStudentNamed(name);
    assertThat(student.toString(), containsString(name));
  }

  @Test
  public void toStringContainsGpa() {
    Student student = new Student("Name", new ArrayList<>(), 1.23, Gender.OTHER);
    assertThat(student.toString(), containsString(" has a GPA of 1.23"));
  }

}
