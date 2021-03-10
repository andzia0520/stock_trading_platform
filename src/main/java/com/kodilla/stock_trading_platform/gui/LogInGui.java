/*
package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.controller.UserController;
import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.service.UserDbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "login")
public class LogInGui extends VerticalLayout {

    @Autowired
    private UserController userController;

    @Autowired
    private UserDbService userDbService;

    private TextField textFieldLogin;
    private EmailField emailField;
    private Button createButton;
    private Label noLoginLabel;
    private Label noEmailLabel;
    private Label alreadyRegisteredLabel;
    private Label createdLabel;
    private Button idButton;
    private Label idLabel;
    private Button goToAccountButton;
    private Button updateButton;
    private Button deleteButton;

    public LogInGui() {
        textFieldLogin = new TextField("*Put your login");
        emailField = new EmailField("*Put your Email");
        createButton = new Button("Create account");
        //noLoginLabel = new Label("You didn't put your login");
        //noEmailLabel = new Label("You didn't put your email");
        createdLabel = new Label("Your account has been created");
        idButton = new Button("Show my id");
        idLabel = new Label();
        alreadyRegisteredLabel = new Label("You have already account on our platform, please LogIn");
        goToAccountButton = new Button("MY ACCOUNT");
        updateButton = new Button("Update my data");
        deleteButton = new Button("Delete my account");


        createButton.addClickListener(e -> createUser());

        //idButton.addClickListener(e -> getUserId());

        add(textFieldLogin, emailField, createButton, idButton, updateButton, deleteButton);

    }

   */
/* private void getUserId() {
        User user = userDbService.getUserByLoginAndEmail(emailField.getValue());
        if (user != null) {
            idLabel.setText(String.valueOf(user.getId()));
        }
    }*//*


    private void createUser() {
        userController.createUser(new UserDto(textFieldLogin.getValue(), emailField.getValue()));
        add(createdLabel);
        add(idButton);
    }


*/
/*    String login = textFieldLogin.getValue();
    String email = emailField.getValue();

    private boolean isNotNull() {
        if (login.isEmpty()) {
            add(noLoginLabel);
            return false;
        } else if (email.isEmpty()) {
            add(noEmailLabel);
            return false;
        } else {
            return true;
        }
    }

    private boolean isNotRegistered() {
        for (User user : userDbService.getAllUsers()) {
            if (!user.getLogin().equals(login) || !user.getEmail().equals(email)) {
                add(alreadyRegisteredLabel);
            }
        }
        return true;*//*



}
*/
