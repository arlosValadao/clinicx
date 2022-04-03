package controller.login;

import controller.paciente.TelaAutoatendimentoController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gerenciador;
import model.Paciente;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class LoginAutoatendimentoController {

    @FXML
    private Button bEntrar;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNome;

    @FXML
    public void logar() throws IOException {
        long login = Long.valueOf(txtCPF.getText());
        String senha = txtNome.getText();
        int loginPaciente = Gerenciador.autenticarPaciente(login, senha);
        switch (loginPaciente) {
            case 0:
                carregarTelaAutoatendimento();
                break;
            case 1:
                mostrarMsg("Login ou senha incorretos", Alert.AlertType.ERROR);
                break;
            case 2:
                mostrarMsg("Você não está cadastrado\n Digira-se à recepção", Alert.AlertType.ERROR);
        }
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }

    private void carregarTelaAutoatendimento() throws IOException {
        long cpfPaciente = Long.valueOf(txtCPF.getText());
        Paciente paciente;
        paciente = Gerenciador.buscarPaciente(cpfPaciente);

        FXMLLoader TALoader = new FXMLLoader(getClass().getResource("/view/paciente/TelaAutoatendimento.fxml"));

        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(TALoader.load()));

        TelaAutoatendimentoController controller = TALoader.getController();
        controller.setPacienteLogado(paciente);
        controller.initialize();
        stage.show();
    }
}
