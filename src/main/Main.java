/*******************************************************************************
Autor: Carlos Henrique de Oliveira Valadão
Componente Curricular: MI Programação
Concluido em: 09/12/2021
Declaro que este código foi elaborado por Carlos Henrique de Oliveira Valadão e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Carlos Valadão
 */
public class Main extends Application {

    private static Stage mainStage = null;

    @Override
    public void start(Stage stage) throws Exception {
        String caminhoArquivo = "/view/TelaInicial.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(caminhoArquivo));
        Scene scene = new Scene(root);

        mainStage = stage;
        mainStage.setScene(scene);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
