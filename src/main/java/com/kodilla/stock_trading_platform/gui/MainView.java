package com.kodilla.stock_trading_platform.gui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout {

    public MainView() {
        createHeader();
        createDrawer();

    }

    private void createHeader() {
        H1 name = new H1("US stock trading platform");
        name.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), name);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(name);
        header.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink transaction = new RouterLink("Trading area", TransactionAreaGui.class);
        transaction.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink myAccount = new RouterLink("My account", AccountGui.class);
        myAccount.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                myAccount, transaction
        ));
    }
}

