package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
public class StudentIT extends InvokeMainTestCase {
  @Test
  public void invokingMainWithNoArgumentsHasExitCodeOf1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Test
  public void whenGPAIsNotValidPrintErrorMessageToStandardError() {
    String gpa = "gpa";
    MainMethodResult result = invokeMain(Student.class, "Name", "gender", gpa, "class1");
    assertThat(result.getTextWrittenToStandardError(), containsString("Invalid gpa: " + gpa));
  }

  @Ignore
  @Test
  public void commandLineArgumentsFromAssignmentPrintExpectedStudentInformation() {
    MainMethodResult result = invokeMain(Student.class, "Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating\n" +
      "Systems, and Java. He says \"This class is too much work\"."));
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void unsupportedGenderPrintsErrorMessage() {
    MainMethodResult result = invokeMain(Student.class, "Name", "Unsupported", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getTextWrittenToStandardError(), containsString("Unsupported gender"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void femaleGenderReturnExitCodeZero() {
    MainMethodResult result = invokeMain(Student.class, "Name", "female", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void maleGenderReturnExitCodeZero() {
    MainMethodResult result = invokeMain(Student.class, "Name", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void otherGenderReturnExitCodeZero() {
    MainMethodResult result = invokeMain(Student.class, "Name", "other", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getExitCode(), equalTo(0));
  }

}
