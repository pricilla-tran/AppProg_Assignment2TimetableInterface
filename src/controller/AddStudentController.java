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

public class AddStudentController extends Controller<University> {
     @FXML private TextField StudnumTf;
    @FXML private TextField StudnameTf;
    @FXML private ToggleGroup AttendanceTg; 
    @FXML private CheckBox scholarshipCb;
    @FXML private Button addBtn;
    @FXML private Button cancelBtn;
    @FXML private Text errorTxt; 
    
    @FXML 
    public void initialize() { 
    StudnumTf.textProperty().addListener((o, oldText, newText) -> updateAddBtn());
    StudnameTf.textProperty().addListener((o, oldText, newText) -> updateAddBtn());
    AttendanceTg.selectedToggleProperty().addListener((observable, oldSubject, newSubject) -> updateAddBtn());
    }
    
    public final University getUniversity() { return model; }
    
    private String getStudNum() { return StudnumTf.getText(); }
    
    private String getStudName() { return StudnameTf.getText(); }
    
    private String getAttendance() { return AttendanceTg.getSelectedToggle().getUserData().toString(); }
    
    private boolean getScholarship() { 
        if(scholarshipCb.isSelected()){
        return true;
        }
        else {
        return false;
        }
    }
    
    private void updateAddBtn() {
        addBtn.setDisable(!(isNumNameValid() && isAttendanceValid()));
    }
    
    private boolean isNumNameValid() {
        return (getStudNum().length() > 0) && (getStudName().length() > 0);
    }
    
    private boolean isAttendanceValid() {
        return AttendanceTg.getSelectedToggle() != null;
    }
    
    @FXML private void handleAdd(ActionEvent event) throws Exception {
        try {
            getUniversity().addStudent(getStudNum(), getStudName(), getAttendance(), getScholarship()); 
            stage.close();
        } catch (Exception e) {
            errorTxt.setText("Student already exists");
        } 
        
    }
    
    @FXML private void handleCancel(ActionEvent event){
        stage.close();
    }
}
