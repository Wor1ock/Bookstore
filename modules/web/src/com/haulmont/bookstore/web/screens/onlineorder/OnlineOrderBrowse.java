package com.haulmont.bookstore.web.screens.onlineorder;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.OnlineOrder;

@UiController("bookstore_OnlineOrder.browse")
@UiDescriptor("online-order-browse.xml")
@LookupComponent("onlineOrdersTable")
@LoadDataBeforeShow
public class OnlineOrderBrowse extends StandardLookup<OnlineOrder> {
}