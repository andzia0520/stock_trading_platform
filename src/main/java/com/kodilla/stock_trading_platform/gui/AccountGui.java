/*
package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.controller.UserController;
import com.kodilla.stock_trading_platform.controller.WalletController;
import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.domain.WalletDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "account")
public class AccountGui extends VerticalLayout {
    @Autowired
    private UserController userController;

    @Autowired
    private WalletController walletController;

    private TextField textFieldLogin;
    private EmailField emailField;
    private Button updateButton;
    private Label updateLabel;
    private Button deleteButton;
    private Label deleteLabel;
    private Button createWalletButton;
    private Button showMyWalletButton;

    public AccountGui() {
        textFieldLogin = new TextField("Put new login");
        emailField = new EmailField("Put new Email");
        updateButton = new Button("Update my data");
        updateLabel = new Label("Your data has been updated");
        deleteButton = new Button("Delete my account");
        deleteLabel = new Label("Account deleted");
        createWalletButton = new Button("Create wallet");
        showMyWalletButton = new Button("Show Wallet");

        updateButton.addClickListener(e -> updateUser());

        deleteButton.addClickListener(e -> deleteUser());

        createWalletButton.addClickListener(e -> createWallet());

        */
/*showMyWalletButton.addClickListener(e -> {
            try {
                showWallet();
            } catch (WalletNotFoundException walletNotFoundException) {
                walletNotFoundException.printStackTrace();
            }
        });
*//*


        add(textFieldLogin, emailField, updateButton, deleteButton, createWalletButton, showMyWalletButton);
    }

    private void updateUser() {
        userController.updateUser(new UserDto(textFieldLogin.getValue(), emailField.getValue()));
        add(updateLabel);
    }

    private void deleteUser() {
        userController.deleteUser(textFieldLogin.getValue(), emailField.getValue());
        add(deleteLabel);
    }

    private void createWallet() {
        walletController.createWallet(new WalletDto(new UserDto(textFieldLogin.getValue(), emailField.getValue())));
    }

    */
/*private void showWallet() throws WalletNotFoundException {
        walletController.getWallet(walletDto.getId());
    }*//*

}

*/
