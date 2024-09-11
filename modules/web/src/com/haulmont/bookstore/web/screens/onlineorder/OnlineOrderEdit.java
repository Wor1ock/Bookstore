package com.haulmont.bookstore.web.screens.onlineorder;

import com.haulmont.bookstore.config.OrdersConfig;
import com.haulmont.bookstore.entity.Customer;
import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.bookstore.entity.OrderLine;
import com.haulmont.bookstore.entity.Status;
import com.haulmont.bookstore.service.CustomerByUserService;
import com.haulmont.bookstore.web.OrderLineBean;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

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
    private TextField<BigDecimal> totalCostField;
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private OrderLineBean orderLineBean;
    @Inject
    private DataContext dataContext;
    @Inject
    private CustomerByUserService customerByUserService;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private Button addOrderLineBtn;
    @Inject
    private PickerField<Customer> customerField;
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onInitEntity(InitEntityEvent<OnlineOrder> event) {
        event.getEntity().setStatus(Status.NEW);
        Customer customer = customerByUserService.getCustomerByUser(userSessionSource.getUserSession()
                .getUser());
        event.getEntity().setCustomer(customer);
        customerField.setEditable(false);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        List<OrderLine> orderLines = orderLinesDc.getMutableItems();
        if (getEditedEntity().getStatus() == Status.CONFIRMED){
            notifications.create().withCaption(messageBundle.getMessage("isUnavailableToEdit")).show();
            getWindow().getComponent("form").setEnabled(false);
            getWindow().getComponent("orderLinesBox").setEnabled(false);
            getWindow().getComponent("confirmBtn").setEnabled(false);
        }
        if (orderLines.size() == ordersConfig.getMaxCountOrderLines()){
            addOrderLineBtn.setEnabled(false);
        }
    }

    @Subscribe("addOrderLineBtn")
    public void onAddOrderLineBtnClick(Button.ClickEvent event) {
        addOrderLinesInOrder(1);
    }

    @Subscribe("generate")
    public void onGenerateClick(Button.ClickEvent event) {
        addOrderLinesInOrder(ordersConfig.getMaxCountOrderLines() - orderLinesDc.getMutableItems().size());
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        dataContext.merge(getEditedEntity().getOrderLines());
    }

    private void addOrderLinesInOrder(int quantity) {
        if (orderLinesDc.getMutableItems().size() < ordersConfig.getMaxCountOrderLines()) {
            List<OrderLine> orderLines = orderLineBean.createOrderLines(orderLinesDc.getMutableItems(),
                    getEditedEntity(), quantity);
            if (!orderLines.isEmpty()) {
                orderLinesDc.getMutableItems().addAll(orderLines);
                totalCostField.setValue(getEditedEntity().getTotalCost());
            } else {
                notifications.create().withCaption(messageBundle.getMessage("noMoreBooks")).show();
            }
        } else {
            notifications.create().withCaption(messageBundle.getMessage("orderLinesLimit")).show();
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
            getWindow().getComponent("form").setEnabled(false);
            getWindow().getComponent("orderLinesBox").setEnabled(false);
        } else {
            notifications.create().withCaption(messageBundle.getMessage("noOrderLines")).show();
        }
    }

    @Subscribe(id = "orderLinesDc", target = Target.DATA_CONTAINER)
    public void onOrderLinesDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderLine> event) {
        totalCostField.setValue(getEditedEntity().getTotalCost());
        commitAndCloseBtn.setEnabled(!orderLinesDc.getMutableItems().isEmpty());
    }

    @Subscribe(id = "orderLinesDc", target = Target.DATA_CONTAINER)
    public void onOrderLinesDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<OrderLine> event) {
        totalCostField.setValue(getEditedEntity().getTotalCost());
    }
}