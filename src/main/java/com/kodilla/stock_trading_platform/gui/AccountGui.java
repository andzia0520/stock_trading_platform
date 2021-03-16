package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.controller.TransactionController;
import com.kodilla.stock_trading_platform.controller.UserController;
import com.kodilla.stock_trading_platform.controller.WalletController;
import com.kodilla.stock_trading_platform.domain.TransactionDto;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.domain.WalletDto;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.kodilla.stock_trading_platform.service.UserAlreadyExistException;
import com.kodilla.stock_trading_platform.service.WalletExsistsException;
import com.kodilla.stock_trading_platform.service.WalletNotEmptyException;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "account")
@PageTitle("My account")
public class AccountGui extends VerticalLayout {
    @Autowired
    private UserController userController;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WalletController walletController;

    @Autowired
    private TransactionController transactionController;

    private Button dashboard;
    private Button tradingArea;
    private TextField textFieldLogin;
    private EmailField emailField;
    private Button createButton;
    private Label noLoginLabel;
    private Label noEmailLabel;
    private Label alreadyRegisteredLabel;
    private Label createdLabel;
    private Button userIdButton;
    private Label idLabel;

    private Button updateButton;
    private Label updateLabel;
    private Button deleteButton;
    private Label deleteLabel;
    private TextField textFieldNewLogin;
    private EmailField emailFieldNewEmail;
    private IntegerField userIdField;

    private Button createWalletButton;
    private Button deleteWalletButton;
    private Button showWalletIdButton;
    private Button showMyWalletButton;
    private Label walletIdLabel;
    private IntegerField walletIdField;
    private Label walletNotEmpty;
    private Label walletCreatedLabel;

  /*  private Transaction transaction;
    private Binder<Transaction> binder = new Binder<>(Transaction.class);*/

    private Grid grid = new Grid<>(TransactionDto.class);
    private TextField transactionTypeFilter = new TextField();
    private TextField shareSymbolFilter = new TextField();

