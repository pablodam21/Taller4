package com.pablo.u3t4event;

import android.widget.DatePicker;
import android.widget.TimePicker;

public class Event {

    private String place;

    private String priority;

    private DatePicker date;

    private TimePicker time;

    public Event(String place, String priority, DatePicker date, TimePicker time) {
        this.place = place;
        this.priority = priority;
        this.date = date;
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }

    public TimePicker getTime() {
        return time;
    }

    public void setTime(TimePicker time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "place='" + place + '\'' +
                ", priority='" + priority + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
