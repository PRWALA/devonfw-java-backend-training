package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author PRWALA
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderRepositoryTest extends ComponentTest {

  @Inject
  private OrderRepository orderRepository;

  @Test
  public void shouldFindAllOrders() {

    // when
    List<OrderEntity> foundOrders = this.orderRepository.findAll();

    // then
    assertThat(foundOrders).hasSize(1);
  }

  @Test
  public void findOrdersFromGivenDayWithSpecificStatus() {

    // given
    LocalDate localDate = LocalDate.parse("2019-03-15");

    // when
    List<OrderEntity> foundOrders = this.orderRepository.findOrdersFromGivenDayWithSpecificStatus(localDate,
        OrderStatus.SERVED);

    // then
    assertThat(foundOrders).hasSize(1);

  }

}
