package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author PRWALA
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerRepositoryTest extends ComponentTest {

  @Inject
  private CustomerRepository customerRepository;

  @Test
  public void shouldFindAllCustomer() {

    // when
    List<CustomerEntity> foundOrders = this.customerRepository.findAll();

    // then
    assertThat(foundOrders).hasSize(1);
  }

  @Test
  public void shouldDeleteCustomer() {

    // when
    this.customerRepository.removeCustomerById(31L);

    // then
    List<CustomerEntity> foundOrders = this.customerRepository.findAll();
    assertThat(foundOrders).hasSize(0);
  }

}