    public AccountGui() {

        dashboard = new Button("DASHBOARD");
        tradingArea = new Button("TRADING AREA");

        dashboard.addClickListener(e -> dashboard.getUI().ifPresent(ui ->
                ui.navigate("")));

        tradingArea.addClickListener(e -> tradingArea.getUI().ifPresent(ui ->
                ui.navigate("tradingArea")));
        HorizontalLayout naviButtons = new HorizontalLayout(dashboard, tradingArea);

        H1 title = new H1("My account");
        title.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), title);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(title);
        header.setVerticalComponentAlignment(FlexComponent.Alignment.STRETCH);

        textFieldLogin = new TextField("*Put your login");
        emailField = new EmailField("*Put your Email");
        createButton = new Button("Create account");
        createdLabel = new Label("Your account has been created");
        userIdButton = new Button("Show my id");
        idLabel = new Label();
        alreadyRegisteredLabel = new Label("You have already account on our platform, " +
                "please put your ID to continue");

        textFieldNewLogin = new TextField("Put new login");
        emailFieldNewEmail = new EmailField("Put new Email");
        userIdField = new IntegerField("Put your id");
        updateButton = new Button("Update my data");
        updateLabel = new Label("Your data has been updated");
        deleteButton = new Button("Delete my account");
        deleteLabel = new Label("Account deleted");

        createWalletButton = new Button("Create wallet");
        walletCreatedLabel = new Label("Wallet created");
        showWalletIdButton = new Button("Show my wallet ID");
        deleteWalletButton = new Button("delete my wallet");
        walletIdLabel = new Label();
        walletIdField = new IntegerField("Put your wallet ID");
        walletNotEmpty = new Label("Please sell all shares before you delete your Wallet");
        showMyWalletButton = new Button("Show Wallet");

        transactionTypeFilter.setPlaceholder("Filter by transaction type");
        transactionTypeFilter.setClearButtonVisible(true);
        transactionTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        transactionTypeFilter.addValueChangeListener(e -> updateByType());

        shareSymbolFilter.setPlaceholder("Filter by share symbol");
        shareSymbolFilter.setClearButtonVisible(true);
        shareSymbolFilter.setValueChangeMode(ValueChangeMode.EAGER);
        shareSymbolFilter.addValueChangeListener(e -> updateByShareSymbol());

        grid.removeColumnByKey("id");
        grid.removeColumnByKey("walletId");
        //grid.setColumns("id", "transactionType", "shareSymbol", "price", "quantity", "transactionDate", "wallet");
        setSizeFull();


        createButton.addClickListener(e -> {
            try {
                createUser();
            } catch (Exception reg) {
                add(alreadyRegisteredLabel);
            }
        });

        userIdButton.addClickListener(e -> getUserId());

        updateButton.addClickListener(e -> updateUser());

        deleteButton.addClickListener(e -> deleteUser());

        createWalletButton.addClickListener(e -> {
            try {
                createWallet();
            } catch (WalletExsistsException walletExsistsException) {
                walletExsistsException.printStackTrace();
            }
        });


        deleteWalletButton.addClickListener(e -> {
            try {
                deleteWallet();
            } catch (WalletNotEmptyException walletNotEmptyException) {
                add(walletNotEmpty);
            }
        });
        // try {

        // } catch (WalletNotEmptyException walletNotEmptyException) {
        //   walletNotEmptyException.printStackTrace();

        //});


        showWalletIdButton.addClickListener(e -> getWalletId());

        showMyWalletButton.addClickListener(e -> showWallet());

        HorizontalLayout register = new HorizontalLayout(textFieldLogin, emailField, createButton);
        HorizontalLayout id = new HorizontalLayout(userIdButton, idLabel);
        HorizontalLayout newData = new HorizontalLayout(userIdField, textFieldNewLogin, emailFieldNewEmail);
        HorizontalLayout actionButtons = new HorizontalLayout(updateButton, deleteButton);
        HorizontalLayout walletIssues = new HorizontalLayout(createWalletButton, showWalletIdButton, walletIdLabel, walletIdField, showMyWalletButton);
        HorizontalLayout filters = new HorizontalLayout(transactionTypeFilter, shareSymbolFilter);

        add(naviButtons, title, register, id, newData, actionButtons, walletIssues, filters, grid);
    }



    private void deleteWallet() throws WalletNotEmptyException {//throws WalletNotEmptyException {
        //try {
        walletController.deleteWallet(Long.valueOf(walletIdField.getValue()));
        //} catch (Exception e ) {
        //   throw new WalletNotEmptyException("Please sell all shares before you delete your Wallet");
        // }
    }

    private void getWalletId() {
        WalletDto walletDto = walletController.getWalletByUser(userMapper.mapToUser(userController.getUser(Long.valueOf(userIdField.getValue()))));
        walletIdLabel.setText(String.valueOf(walletDto.getId()));
    }

    private void getUserId() {
        UserDto userDto = userController.getUserByEmail(String.valueOf(emailField.getValue()));
        idLabel.setText(String.valueOf(userDto.getId()));
    }

    private void createUser() throws UserAlreadyExistException {//throws UserAlreadyExistException {
        //try {
        userController.createUser(new UserDto(textFieldLogin.getValue(), emailField.getValue()));
        add(createdLabel);
        // } catch (Exception e) {
        //throw new UserAlreadyExistException(alreadyRegisteredLabel.toString());
        // }

    }

    private void updateUser() {
        userController.updateUser(new UserDto(userIdField.getValue(), textFieldNewLogin.getValue(), emailFieldNewEmail.getValue()));
        add(updateLabel);
    }

    private void deleteUser() {
        userController.deleteUser(Long.valueOf(userIdField.getValue()));
        add(deleteLabel);
    }

    private void createWallet() throws WalletExsistsException {//throws WalletExsistsException {
        //try {
        walletController.createWallet(new WalletDto(Long.valueOf(userIdField.getValue())));
        add(walletCreatedLabel);
        // } catch (WalletExsistsException w) {
        //    throw new WalletExsistsException("Wallet for user with given id already exists, " +
        //                  "please check your wallet id");
        // }
    }


    private void showWallet() {
        transactionController.getTransactionsByWalletId(Long.valueOf(walletIdField.getValue()));
        refresh();
    }


    private void updateByType() {
        grid.setItems(transactionController.getTransactionByType(Long.valueOf(walletIdField.getValue()), TransactionType.valueOf(transactionTypeFilter.getValue())));
    }

    private void updateByShareSymbol() {
        grid.setItems(transactionController.getTransactionByShareSymbol(Long.valueOf(walletIdField.getValue()), shareSymbolFilter.getValue()));

    }

    private void refresh() {
        grid.setItems(transactionController.getTransactionsByWalletId(Long.valueOf(walletIdField.getValue())));
    }
}
