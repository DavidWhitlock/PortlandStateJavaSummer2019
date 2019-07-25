package edu.pdx.cs410J.whitlock;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>AppointmentBook</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple key/value pairs.
 */
public class AppointmentBookServlet extends HttpServlet
{
    static final String OWNER_PARAMETER = "owner";
    static final String DESCRIPTION_PARAMETER = "description";
    static final String BEGIN_TIME_PARAMETER = "beginTime";
    static final String END_TIME_PARAMETER = "endTime";

    private final Map<String, AppointmentBook> appointmentBooks = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String parameter = OWNER_PARAMETER;
        String owner = getRequiredParameter(request, response, parameter);
        if (owner == null) return;

        String description = getRequiredParameter(request, response, DESCRIPTION_PARAMETER);
        if (description == null) return;

        String beginTime = getRequiredParameter(request, response, BEGIN_TIME_PARAMETER);
        if (beginTime == null) return;

        String endTime = getRequiredParameter(request, response, END_TIME_PARAMETER);
        if (endTime == null) return;

        AppointmentBook book = new AppointmentBook(owner);
        this.appointmentBooks.put(owner, book);

        Appointment appt = new Appointment(description, beginTime, endTime);
        book.addAppointment(appt);

        response.setStatus( HttpServletResponse.SC_OK);
    }

    private String getRequiredParameter(HttpServletRequest request, HttpServletResponse response, String parameter) throws IOException {
        String value = getParameter(parameter, request);
        if (value == null) {
            missingRequiredParameter(response, parameter);
            return null;
        }
        return value;
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        this.appointmentBooks.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

    @VisibleForTesting
    AppointmentBook getAppointmentBook(String word) {
        return this.appointmentBooks.get(word);
    }
}
