package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AppointmentBookServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AppointmentBookServletTest {

  @Test
  public void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    int expectedWords = 0;
    verify(pw).println(Messages.formatWordCount(expectedWords));
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  public void addOneWordToDictionary() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String word = "TEST WORD";
    String definition = "TEST DEFINITION";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("word")).thenReturn(word);
    when(request.getParameter("definition")).thenReturn(definition);

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    verify(pw).println(Messages.definedWordAs(word, definition));
    verify(response).setStatus(HttpServletResponse.SC_OK);

    assertThat(servlet.getDefinition(word), equalTo(definition));
  }

  @Test
  public void lookingUpUnknownWordReturnNotFound() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("word")).thenReturn("unknownWord");

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);

  }

}
