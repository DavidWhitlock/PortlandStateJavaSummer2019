package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
  private Collection<Appointment> appointments = new ArrayList<>();
  private final String ownerName;

  public AppointmentBook(String owner) {
    this.ownerName = owner;
  }

  @Override
  public String getOwnerName() {
    return this.ownerName;
  }

  @Override
  public Collection<Appointment> getAppointments() {
    return this.appointments;
  }

  @Override
  public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);
  }
}
