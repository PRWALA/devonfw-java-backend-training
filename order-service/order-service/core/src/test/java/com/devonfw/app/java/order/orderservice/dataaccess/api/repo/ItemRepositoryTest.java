package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author PRWALA
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ItemRepositoryTest extends ComponentTest {

  @Inject
  private ItemRepository itemRepository;

  @Test
  public void shouldFindAllItems() {

    // when
    List<ItemEntity> foundItems = this.itemRepository.findAll();

    // then
    assertThat(foundItems).hasSize(1);
  }

  @Test
  public void shouldFindItemByName() {

    // given
    Sort sort = Sort.by("name");
    sort.ascending();
    Pageable pageable = PageRequest.of(0, 20, sort);

    // when
    Page<ItemEntity> foundItem = this.itemRepository.findItemByName("spaghetti bolognese", pageable);

    // then
    assertThat(foundItem).hasSize(1);
    assertTrue(foundItem.getContent().get(0).getName().equals("spaghetti bolognese"));

  }

  @Test
  public void shouldUpdatedItemPriceById() {

    this.itemRepository.updateItemPriceWithGivenId(21L, 35D);

    Optional<ItemEntity> item = this.itemRepository.findById(21L);

    assertTrue(item.isPresent());

    assertTrue(item.get().getPrice().equals(35D));

  }

  // @Override
  // protected void doTearDown() {
  //
  // super.doTearDown();
  // // TODO: call here delete for all entities related to this test class
  // this.itemRepository.deleteAll();
  // }

}
