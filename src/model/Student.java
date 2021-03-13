package model;

import java.util.*;
import javafx.beans.property.*; 
import javafx.collections.*;

public class Student {
    private University university;
    private final String number;
    private final String name;
    private final String attendance;
    private BooleanProperty scholarship = new SimpleBooleanProperty();
    private ObservableList<Activity> activities = FXCollections.observableArrayList();

    public Student(University university, String number, String name, String attendance, boolean scholarship) {
        this.university = university;
        this.number = number;
        this.name = name;
        this.attendance = attendance;
        this.scholarship.set(scholarship);
    }

    public University getUniversity() { return university; }
    public String getNumber() { return number; }
    public String getName() { return name; }
    public String getAttendance() { return attendance; }
    public String getScholarship() { 
        if (scholarship.get()) {
        return "Yes"; 
        }
        return "No"; 
    }
    
    public ObservableList<Activity> getActivities() { return activities; }

    public boolean isEnrolledIn(Activity activity) {
        return activities.contains(activity);
    }

    public boolean matches(String number) {
        return this.number.equals(number);
    }

    public void enrol(Activity activity) {
        // You should first check if the student is already enrolled
        // in an existing activity with the same subject and group.
        // If so, the student should be withdrawn from that activity first.
        // See Lecture 5 for hints.
        if (activity.canEnrol()) {
            Activity oldActivity = checkGroup(activity);
            if (oldActivity != null) {
                oldActivity.withdraw();
                activities.remove(oldActivity);   
            }
            activities.add(activity);
            activity.enrol();
        }
    }

    public Activity checkGroup(Activity tempActivity) {
       for (Activity activity: activities) {
           if (tempActivity.getSubject() == activity.getSubject() && tempActivity.getGroup().equals(activity.getGroup())) {
               return activity; 
           }
       } 
       return null; 
    }
    
    public void withdraw(Activity activity) {
        activities.remove(activity);
        activity.withdraw();
    }
    
    public void withdrawActivities() {
        for (Activity activity : activities) {
            activity.withdraw();
        }    
    }
    
    // This lookup function should be useful to check if a student is
    // already enrolled in an activity.
    private Activity activity(int subjectNumber, String group) {
        for (Activity activity : activities)
            if (activity.matches(subjectNumber, group))
                return activity;
        return null;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }
}
