package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.controller.TransactionController;
import com.kodilla.stock_trading_platform.controller.UserController;
import com.kodilla.stock_trading_platform.controller.WalletController;
import com.kodilla.stock_trading_platform.domain.TransactionDto;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.domain.WalletDto;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route(value = "account")
public class AccountGui extends VerticalLayout {
    @Autowired
    private UserController userController;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WalletController walletController;

    @Autowired
    private TransactionController transactionController;

    private final Button dashboard = new Button("DASHBOARD");
    private final Button tradingArea = new Button("TRADING AREA");

    private final TextField textFieldLogin = new TextField("*Put your login");
    private final EmailField emailField = new EmailField("*Put your Email");

    private final Label createdLabel = new Label("Your account has been created");
    private final Label alreadyRegisteredLabel = new Label(" and check if there is ID for your Email - press <Show my ID>");
    private final Label idLabel = new Label();
    private final Label userNotFoundLabel = new Label("Please put correct email to check your ID");
    private final TextField textFieldNewLogin = new TextField("Put new login");
    private final EmailField emailFieldNewEmail = new EmailField("Put new Email");
    private final IntegerField userIdField = new IntegerField("Put your id");
    private final Label updateLabel = new Label("Your data has been updated");
    private final Label deleteLabel = new Label("Account deleted");
    private final Label idMissingLabel = new Label("You put wrong user ID or you don't have wallet yet");
    private final Label deleteUserNotPossibleLabel = new Label("You didn't put correct user ID or your wallet isn't empty");
    private final Label walletCreatedLabel = new Label("Wallet created");
    private final Label walletNotCreatedLabel = new Label("You didn't put or put wrong user id or you already have wallet");
    private final Label walletIdNotPresentLabel = new Label("You didn't put or put wrong wallet id ");
    private final Label walletNotEmpty = new Label("You put wrong wallet ID or your ID isn't empty");
    private final Label walletIdLabel = new Label();
    private final IntegerField walletIdField = new IntegerField("Put your wallet ID");
    private final Label fullFill = new Label("You have to full fill all data");

    private final Grid<TransactionDto> grid = new Grid<>(TransactionDto.class);
    private final TextField transactionTypeFilter = new TextField();
    private final TextField shareSymbolFilter = new TextField();
    private final TextField transactionDateFilter = new TextField();

