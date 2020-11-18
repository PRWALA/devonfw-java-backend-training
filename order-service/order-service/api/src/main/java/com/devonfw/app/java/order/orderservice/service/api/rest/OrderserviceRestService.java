package com.devonfw.app.java.order.orderservice.service.api.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;

/**
 * The service interface for REST calls in order to execute the logic of component {@link Orderservice}.
 */
@Path("/orderservice/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OrderserviceRestService {

  /**
   * Delegates to {@link Orderservice#findItem}.
   *
   * @param id the ID of the {@link ItemEto}
   * @return the {@link ItemEto}
   */
  @GET
  @Path("/item/{id}/")
  public ItemEto getItem(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#saveItem}.
   *
   * @param item the {@link ItemEto} to be saved
   * @return the recently created {@link ItemEto}
   */
  @POST
  @Path("/item/")
  public ItemEto saveItem(ItemEto item);

  /**
   * Delegates to {@link Orderservice#deleteItem}.
   *
   * @param id ID of the {@link ItemEto} to be deleted
   */
  @DELETE
  @Path("/item/{id}/")
  public void deleteItem(@PathParam("id") long id);

  /**
   * Delegates to {@link Orderservice#findItemEtos}.
   *
   * @param searchCriteriaTo the pagination and search criteria to be used for finding items.
   * @return the {@link Page list} of matching {@link ItemEto}s.
   */
  @Path("/item/search")
  @POST
  public Page<ItemEto> findItems(ItemSearchCriteriaTo searchCriteriaTo);

  @GET
  @Path("/items/{name}")
  public Page<ItemEto> findItemByName(@PathParam("name") String name);

  @PUT
  @Path("/order/save/")
  public OrderCto saveOrder(OrderCto order);

}