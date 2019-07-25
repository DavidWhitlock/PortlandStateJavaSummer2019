package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AbstractAppointment;

public class Appointment extends AbstractAppointment {
  private final String description;
  private final String beginTimeString;
  private final String endTimeString;

  public Appointment(String description, String beginTime, String endTime) {
    this.description = description;
    beginTimeString = beginTime;
    endTimeString = endTime;
  }

  @Override
  public String getBeginTimeString() {
    return this.beginTimeString;
  }

  @Override
  public String getEndTimeString() {
    return this.endTimeString;
  }

  @Override
  public String getDescription() {
    return this.description;
  }
}
