package com.devonfw.app.java.order.orderservice.logic.api.to;

import com.devonfw.module.basic.common.api.to.AbstractCto;

/**
 * Composite transport object of Order
 */
public class OrderCto extends AbstractCto {

  private static final long serialVersionUID = 1L;

  private OrderEto order;

  private CustomerEto owner;

  public OrderEto getOrder() {

    return order;
  }

  public void setOrder(OrderEto order) {

    this.order = order;
  }

  public CustomerEto getOwner() {

    return owner;
  }

  public void setOwner(CustomerEto owner) {

    this.owner = owner;
  }

}
