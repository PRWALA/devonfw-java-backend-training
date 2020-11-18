package com.devonfw.app.java.order.orderservice.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

/**
 * The service implementation for REST calls in order to execute the logic of component {@link Orderservice}.
 */
@Named("OrderserviceRestService")
public class OrderserviceRestServiceImpl implements OrderserviceRestService {

  @Inject
  private Orderservice orderservice;

  @Override
  public ItemEto getItem(long id) {

    return this.orderservice.findItem(id);
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    return this.orderservice.saveItem(item);
  }

  @Override
  public void deleteItem(long id) {

    this.orderservice.deleteItem(id);
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo searchCriteriaTo) {

    return this.orderservice.findItems(searchCriteriaTo);
  }

  @Override
  public Page<ItemEto> findItemByName(String name) {

    return this.orderservice.findItemByName(name);

  }

  @Override
  public OrderCto saveOrder(OrderCto order) {

    // TODO Auto-generated method stub
    return null;
  }
}