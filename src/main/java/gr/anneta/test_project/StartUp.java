package gr.anneta.test_project;

import java.io.IOException;
import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author chsifinos@gmail.com
 */
public class StartUp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/RequiredValidationView.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/files/css/common.css");
        stage.setTitle("Required Validation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
