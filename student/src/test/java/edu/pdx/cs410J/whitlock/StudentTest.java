package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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

  @Test
  public void toStringContainsThreeClasses() {
    List<String> classes = List.of("Algorithms", "Operating Systems", "Java");
    Student student = new Student("Name", classes, 1.23, Gender.OTHER);
    assertThat(student.toString(), containsString("is taking 3 classes: Algorithms, Operating Systems, and Java"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void cannotModifyAStudentsClasses() {
    List<String> classes = new ArrayList<>();
    String className = "Algorithms";
    classes.add(className);
    Student student = new Student("Name", classes, 1.23, Gender.OTHER);
    student.getClasses().remove(0);
  }

  @Test
  public void modifyingClassesListDoesNotImpactStudentsClasses() {
    List<String> classes = new ArrayList<>();
    String className = "Algorithms";
    classes.add(className);
    Student student = new Student("Name", classes, 1.23, Gender.OTHER);
    classes.remove(0);
    assertThat(student.getClasses(), contains(className));
  }

  @Test
  public void formatOneClass() {
    String className = "ClassName";
    String s = Student.formatClasses(List.of(className));
    assertThat(s, equalTo("ClassName"));
  }

  @Test
  public void formatTwoClasses() {
    String s = Student.formatClasses(List.of("One", "Two"));
    assertThat(s, equalTo("One and Two"));
  }

  @Test
  public void formatThreeClasses() {
    String s = Student.formatClasses(List.of("One", "Two", "Three"));
    assertThat(s, equalTo("One, Two, and Three"));
  }

  @Test
  public void formatFourClasses() {
    String s = Student.formatClasses(List.of("One", "Two", "Three", "Four"));
    assertThat(s, equalTo("One, Two, Three, and Four"));
  }

  @Test
  public void formatZeroClasses() {
    String s = Student.formatClasses(List.of());
    assertThat(s, equalTo(""));
  }

}
