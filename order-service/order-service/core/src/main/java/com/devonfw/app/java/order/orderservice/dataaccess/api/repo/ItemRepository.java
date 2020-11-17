package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * @author PRWALA
 *
 */
public interface ItemRepository extends DefaultRepository<ItemEntity> {

  default Page<ItemEntity> findByCriteria(ItemSearchCriteriaTo criteria) {

    ItemEntity alias = newDslAlias();
    JPAQuery<ItemEntity> query = newDslQuery(alias);

    String name = criteria.getName();
    if (name != null && !name.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getName()), name, criteria.getNameOption());
    }

    // TODO: implement also expression for description and price

    String description = criteria.getDescription();

    if (description != null && !description.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getDescription()), description, criteria.getDescriptionOption());
    }

    Double price = criteria.getPrice();

    if (price != null) {
      query.where($(alias.getPrice()).eq(criteria.getPrice()));
    }

    // TODO: implement also sorting using addOrderBy

    if (criteria.getPageable() == null) {
      criteria.setPageable(PageRequest.of(0, Integer.MAX_VALUE));
    } else {
      addOrderBy(query, alias, criteria.getPageable().getSort());
    }

    // TODO: return found items using QueryUtil
    return QueryUtil.get().findPaginated(criteria.getPageable(), query, true);
  }

  default Page<ItemEntity> findItemByName(String name, Pageable pageable) {

    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setPageable(pageable);
    criteria.setName(name);

    return findByCriteria(criteria);

  }

  // @Query("update Item i set i.price = :price WHERE i.id = :itemId")
  // void updateItemPriceWithGivenId(@Param("itemId") Long id, @Param("price") Double price);

  default void updateItemPriceWithGivenId(Long id, Double price) {

    Optional<ItemEntity> itemEntity = findById(id);
    if (itemEntity.isPresent()) {
      itemEntity.get().setPrice(price);
      save(itemEntity.get());
    }
  }

  public default void addOrderBy(JPAQuery<ItemEntity> query, ItemEntity alias, Sort sort) {

    if (sort != null && sort.isSorted()) {
      Iterator<Order> it = sort.iterator();
      while (it.hasNext()) {
        Order next = it.next();
        switch (next.getProperty()) {
          case "name":
            if (next.isAscending()) {
              query.orderBy($(alias.getName()).asc());
            } else {
              query.orderBy($(alias.getName()).desc());
            }
            break;
          case "price":
            if (next.isAscending()) {
              query.orderBy($(alias.getPrice()).asc());
            } else {
              query.orderBy($(alias.getPrice()).desc());
            }
            break;
          case "description":
            if (next.isAscending()) {
              query.orderBy($(alias.getDescription()).asc());
            } else {
              query.orderBy($(alias.getDescription()).desc());
            }
            break;
          default:
            throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
        }
      }
    }
  }
}
