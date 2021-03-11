package com.kodilla.stock_trading_platform.gui;

import com.kodilla.stock_trading_platform.webClient.stockExchange.StockExchangeController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "share")
public class ShareGui extends VerticalLayout {

    @Autowired
    private StockExchangeController stockExchangeController;

    private final TextField textFieldCompanyName;
    private final Button checkSymbolButton;
    private final Label labelShareSymbol;
    private final TextField textFieldShareSymbol;
    private final Button checkPriceButton;
    private final Label labelPrice;

    public ShareGui() {
        textFieldCompanyName = new TextField("Put full company name");
        checkSymbolButton = new Button("Show share symbol", new Icon(VaadinIcon.TRENDING_UP));
        labelShareSymbol = new Label();
        textFieldShareSymbol = new TextField("Put share symbol in capital letters");
        checkPriceButton = new Button("Check price", new Icon(VaadinIcon.DOLLAR));
        labelPrice = new Label();

        checkSymbolButton.addClickListener(e -> {
            labelShareSymbol.setText("Share symbol of " + textFieldCompanyName.getValue() + " is " + stockExchangeController.getShareSymbol(textFieldCompanyName.getValue()));
        });

        checkPriceButton.addClickListener(e -> {
            labelPrice.setText(stockExchangeController.getSharePrice(textFieldShareSymbol.getValue()).toString());
        });

        add(textFieldCompanyName, checkSymbolButton,labelShareSymbol, textFieldShareSymbol,checkPriceButton,labelPrice);

        setSizeFull();
    }
}
