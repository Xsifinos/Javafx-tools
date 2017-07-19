package gr.anneta.test_project.controller;

import gr.softaware.lib.controlsfx.ValidationSupportNoThread;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.CompoundValidationDecoration;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;
import org.controlsfx.validation.decoration.ValidationDecoration;

/**
 *
 * @author chsifinos@gmail.com
 */
public class RequiredValidationController implements Initializable {

    @FXML
    private TextField testTextField1;
    @FXML
    private TextField testTextField2;
    @FXML
    private CheckBox testCheckBox;
    @FXML
    private ComboBox testComboBox;
    @FXML
    private ChoiceBox testChoiceBox;

    @FXML
    void secondaryStageButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Custom Validation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CustomValidationView.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        validationSupport.registerValidator(testTextField1, Validator.createEmptyValidator("Tooltip message 1", Severity.WARNING));
        validationSupport.registerValidator(testTextField2, Validator.createEmptyValidator("Tooltip message 2", Severity.ERROR));
        validationSupport.registerValidator(testCheckBox, (Control c, Boolean newValue)
                -> ValidationResult.fromErrorIf(c, "Checkbox should be checked", !newValue)
        );
        validationSupport.registerValidator(testComboBox, Validator.createEmptyValidator("ComboBox Selection required"));
        validationSupport.registerValidator(testChoiceBox, Validator.createEmptyValidator("ChoiceBox Selection required"));
        ValidationSupport.setRequired(testTextField2, false);
        ValidationSupport.setRequired(testChoiceBox, false);
        ValidationSupport.isRequired(testComboBox);
    }

    ValidationSupportNoThread validationSupport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testComboBox.getItems().addAll("Item A", "Item B", "Item C");
        testChoiceBox.getItems().addAll("Item A", "Item B", "Item C");
        validationSupport = new ValidationSupportNoThread();
        ValidationDecoration iconDecorator = new GraphicValidationDecoration();
        ValidationDecoration cssDecorator = new StyleClassValidationDecoration();
        ValidationDecoration compoundDecorator = new CompoundValidationDecoration(cssDecorator, iconDecorator);
        validationSupport.setValidationDecorator(iconDecorator);
        validationSupport.setValidationDecorator(cssDecorator);
        validationSupport.setValidationDecorator(compoundDecorator);
        validationSupport.initInitialDecoration();
    }
}
