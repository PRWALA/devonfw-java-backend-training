package com.devonfw.app.java.order.orderservice.logic.api.to;

import java.util.Set;

import com.devonfw.module.basic.common.api.to.AbstractCto;

/**
 * Composite transport object of Item
 */
public class ItemCto extends AbstractCto {

  private static final long serialVersionUID = 1L;

  private ItemEto item;

  private Set<ItemEto> orderPositions;

  public ItemEto getItem() {

    return item;
  }

  public void setItem(ItemEto item) {

    this.item = item;
  }

  public Set<ItemEto> getOrderPositions() {

    return orderPositions;
  }

  public void setOrderPositions(Set<ItemEto> orderPositions) {

    this.orderPositions = orderPositions;
  }

}