    public AccountGui() {

        dashboard.addClickListener(e -> dashboard.getUI().ifPresent(ui ->
                ui.navigate("")));

        tradingArea.addClickListener(e -> tradingArea.getUI().ifPresent(ui ->
                ui.navigate("tradingArea")));

        HorizontalLayout navigationButtons = new HorizontalLayout(dashboard, tradingArea);
        H1 title = new H1("My account");
        title.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), title);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(title);
        header.setVerticalComponentAlignment(FlexComponent.Alignment.STRETCH);

        transactionTypeFilter.setPlaceholder("Filter by transaction type");
        transactionTypeFilter.setClearButtonVisible(true);
        transactionTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        transactionTypeFilter.addValueChangeListener(e -> updateByType());

        shareSymbolFilter.setPlaceholder("Filter by share symbol");
        shareSymbolFilter.setClearButtonVisible(true);
        shareSymbolFilter.setValueChangeMode(ValueChangeMode.EAGER);
        shareSymbolFilter.addValueChangeListener(e -> updateByShareSymbol());

        transactionDateFilter.setPlaceholder("Filter by date");
        transactionDateFilter.setClearButtonVisible(true);
        transactionDateFilter.setValueChangeMode(ValueChangeMode.EAGER);
        transactionDateFilter.addValueChangeListener(e -> updateByDate());

        grid.removeColumnByKey("id");
        grid.removeColumnByKey("walletId");
        setSizeFull();

        Button createButton = new Button("Create account");
        createButton.addClickListener(e -> createUser());

        Button userIdButton = new Button("Show my id");
        userIdButton.addClickListener(e -> getUserId());

        Button updateButton = new Button("Update my data");
        updateButton.addClickListener(e -> updateUser());

        Button deleteButton = new Button("Delete my account");
        deleteButton.addClickListener(e -> {
            deleteWallet();
            deleteUser();
        });

        Button createWalletButton = new Button("Create wallet");
        createWalletButton.addClickListener(e -> createWallet());

        Button showWalletIdButton = new Button("Show my wallet ID");
        showWalletIdButton.addClickListener(e -> getWalletId());

        Button showMyWalletButton = new Button("Show Wallet");
        showMyWalletButton.addClickListener(e -> showWallet());

        Button deleteWalletButton = new Button("Delete my wallet");
        deleteWalletButton.addClickListener(e -> deleteWallet());

        HorizontalLayout registerData = new HorizontalLayout(textFieldLogin, emailField, createButton);
        HorizontalLayout idIssues = new HorizontalLayout(userIdButton, idLabel);
        HorizontalLayout newData = new HorizontalLayout(userIdField, textFieldNewLogin, emailFieldNewEmail);
        HorizontalLayout actionButtons = new HorizontalLayout(updateButton, deleteButton);
        HorizontalLayout walletIssues = new HorizontalLayout(createWalletButton, showWalletIdButton, walletIdLabel, walletIdField, showMyWalletButton, deleteWalletButton);
        HorizontalLayout filters = new HorizontalLayout(transactionTypeFilter, shareSymbolFilter, transactionDateFilter);

        add(navigationButtons, title, registerData, idIssues, newData, actionButtons, walletIssues, filters, grid);
    }

    private void createUser() {
        try {
            userController.createUser(new UserDto(textFieldLogin.getValue(), emailField.getValue()));
            add(createdLabel);
        } catch (Exception e) {
            add(fullFill);
            add(alreadyRegisteredLabel);
        }
    }

    private void getUserId() {
        try {
            UserDto userDto = userController.getUserByEmail(String.valueOf(emailField.getValue()));
            idLabel.setText(String.valueOf(userDto.getId()));
        } catch (Exception e) {
            add(userNotFoundLabel);
        }
    }

    private void updateUser() {
        try {
            userController.updateUser(new UserDto(userIdField.getValue(), textFieldNewLogin.getValue(), emailFieldNewEmail.getValue()));
            add(updateLabel);
        } catch (Exception e) {
            add(fullFill);
        }
    }

    private void deleteUser() {
        try {
            deleteWallet();
            userController.deleteUser(Long.valueOf(userIdField.getValue()));
            add(deleteLabel);
        } catch (Exception e) {
            add(deleteUserNotPossibleLabel);
        }
    }

    private void createWallet() {
        try {
            walletController.createWallet(new WalletDto(Long.valueOf(userIdField.getValue())));
            add(walletCreatedLabel);
        } catch (Exception e) {
            add(walletNotCreatedLabel);
        }
    }

    private void getWalletId() {
        try {
            WalletDto walletDto = walletController.getWalletByUser(userMapper.mapToUser(userController.getUser(Long.valueOf(userIdField.getValue()))));
            walletIdLabel.setText(String.valueOf(walletDto.getId()));
        } catch (Exception e) {
            add(idMissingLabel);
        }
    }

    private void showWallet() {
        try {
            transactionController.getTransactionsByWalletId(Long.valueOf(walletIdField.getValue()));
            refresh();
        } catch (Exception e) {
            add(walletIdNotPresentLabel);
        }
    }

    private void deleteWallet() {
        try {
            walletController.deleteWallet(Long.valueOf(walletIdField.getValue()));
        } catch (Exception e) {
            add(walletNotEmpty);
        }
    }

    private void updateByType() {
        grid.setItems(transactionController.getTransactionByType(Long.valueOf(walletIdField.getValue()), TransactionType.valueOf(transactionTypeFilter.getValue())));
    }

    private void updateByShareSymbol() {
        grid.setItems(transactionController.getTransactionByShareSymbol(Long.valueOf(walletIdField.getValue()), shareSymbolFilter.getValue()));
    }

    private void updateByDate() {
        grid.setItems(transactionController.getTransactionByTransactionDate(Long.valueOf(walletIdField.getValue()), LocalDate.parse(transactionDateFilter.getValue())));
    }

    private void refresh() {
        grid.setItems(transactionController.getTransactionsByWalletId(Long.valueOf(walletIdField.getValue())));
    }
}

