package model;

import java.util.*;
import javafx.beans.property.*; 
import javafx.collections.*;

public class Activity {
    private Subject subject;
    private StringProperty group = new SimpleStringProperty();
    private IntegerProperty number = new SimpleIntegerProperty();
    private StringProperty day = new SimpleStringProperty();
    private IntegerProperty start = new SimpleIntegerProperty();
    private IntegerProperty duration = new SimpleIntegerProperty();
    private StringProperty room = new SimpleStringProperty();
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private IntegerProperty enrolled = new SimpleIntegerProperty();

    public Activity(Subject subject, String group, int number, String day, int start, int duration, String room, int capacity) {
        this.subject = subject;
        this.group.set(group);
        this.number.set(number);
        this.day.set(day);
        this.start.set(start);
        this.duration.set(duration);
        this.room.set(room);
        this.capacity.set(capacity);
        enrolled.set(0);
    }

    public Subject getSubject() { return subject; }
    public int getSubjectNumber() { return subject.getNumber(); }
    //public IntegerProperty getSubjectNumberProperty(){ return subject.numberProperty();}
    public String getGroup() { return group.get(); }
    //public StringProperty groupProperty() { return group; }
    public int getNumber() { return number.get(); }
    //public IntegerProperty numberProperty() { return number; }
    public String getDay() { return day.get(); }
    //public StringProperty dayProperty() { return day; }
    public int getStart() { return start.get(); }
    //public IntegerProperty startProperty() { return start; }
    public int getDuration() { return duration.get(); }
    //public IntegerProperty durationProperty() { return duration; }
    public String getRoom() { return room.get(); }
    //public StringProperty roomProperty() { return room; }
    public int getCapacity() { return capacity.get(); }
    //public IntegerProperty capacityProperty() { return capacity; }
    public int getEnrolled() { return enrolled.get(); }
    public IntegerProperty enrolledProperty() { return enrolled; }

    public boolean canEnrol() {
        return enrolled.get() < capacity.get();
    }

    public void enrol() {
        enrolled.set(getEnrolled() + 1);
    }

    public void withdraw() {
        enrolled.set(getEnrolled() - 1);
    }

    public boolean matches(int subjectNumber, String group) {
        return subject.matches(subjectNumber) && group.equals(this.group);
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %s %s %02d:00 %dhrs %d/%d", subject.getNumber(), group, number, day, room, start, duration, enrolled, capacity);
    }
}
