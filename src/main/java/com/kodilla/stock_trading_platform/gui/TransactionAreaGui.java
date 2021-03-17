package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.controller.TransactionController;
import com.kodilla.stock_trading_platform.domain.TransactionDto;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.webClient.fcs.FcsController;
import com.kodilla.stock_trading_platform.webClient.finnhub.FinnhubController;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tradingArea")
@PageTitle("Transaction area")
public class TransactionAreaGui extends VerticalLayout {

    @Autowired
    private FinnhubController finnhubController;

    @Autowired
    private FcsController fcsController;

    @Autowired
    private TransactionController transactionController;

    private Button dashboard;
    private Button account;
    private final TextField textFieldCompanyName;
    private final Button checkSymbolButton;
    private final Label labelShareSymbol;
    private final TextField textFieldShareSymbol;
    private final Label labelPrice;
    private ComboBox<TransactionType> transactionType;
    private TextField textFieldChosenShareSymbol;
    private BigDecimalField bigDecimalFieldSharePrice;
    private IntegerField integerFieldQuantity;
    private DatePicker datePickerTransactionDate;
    private IntegerField walletId;
    private Label instructionLabel;

    private Button confirmButton;
    private Label confirmed;
    private Label fullFill;

    private Button deleteTransactions;

    public TransactionAreaGui() {

        dashboard = new Button("DASHBOARD");
        account = new Button("MY ACCOUNT");

        dashboard.addClickListener(e -> dashboard.getUI().ifPresent(ui ->
                ui.navigate("")));

        account.addClickListener(e -> account.getUI().ifPresent(ui ->
                ui.navigate("account")));

        HorizontalLayout naviButtons = new HorizontalLayout(dashboard, account);
        H1 title = new H1("Trading area");
        title.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), title);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(title);
        header.setVerticalComponentAlignment(FlexComponent.Alignment.STRETCH);

        textFieldCompanyName = new TextField("Put full company name");
        checkSymbolButton = new Button("Show share symbol", new Icon(VaadinIcon.TRENDING_UP));
        labelShareSymbol = new Label();
        textFieldShareSymbol = new TextField("Put share symbol *capital letters");
        Button checkPriceButton = new Button("Check price", new Icon(VaadinIcon.DOLLAR));
        labelPrice = new Label();

        instructionLabel = new Label("Before you confirm transaction, please check current price - put share symbol in CAPITAL LETTERS and press <Check price> !!!");

        deleteTransactions = new Button("SELL ALL");
        transactionType = new ComboBox("What will you do?");
        transactionType.setItems(TransactionType.values());

        textFieldChosenShareSymbol = new TextField("Put share symbol");

        bigDecimalFieldSharePrice = new BigDecimalField("Current price");

        integerFieldQuantity = new IntegerField("Put shares quantity");

        datePickerTransactionDate = new DatePicker("Date of transaction");

        walletId = new IntegerField("Put your wallet Id");

        confirmButton = new Button("CONFIRM");

        confirmed = new Label("Transaction confirmed");
        fullFill = new Label("You have to full fill all data and check if Wallet ID is correct");
        confirmButton.addClickListener(e -> save());

        checkSymbolButton.addClickListener(e -> {
            labelShareSymbol.setText("Share symbol of " + textFieldCompanyName.getValue() + " is " + fcsController.getShareSymbol(textFieldCompanyName.getValue()));
        });

        checkPriceButton.addClickListener(e -> {
            labelPrice.setText(finnhubController.getSharePrice(textFieldShareSymbol.getValue()).toString());
        });

        deleteTransactions.addClickListener(e -> deleteAll());

        VerticalLayout firstRow = new VerticalLayout(textFieldCompanyName, checkSymbolButton, labelShareSymbol);
        VerticalLayout secondRow = new VerticalLayout(textFieldShareSymbol, checkPriceButton, labelPrice);

        HorizontalLayout f = new HorizontalLayout(firstRow, secondRow);

        HorizontalLayout thirdRow = new HorizontalLayout(transactionType, textFieldChosenShareSymbol, bigDecimalFieldSharePrice,
                integerFieldQuantity, datePickerTransactionDate, walletId, confirmButton, deleteTransactions);

        title.setWidthFull();
        thirdRow.setSizeFull();
        thirdRow.setAlignItems(Alignment.CENTER);

        add(naviButtons, title, f, instructionLabel, thirdRow);

    }

    private void deleteAll() {
        transactionController.deleteTransactions(Long.valueOf(walletId.getValue()));
    }

    private void save() {
        try {
            transactionController.createTransaction(new TransactionDto(Long.valueOf(walletId.getValue()), transactionType.getValue(), textFieldShareSymbol.getValue(), bigDecimalFieldSharePrice.getValue(),
                    integerFieldQuantity.getValue(), datePickerTransactionDate.getValue()
            ));
            add(confirmed);
        } catch (Exception e) {
            add(fullFill);
        }
    }
}

