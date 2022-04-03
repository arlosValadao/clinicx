package controller.recepcionista;

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
 * @author Carlos Valadão
 */
public class TelaRecepcionistaController {

    private FXMLLoader TCPLoader;

    @FXML
    private Button btCadastrarEspecialidade;

    @FXML
    private Button btCadastrarMedico;

    @FXML
    private Button btCadastrarPaciente;

    @FXML
    private Button btListarEspecialidades;

    @FXML
    private Button btListarMedico;

    @FXML
    private Button btListarPacientes;

    @FXML
    public void criarPaciente() throws IOException {
        TCPLoader = new FXMLLoader(getClass().getResource("/view/paciente/TelaCadastrarPaciente.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(TCPLoader.load()));
        stage.show();
    }

    @FXML
    public void listarPacientes() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/paciente/ListarPacientes.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void criarMedico() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/medico/TelaCadastrarMedico.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void listarMedicos() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/medico/TelaListarMedicos.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void criarEspecialidade() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/especialidade/TelaCadastrarEspecialidade.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void listarEspecialidades() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/especialidade/ListarEspecialidades.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void criarSubespecialidade() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/subespecialidade/TelaCadastrarSubespecialidade.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void listarSubespecialidades() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/subespecialidade/ListarSubespecialidades.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void marcarConsulta() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/recepcionista/TelaMarcarConsulta.fxml"));
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void sair() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btCadastrarEspecialidade.getScene().getWindow();
        stage.close();
    }
}
