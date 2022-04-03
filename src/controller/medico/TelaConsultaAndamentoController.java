package controller.medico;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Consulta;
import model.Gerenciador;
import model.Medico;
import model.Paciente;

public class TelaConsultaAndamentoController {

    @FXML
    private Button btEncerrarConsulta;

    @FXML
    private Button btProntuario;

    private Consulta consultaSelecionada;
    private Paciente pacienteConsulta;
    private Medico medicoLogado;
    private TelaMedicoController controller;

    @FXML
    public void encerrarConsulta() {
        Gerenciador.concluirConsulta(medicoLogado, consultaSelecionada);
        mostrarMsg("Consulta finalizada!", Alert.AlertType.INFORMATION);
        controller.carregarDados();
        fecharJanela();
    }

    @FXML
    public void prontuarioPaciente() throws IOException {
        FXMLLoader TPLoader = new FXMLLoader(getClass().getResource("/view/medico/TelaProntuario.fxml"));

        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(TPLoader.load()));

        TelaProntuarioController controller1 = TPLoader.getController();
        controller1.setPaciente(pacienteConsulta);
        controller1.carregarDados();
        if (pacienteConsulta.getProntuario().isEmpty()) {
            mostrarMsg("Essa é a primeira vez do paciente na clínica", Alert.AlertType.INFORMATION);
        }
        stage.show();
    }

    @FXML
    public void gerarReceitaMedica() throws IOException {
        FXMLLoader TRLoader = new FXMLLoader(getClass().getResource("/view/medico/TelaReceita.fxml"));

        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(TRLoader.load()));

        TelaReceitaController controllerLocal = TRLoader.getController();
        controllerLocal.setNomeMedico(medicoLogado.getNome());
        controllerLocal.setNomePaciente(pacienteConsulta.getNome());
        controllerLocal.carregarDados();

        stage.show();
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btEncerrarConsulta.getScene().getWindow();
        stage.close();
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }

    public void setConsultaSelecionada(Consulta consultaSelecionada) {
        this.consultaSelecionada = consultaSelecionada;
    }

    public void setPacienteConsulta(Paciente pacienteConsulta) {
        this.pacienteConsulta = pacienteConsulta;
    }

    public void setMedicoLogado(Medico medicoLogado) {
        this.medicoLogado = medicoLogado;
    }

    public void setController(TelaMedicoController controller) {
        this.controller = controller;
    }

}
