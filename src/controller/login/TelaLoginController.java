package controller.login;

import controller.medico.TelaMedicoController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gerenciador;
import model.Medico;
import model.TipoUsuario;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class TelaLoginController {

    @FXML
    private Button bEntrar;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtSenha;

    @FXML
    public void logar() throws IOException {
        long login = Long.valueOf(this.txtCPF.getText());
        String senha = txtSenha.getText();
        int tipoUsuario = Gerenciador.identificarTipoUsuario(login);
        switch (tipoUsuario) {
            case TipoUsuario.MEDICO:
                int status = Gerenciador.autenticarMedico(login, senha);
                switch (status) {
                    case 0:
                        carregarTelaMedico();
                        break;
                    case 1:
                        mostrarMsg("Login e/ou senha incorreto(s)", Alert.AlertType.ERROR);
                        break;
                    case 2:
                        mostrarMsg("Login e/ou senha incorreto(s)", Alert.AlertType.ERROR);
                }
                break;
            case TipoUsuario.RECEPCIONISTA:
                status = Gerenciador.autenticarRecepcionista(login, senha);
                switch (status) {
                    case 0:
                        carregarTelaRecepcionista();
                        break;
                    case 1:
                        mostrarMsg("Login e/ou senha incorreto(s)", Alert.AlertType.ERROR);
                        limparEntradas();
                        break;
                    case 2:
                        mostrarMsg("Login e/ou senha incorreto(s)", Alert.AlertType.ERROR);
                        limparEntradas();
                }
        }
        fecharJanela();
    }

    private void limparEntradas() {
        this.txtCPF.setText(null);
        this.txtSenha.setText(null);
    }

    private void fecharJanela() {
        Stage stage = (Stage) bEntrar.getScene().getWindow();
        stage.close();
    }

    private void carregarTelaRecepcionista() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/recepcionista/TelaRecepcionista.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void carregarTelaMedico() throws IOException {
        long cpfMedico = Long.valueOf(txtCPF.getText());
        Medico medicoLogado = Gerenciador.buscarMedico(cpfMedico);
        FXMLLoader TMLoader = new FXMLLoader(getClass().getResource("/view/medico/TelaMedico.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(TMLoader.load());
        stage.setScene(scene);

        TelaMedicoController controller = TMLoader.getController();
        controller.setMedicoLogado(medicoLogado);
        controller.carregarDados();

        stage.show();
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }

}
