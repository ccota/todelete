package org.academiadecodigo.bootcamp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.model.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {
    private boolean isOnLogin = true;

    @FXML
    private ImageView logoImage;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField usernameTxtField;

    @FXML
    private TextField emailTxtField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button Register;

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink registerLink;


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @FXML
    void onLogin(ActionEvent event) {

        if (usernameTxtField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorLabel.setText("Please set your user name and password ");
            return;
        }
        if (!isOnLogin && emailTxtField.getText().isEmpty()) {
            errorLabel.setText("Please set your Email ");
            return;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailTxtField.getText());
        if (!isOnLogin && !matcher.matches()) {
            emailTxtField.clear();
            errorLabel.setText("Invalid email format");
            return;
        }

        if ((isOnLogin)) {
            login();
        } else {
            register();
        }


    }

    private void login(){

        errorLabel.setText((userService.authenticate(usernameTxtField.getText(), passwordField.getText()) ) ?
                "Login successful"  : "username or password does not exist");





    }
    private void register(){

        if (userService.findByName(usernameTxtField.getText()) != null){
            usernameTxtField.clear();
            errorLabel.setText("Username already taken");
            return;
        }

        userService.addUser(new User(usernameTxtField.getText(), passwordField.getText(), emailTxtField.getText()));
        errorLabel.setText("Register successful");

        setOnLogin();

    }

    @FXML
    void onRegister(ActionEvent event) {
        if (isOnLogin){
            setOnRegister();
            return;
        }
        setOnLogin();


    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    private void setOnRegister(){
        isOnLogin = false;
        loginButton.setText("Register");
        registerLink.setText("Cancel");

        usernameTxtField.clear();
        passwordField.clear();
        emailTxtField.clear();
        errorLabel.setText("");

        emailLabel.setVisible(true);
        emailTxtField.setVisible(true);

    }

    private void setOnLogin() {
        isOnLogin = true;
        loginButton.setText("Login");
        registerLink.setText("Register");

        usernameTxtField.clear();
        passwordField.clear();
        emailTxtField.clear();

        emailLabel.setVisible(false);
        emailTxtField.setVisible(false);
    }

}

