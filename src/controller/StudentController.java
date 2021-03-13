/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author prici
 */

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.*;
import javafx.fxml.*;
import model.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.collections.*; 

public class StudentController extends Controller<Student>{
    @FXML private ComboBox<Subject> subjectCmb;
    @FXML private Button enrolBtn; 
    @FXML private Button withdrawBtn; 
    @FXML private Button logoutBtn; 
    @FXML private TableView<Activity> studentActTv;
    @FXML private TableView<Activity> enrolledActTv;
    
    @FXML private void initialize() {
       
       subjectCmb.getSelectionModel().selectedItemProperty().addListener(
            (obs, o, n) -> {
               enrolledActTv.setItems(n.getActivities());
       });
       
       enrolledActTv.getSelectionModel().selectedItemProperty().addListener(
            (obs, o, n) -> enrolBtn.setDisable(!n.canEnrol()));
       
       studentActTv.setItems(getStudent().getActivities());
       studentActTv.getSelectionModel().selectedItemProperty().addListener(
            (obs, o, n) -> withdrawBtn.setDisable(n == null));
    }
    
    public final Student getStudent() { return model; } 
    
    public final University getUniversity() {return getStudent().getUniversity(); }
    
    private Activity getEnrolledAct() { return enrolledActTv.getSelectionModel().getSelectedItem();}
    
    private Activity getWithdrawAct() { return studentActTv.getSelectionModel().getSelectedItem();}
    
    @FXML public void handleWithdraw() {
        getStudent().withdraw(getWithdrawAct());
        withdrawBtn.setDisable(true);
        studentActTv.getSelectionModel().clearSelection();
    }
    
    @FXML public void handleEnrol() {
        getStudent().enrol(getEnrolledAct());
        enrolBtn.setDisable(true);
    }
    
    @FXML public void handleLogout() {
        stage.close(); 
    }
    
}
