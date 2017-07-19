package gr.anneta.test_project.controller;

import gr.softaware.lib.controlsfx.ValidationSupportNoThread;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.decoration.CompoundValidationDecoration;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;
import org.controlsfx.validation.decoration.ValidationDecoration;

/**
 *
 * @author chsifinos@gmail.com
 */
public class CustomValidationController implements Initializable {

    @FXML
    private TextField emailTxtf;
    @FXML
    private TextField numberTxtf;
    @FXML
    private TextField min20Txtf;
    @FXML
    private TextField max100Txtf;
    @FXML
    private TextField dateTxtf;
    @FXML
    private TextField dateTimeTxtf;
    @FXML
    private TextField ageMin20Txtf;
    @FXML
    private TextField ageMax30Txtf;
    @FXML
    private TextField decimalTxtf;
    @FXML
    private TextField max20LengthTxtf;
    @FXML
    private TextField min5LengthTxtf;
    @FXML
    private TextField rangeLengthTxtf;
    @FXML
    private TextField pathFile;
    @FXML
    private TextField pathDirectory;
    @FXML
    private TextField pathFilePdf;

    @FXML
    void backScreenButton(ActionEvent event) {
        Stage stage = getStage();
        stage.close();
    }

    @FXML
    private Stage getStage() {
        Stage stage = (Stage) emailTxtf.getScene().getWindow();
        return stage;
    }

