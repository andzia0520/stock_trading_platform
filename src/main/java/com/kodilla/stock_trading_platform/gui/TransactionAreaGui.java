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
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tradingArea")
public class TransactionAreaGui extends VerticalLayout {

    @Autowired
    private FinnhubController finnhubController;

    @Autowired
    private FcsController fcsController;

    @Autowired
    private TransactionController transactionController;

    private final Button dashboard = new Button("DASHBOARD");
    private final Button account = new Button("MY ACCOUNT");
    private final TextField textFieldCompanyName = new TextField("Put company name e.g. Alphabet");
    private final Label labelShareSymbol = new Label();
    private final TextField textFieldShareSymbol = new TextField("Put share symbol *capital letters");
    private final Label labelPrice = new Label();
    private final ComboBox<TransactionType> transactionType = new ComboBox<>("What will you do?");
    private final BigDecimalField bigDecimalFieldSharePrice = new BigDecimalField("Current price");
    private final IntegerField integerFieldQuantity = new IntegerField("Put shares quantity");
    private final DatePicker datePickerTransactionDate = new DatePicker("Date of transaction");
    private final IntegerField walletId = new IntegerField("Put your wallet Id");

    private final Label confirmed = new Label("Action done");
    private final Label fullFill = new Label("You have to full fill all data and check if wallet ID is correct");
    private final Label emptyWalletLabel = new Label("Please check if you've put correct wallet ID, " +
            "if YES your wallet was empty ");

    public TransactionAreaGui() {

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

        transactionType.setItems(TransactionType.values());

        Button checkSymbolButton = new Button("Show share symbol", new Icon(VaadinIcon.TRENDING_UP));
        checkSymbolButton.addClickListener(e -> checkShareSymbol());

        Button checkPriceButton = new Button("Check price", new Icon(VaadinIcon.DOLLAR));
        checkPriceButton.addClickListener(e -> checkSharePrice());

        Button confirmButton = new Button("CONFIRM");
        confirmButton.addClickListener(e -> save());

        Button deleteTransactionsButton = new Button("SELL ALL");
        deleteTransactionsButton.addClickListener(e -> deleteAll());

        VerticalLayout firstRow = new VerticalLayout(textFieldCompanyName, checkSymbolButton, labelShareSymbol);
        VerticalLayout secondRow = new VerticalLayout(textFieldShareSymbol, checkPriceButton, labelPrice);
        HorizontalLayout h1 = new HorizontalLayout(firstRow, secondRow);

        TextField textFieldChosenShareSymbol = new TextField("Put share symbol");
        HorizontalLayout transactionRow = new HorizontalLayout(transactionType, textFieldChosenShareSymbol, bigDecimalFieldSharePrice,
                integerFieldQuantity, datePickerTransactionDate, walletId, confirmButton, deleteTransactionsButton);

        title.setWidthFull();
        transactionRow.setSizeFull();
        transactionRow.setAlignItems(Alignment.CENTER);

        Label instructionLabel = new Label("Before you confirm transaction, please check current price - put share symbol in CAPITAL LETTERS and press <Check price> !!!");
        add(naviButtons, title, h1, instructionLabel, transactionRow);

    }

    private void checkShareSymbol() {
        labelShareSymbol.setText("Share symbol of " + textFieldCompanyName.getValue() + " is " + fcsController.getShareSymbol(textFieldCompanyName.getValue()));
    }

    private void checkSharePrice() {
        labelPrice.setText(finnhubController.getSharePrice(textFieldShareSymbol.getValue()).toString());
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

    private void deleteAll() {
        try {
            transactionController.deleteTransactions(Long.valueOf(walletId.getValue()));
            add(confirmed);
        } catch (Exception e) {
            add(emptyWalletLabel);
        }
    }
}

