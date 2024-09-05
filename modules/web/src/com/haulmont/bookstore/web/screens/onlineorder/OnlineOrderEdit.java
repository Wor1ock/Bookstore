package com.haulmont.bookstore.web.screens.onlineorder;

import com.haulmont.bookstore.entity.Book;
import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.bookstore.entity.OrderLine;
import com.haulmont.bookstore.entity.Status;
import com.haulmont.bookstore.service.BookService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@UiController("bookstore_OnlineOrder.edit")
@UiDescriptor("online-order-edit.xml")
@EditedEntityContainer("onlineOrderDc")
@LoadDataBeforeShow
public class OnlineOrderEdit extends StandardEditor<OnlineOrder> {
    private static int maxCountOrderLines = 4;
    @Inject
    private Button commitAndCloseBtn;
    @Inject
    private CollectionPropertyContainer<OrderLine> orderLinesDc;
    @Inject
    private BookService bookService;
    @Inject
    private DataManager dataManager;
    @Inject
    private Table<OrderLine> orderLinesTable;
    @Inject
    private TextField<BigDecimal> totalCostField;
    @Inject
    private Button add;
    @Inject
    private LookupField<Status> statusField;
    @Inject
    private TextField<String> maxCountOrderLinesField;
    @Inject
    private Notifications notifications;
    @Inject
    private TextField<String> newQuantityField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        getEditedEntity().setStatus(Status.NEW);
        add.setEnabled(isAvailableBooks());
        statusField.setEditable(false);
        totalCostField.setEditable(false);
    }

    @Subscribe("maxCountOrderLinesBtn")
    public void onMaxCountOrderLinesBtnClick(Button.ClickEvent event) {
        try {
            int count = Integer.parseInt(Objects.requireNonNull(maxCountOrderLinesField.getValue()));
            if (count > 0) {
                maxCountOrderLines = count;
                maxCountOrderLinesField.setValue("");
            } else {
                notifications.create()
                        .withCaption("Количество должно быть положительным")
                        .withType(Notifications.NotificationType.WARNING)
                        .show();
            }
        } catch (NumberFormatException e) {
            notifications.create()
                    .withCaption("Неверный формат числа")
                    .withType(Notifications.NotificationType.WARNING)
                    .show();
        }
    }

    @Subscribe("add")
    public void onAddClick(Button.ClickEvent event) {
        if (!isAvailableBooks()) {
            notifications.create()
                    .withCaption("Нет доступных книг")
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
            add.setEnabled(false);
            return;
        }
        if (orderLinesDc.getMutableItems().size() < maxCountOrderLines) {
            orderLinesDc.getMutableItems().add(getRandUniqOrderLine());
        } else notifications.create()
                .withCaption("Максимальное количесто строк заказа - " + maxCountOrderLines)
                .withType(Notifications.NotificationType.HUMANIZED)
                .show();
    }

    @Subscribe("exclude")
    public void onExcludeClick(Button.ClickEvent event) {
        orderLinesDc.getMutableItems().remove(orderLinesTable.getSingleSelected());
    }

    @Subscribe("generate")
    public void onGenerateClick(Button.ClickEvent event) {
        orderLinesDc.getMutableItems().clear();
        orderLinesDc.getMutableItems().addAll(getRandomOrderLines());
    }

    @Subscribe("changeQuantityBtn")
    public void onChangeQuantityBtnClick(Button.ClickEvent event) {
        OrderLine selectedOrderLine = orderLinesTable.getSingleSelected();
        String newQuantityStr = newQuantityField.getValue();

        if (selectedOrderLine != null && newQuantityStr != null) {
            try {
                int newQuantity = Integer.parseInt(newQuantityStr);
                if (newQuantity > 0) {
                    selectedOrderLine.setQuantity(newQuantity);
                    orderLinesDc.replaceItem(selectedOrderLine);
                    updateTotalCost();
                } else {
                    notifications.create()
                            .withCaption("Количество должно быть положительным")
                            .withType(Notifications.NotificationType.WARNING)
                            .show();
                }
            } catch (NumberFormatException e) {
                notifications.create()
                        .withCaption("Неверный формат числа")
                        .withType(Notifications.NotificationType.WARNING)
                        .show();
            }
        } else {
            notifications.create()
                    .withCaption("Выберите строку заказа и введите новое количество")
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
        }
    }

    @Subscribe("closeBtn")
    public void onCloseBtnClick(Button.ClickEvent event) {
        closeWithDiscard();
    }

    @Subscribe("confirmBtn")
    public void onConfirmBtnClick(Button.ClickEvent event) {
        if (!orderLinesDc.getMutableItems().isEmpty()) {
            getEditedEntity().setStatus(Status.CONFIRMED);
        }
    }

    @Subscribe(id = "orderLinesDc", target = Target.DATA_CONTAINER)
    public void onOrderLinesDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderLine> event) {
        updateTotalCost();
        commitAndCloseBtn.setEnabled(!orderLinesDc.getMutableItems().isEmpty());
    }

    private void updateTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLinesDc.getMutableItems()) {
            totalCost = totalCost.add(orderLine.getBookPrice());
            totalCost = totalCost.multiply(BigDecimal.valueOf(orderLine.getQuantity()));
        }
        totalCostField.setValue(totalCost);
    }

    private List<OrderLine> getRandomOrderLines() {
        List<OrderLine> orderLines = new ArrayList<>();
        List<Book> books = bookService.getRandomBooks(maxCountOrderLines);
        for (Book book : books) {
            orderLines.add(getOrderLineByBook(book));
        }
        return orderLines;
    }

    private OrderLine getRandUniqOrderLine() {
        List<Book> availableBooks = bookService.getAvailableBooks();
        List<Book> booksInOrder = orderLinesDc.getMutableItems().stream().map(OrderLine::getBook)
                .collect(Collectors.toList());
        if (availableBooks.size() <= booksInOrder.size()) {
            return null;
        }
        List<Book> filteredBooks = availableBooks.stream().filter(book -> !booksInOrder.contains(book))
                .collect(Collectors.toList());
        int randomIndex = ThreadLocalRandom.current().nextInt(filteredBooks.size());
        Book randomBook = filteredBooks.get(randomIndex);
        return getOrderLineByBook(randomBook);
    }

    private OrderLine getOrderLineByBook(Book book) {
        OrderLine orderLine = dataManager.create(OrderLine.class);
        orderLine.setBook(book);
        orderLine.setBookTitle(book.getTitle());
        orderLine.setBookPrice(book.getPrice());
        orderLine.setQuantity(1);
        orderLine.setOnlineOrder(getEditedEntity());

        return orderLine;
    }

    private boolean isAvailableBooks() {
        return !bookService.getAvailableBooks().isEmpty();
    }
}