    @FXML
    void handleButtonAction(ActionEvent event) {

        // Validator for email insertion.
        validationSupport.registerValidator(emailTxtf, false, (c, value) -> {
//            // The condition when is valid.
            boolean isValid = value instanceof String
                    ? value.toString().matches("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$")
                    || value.toString().isEmpty() : value == null;

            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for only numbers insertion.
        validationSupport.registerValidator(numberTxtf, false, (c, value) -> {
            // The condition when is valid.
            boolean isValid = value instanceof String
                    ? value.toString().matches("^\\d+$")
                    || value.toString().isEmpty() : value == null;

            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for greater than 20 number insertion.
        validationSupport.registerValidator(min20Txtf, false, (c, value) -> {
            boolean isValid;

            try {
                // Convert String to int.
                int b = Integer.parseInt(value.toString());
                // The condition when is valid.
                isValid = b >= 20;
            } catch (NullPointerException | NumberFormatException ex) {
                // Allow null user input.
                isValid = value == null || value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);

        });

        // Validator for less than 100 number insertion.
        validationSupport.registerValidator(max100Txtf, false, (c, value) -> {
            boolean isValid;

            try {
                // Convert String to int. 
                int b = Integer.parseInt(value.toString());
                // The condition when is valid.
                isValid = b <= 100;
            } catch (NumberFormatException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for formatted date insertion.
        validationSupport.registerValidator(dateTxtf, false, (c, value) -> {
            boolean isValid;

            try {
                // Formatting given date.
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d[d?].M[M?].yyyy");
                LocalDate date = LocalDate.parse(value.toString(), formatter);
                isValid = date != null;
            } catch (DateTimeParseException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for formatted datetime insertion.
        validationSupport.registerValidator(dateTimeTxtf, false, (c, value) -> {
            boolean isValid;

            try {
                // Formatting given date time.
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d[d?].M[M?].yyyy HH:mm");
                LocalDate date = LocalDate.parse(value.toString(), formatter);
                isValid = date != null;
            } catch (DateTimeParseException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for formatted birthdate -older than 20 y.o.- insertion.
        validationSupport.registerValidator(ageMin20Txtf, false, (c, value) -> {
            boolean isValid = false;

            try {
                // Get current date.
                LocalDate nowDate = LocalDate.now();

                // Formatting given date.
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d[d?].M[M?].yyyy");
                LocalDate birthDate = LocalDate.parse(value.toString(), formatter);

                // The condition when is valid.
                // Checking if the difference between the current date 
                // and the given date is greater than 20 years.
                LocalDate minDate = birthDate.plusYears(20);
                isValid = nowDate.isAfter(minDate);

//                if (nowDate.getYear() - birthDate.getYear() > 20) {
//                    isValid = true;
//                } else if (nowDate.getYear() - birthDate.getYear() == 20) {
//                    if (nowDate.getMonthValue() - birthDate.getMonthValue() > 0) {
//                        isValid = true;
//                    } else if (nowDate.getMonthValue() - birthDate.getMonthValue() == 0) {
//                        if (nowDate.getDayOfYear() - birthDate.getDayOfYear() >= 0) {
//                            isValid = true;
//                        }
//                    }
//                }
            } catch (DateTimeParseException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for formatted birthdate -younger than 30 y.o.- insertion.
        validationSupport.registerValidator(ageMax30Txtf, false, (c, value) -> {
            boolean isValid = false;

            try {
                // Get current date.
//                LocalDate now = LocalDate.now();
//                String formattedString = now.toString();
                LocalDate nowDate = LocalDate.now();//LocalDate.parse(formattedString);

                // Formatting given date.
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d[d?].M[M?].yyyy");
                LocalDate birthDate = LocalDate.parse(value.toString(), formatter);

                // The condition when is valid.
                // Checking if the difference between the current date 
                // and the given date is less than 30 years.
                LocalDate maxDate = birthDate.plusYears(30);
                isValid = nowDate.isBefore(maxDate);

//                if (dateNow.getYear() - date.getYear() < 30) {
//                    isValid = true;
//                } else if (dateNow.getYear() - date.getYear() == 30) {
//                    if (dateNow.getMonthValue() - date.getMonthValue() < 0) {
//                        isValid = true;
//                    } else if (dateNow.getMonthValue() - date.getMonthValue() == 0) {
//                        if (dateNow.getDayOfYear() - date.getDayOfYear() <= 0) {
//                            isValid = true;
//                        }
//                    }
//                }
            } catch (DateTimeParseException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for decimal number insertion.
        validationSupport.registerValidator(decimalTxtf, false, (c, value) -> {
            // The condition when is valid.
            boolean isValid = value instanceof String
                    ? value.toString().matches("^\\d+\\,\\d+$") // Precision of two example:"^\\d+\\,\\d{1,2}?$"
                    || value.toString().isEmpty() : value == null;

            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for characters less than 20 insertion.
        validationSupport.registerValidator(max20LengthTxtf, false, (c, value) -> {
            // The condition when is valid.
            boolean isValid = value instanceof String
                    ? value.toString().length() <= 20
                    || value.toString().isEmpty() : value == null;

            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for characters greater than 5 insertion.
        validationSupport.registerValidator(min5LengthTxtf, false, (c, value) -> {
            // The condition when is valid.
            boolean isValid = value instanceof String
                    ? value.toString().length() >= 5
                    || value.toString().isEmpty() : value == null;

            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for characters with a length of 20 to 50 insertion.
        validationSupport.registerValidator(rangeLengthTxtf, false, (c, value) -> {
            // The condition when is valid.
            boolean isValid = value instanceof String
                    ? (value.toString().length() >= 20 && value.toString().length() <= 50)
                    || value.toString().isEmpty() : value == null;

            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for existing file path insertion.
        validationSupport.registerValidator(pathFile, false, (c, value) -> {
            boolean isValid = false;

            try {
                // Convert user input to File object.
                File f = new File(value.toString());
                // The condition when is valid.
                // Checking if user input is file.
                if (f.isFile()) {
                    isValid = true;
                }
            } catch (SecurityException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for existing directory path insertion.
        validationSupport.registerValidator(pathDirectory, false, (c, value) -> {
            boolean isValid = false;
            try {
                // Convert user input to File object.
                File f = new File(value.toString());
                // The condition when is valid.
                // Checking if user input is directory.
                if (f.isDirectory()) {
                    isValid = true;
                }
            } catch (SecurityException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });

        // Validator for existing pdf file path insertion.
        validationSupport.registerValidator(pathFilePdf, false, (c, value) -> {
            boolean isValid = false;
            try {
                // Convert user input to File object.
                File f = new File(value.toString());
                // The condition when is valid.
                // Checking if user input is pdf file.
                if (f.isFile() && value.toString().toLowerCase().endsWith(".pdf")) {
                    isValid = true;
                }
            } catch (SecurityException ex) {
                // Allow null user input.
                isValid = value.toString().isEmpty();
            }
            return ValidationResult.fromMessageIf(c, "kati", Severity.ERROR, !isValid);
        });
    }

    ValidationSupportNoThread validationSupport;

    @Override
    public void initialize(URL location, ResourceBundle resources
    ) {
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
