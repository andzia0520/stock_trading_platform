package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.webClient.stockExchange.StockExchangeClient;
import com.kodilla.stock_trading_platform.webClient.stockExchange.StockExchangeController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "share")
public class ShareGui extends VerticalLayout {

    @Autowired
    private StockExchangeClient stockExchangeClient;

    @Autowired
    private StockExchangeController stockExchangeController;

    private TextField textFieldCompanyName;
    private Button checkSymbolButton;
    private Label labelShareSymbol;
    private TextField textFieldShareSymol;
    private Button checkPriceButton;
    private Label labelPrice;

    public ShareGui() {
        textFieldCompanyName = new TextField("Put full company name");
        checkSymbolButton = new Button("Show share symbol", new Icon(VaadinIcon.TRENDING_UP));
        labelShareSymbol = new Label();
        textFieldShareSymol = new TextField("Put share symbol to check price");
        checkPriceButton = new Button("Check price", new Icon(VaadinIcon.DOLLAR));
        labelPrice = new Label();

        checkSymbolButton.addClickListener(e -> {
            labelShareSymbol.setText("Share symbol of " + textFieldCompanyName.getValue() + " is " + stockExchangeController.getShareSymbol(textFieldCompanyName.getValue()));
        });

        checkPriceButton.addClickListener(e -> {
            labelPrice.setText(stockExchangeController.getSharePrice(textFieldShareSymol.getValue()).toString());
        });

        add(textFieldCompanyName, checkSymbolButton,labelShareSymbol,textFieldShareSymol,checkPriceButton,labelPrice);

        setSizeFull();
    }
}
