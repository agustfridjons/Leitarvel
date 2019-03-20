/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.torg.vidmot;

import is.hi.torg.vinnsla.DagskraKatalogur;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class DagskraController implements Initializable {
    
    @FXML
    private Label label;
    
    private DagskraKatalogur minnKatalogur;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        minnKatalogur = new DagskraKatalogur();
        minnKatalogur.birtaDagskrarlid();
    }    
    
}
