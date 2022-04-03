package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos Valadao
 */
public class TelaInicialController {

    @FXML
    private Button btAutoatendimento;

    @FXML
    private Button btEntrar;

    @FXML
    public void Autoatendimento() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login/LoginAutoatendimento.fxml"));
        Stage stage;
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void TelaLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login/TelaLogin.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btAutoatendimento.getScene().getWindow();
        stage.close();
    }
}
