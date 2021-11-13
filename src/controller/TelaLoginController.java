/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author --
 */
public class TelaLoginController {


    @FXML
    private Button bEntrar;

//    @FXML
//    private ToggleGroup tipoUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtSenha;

    
    public void logar() {
//        if(pegarSelecionado().equals("Recepcionista"))
//            carregarTelaRecepcionista();
        //carregarTelaMedico();
    }
 
    
//    private String pegarSelecionado() {
//        RadioButton selecionado = (RadioButton) tipoUsuario.getSelectedToggle();
//        return selecionado.getText();
//    }
    
    
    private void carregarTelaRecepcionista() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaRecepcionista.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarTelaMedico() {
         try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaMedico.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
