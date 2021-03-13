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
import javafx.stage.*;

public class UniversityController extends Controller<University>{
    @FXML ListView<Student> studentLv; 
    @FXML Button addStudentBtn; 
    @FXML Button removeStudentBtn;
    @FXML Button loginBtn; 
    
    @FXML public void initialize() { 
        studentLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> removeStudentBtn.setDisable(newSubject == null));
        studentLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> loginBtn.setDisable(newSubject == null));
    }
    
    public final University getUniversity() { return model; }
    
    private Student getSelectedStudent() {
        return studentLv.getSelectionModel().getSelectedItem();
    }
    
    @FXML private void handleAddStud(ActionEvent event) throws Exception{
       ViewLoader.showStage(getUniversity(), "/view/add_student.fxml", "Add Student", new Stage());
    }
    
    @FXML private void handleRemoveStud(ActionEvent event) {
        getUniversity().remove(getSelectedStudent());
    }
    
    @FXML private void handleLogin(ActionEvent event) throws Exception {
        ViewLoader.showStage(getSelectedStudent(), "/view/student.fxml", "Student", new Stage());
    }
    
}
