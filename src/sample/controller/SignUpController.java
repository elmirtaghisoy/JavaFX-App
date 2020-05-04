package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseHandler;
import sample.User;

public class SignUpController {

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUp_name;

    @FXML
    private TextField signUp_surname;

    @FXML
    private TextField signUpCountry;

    @FXML
    private Button signInButton;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    void initialize() {
        signInButton.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstName = signUp_name.getText();
        String lastName = signUp_surname.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String location = signUpCountry.getText();
        String gender = (signUpCheckBoxMale.isSelected()) ? "Male" : "Female";

        User user = new User(firstName, lastName, userName, password, location, gender);
        dbHandler.signUpUser(user);
    }
}