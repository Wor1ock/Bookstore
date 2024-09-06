package com.haulmont.bookstore.web.screens.onlineorder;

import com.haulmont.bookstore.config.OrdersConfig;
import com.haulmont.bookstore.entity.Book;
import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.bookstore.entity.OrderLine;
import com.haulmont.bookstore.entity.Status;
import com.haulmont.bookstore.service.AvailableBookService;
import com.haulmont.bookstore.service.OrderLineService;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@UiController("bookstore_OnlineOrder.edit")
@UiDescriptor("online-order-edit.xml")
@EditedEntityContainer("onlineOrderDc")
@LoadDataBeforeShow
public class OnlineOrderEdit extends StandardEditor<OnlineOrder> {
    @Inject
    private OrdersConfig ordersConfig;
    @Inject
    private Button commitAndCloseBtn;
    @Inject
    private CollectionPropertyContainer<OrderLine> orderLinesDc;
    @Inject
    private AvailableBookService bookService;
    @Inject
    private TextField<BigDecimal> totalCostField;
    @Inject
    private Notifications notifications;
    @Inject
    private OrderLineService orderLineService;
    @Inject
    private MessageBundle messageBundle;

    @Subscribe
    public void onInitEntity(InitEntityEvent<OnlineOrder> event) {
        event.getEntity().setStatus(Status.NEW);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        commitAndCloseBtn.setEnabled(false);
    }

    @Subscribe("addOrderLineBtn")
    public void onAddOrderLineBtnClick(Button.ClickEvent event) {
        OrderLine orderLine = getRandUniqOrderLine();
        if (orderLine != null) {
            if (orderLinesDc.getMutableItems().size() < ordersConfig.getMaxCountOrderLines()) {
                orderLinesDc.getMutableItems().add(orderLine);
                updateTotalCost();
            } else {
                String message = messageBundle.getMessage("orderLinesLimit");
                notifications.create().withCaption(message + " - " + ordersConfig.getMaxCountOrderLines()).show();
            }
        } else {
            String message = messageBundle.getMessage("noMoreBooks");
            notifications.create().withCaption(message).show();
        }
    }

    @Subscribe("generate")
    public void onGenerateClick(Button.ClickEvent event) {
        OrderLine orderLine = getRandUniqOrderLine();
        while (orderLine != null) {
            if (orderLinesDc.getMutableItems().size() < ordersConfig.getMaxCountOrderLines()) {
                orderLinesDc.getMutableItems().add(orderLine);
                orderLine = getRandUniqOrderLine();
            } else {
                String message = messageBundle.getMessage("orderLinesLimit");
                notifications.create().withCaption(message + " - " + ordersConfig.getMaxCountOrderLines()).show();
                break;
            }
        }
        if (orderLine == null && orderLinesDc.getMutableItems().size() < ordersConfig.getMaxCountOrderLines()) {
            String message = messageBundle.getMessage("noMoreBooks");
            notifications.create().withCaption(message).show();
        }
        updateTotalCost();
    }

    @Subscribe("closeBtn")
    public void onCloseBtnClick(Button.ClickEvent event) {
        closeWithDiscard();
    }

    @Subscribe("confirmBtn")
    public void onConfirmBtnClick(Button.ClickEvent event) {
        if (!orderLinesDc.getMutableItems().isEmpty()) {
            getEditedEntity().setStatus(Status.CONFIRMED);
        } else {
            String message = messageBundle.getMessage("noOrderLines");
            notifications.create().withCaption(message).show();
        }
    }

    @Subscribe(id = "orderLinesDc", target = Target.DATA_CONTAINER)
    public void onOrderLinesDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderLine> event) {
        updateTotalCost();
        commitAndCloseBtn.setEnabled(!orderLinesDc.getMutableItems().isEmpty());
    }

    @Subscribe(id = "orderLinesDc", target = Target.DATA_CONTAINER)
    public void onOrderLinesDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<OrderLine> event) {
        updateTotalCost();
    }

    private void updateTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLinesDc.getMutableItems()) {
            totalCost = totalCost.add(orderLine.getBookPrice());
            totalCost = totalCost.multiply(BigDecimal.valueOf(orderLine.getQuantity()));
        }
        totalCostField.setValue(totalCost);
    }

    private OrderLine getRandUniqOrderLine() {
        List<Book> availableBooks = bookService.getAllBooks();
        List<Book> booksInOrder = orderLinesDc.getMutableItems().stream().map(OrderLine::getBook)
                .collect(Collectors.toList());
        if (availableBooks.size() <= booksInOrder.size()) {
            return null;
        }
        List<Book> filteredBooks = availableBooks.stream().filter(book -> !booksInOrder.contains(book))
                .collect(Collectors.toList());
        int randomIndex = ThreadLocalRandom.current().nextInt(filteredBooks.size());
        Book randomBook = filteredBooks.get(randomIndex);
        return createOrderLineByBook(randomBook);
    }

    private OrderLine createOrderLineByBook(Book book) {
        OrderLine orderLine = orderLineService.createByBook(book);
        orderLine.setBook(book);
        orderLine.setBookTitle(book.getTitle());
        orderLine.setBookPrice(book.getPrice());
        orderLine.setQuantity(1);
        orderLine.setOnlineOrder(getEditedEntity());

        return orderLine;
    }
}