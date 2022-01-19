package com.api.spring_restapi.Specification;

import com.api.spring_restapi.Entity.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrderSpecification implements Specification<Order> {

    private final SearchCriteria criteriaSearch;

    public OrderSpecification(SearchCriteria criteria){this.criteriaSearch = criteria;}

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteriaSearch.getOperation()){
            case GREATER_THAN:
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteriaSearch.getKey()), criteriaSearch.getValue().toString());
            case LESS_THAN:
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteriaSearch.getKey()), criteriaSearch.getValue().toString());
            case EQUALITY:
                if (root.get(criteriaSearch.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            root.get(criteriaSearch.getKey()), "%" + criteriaSearch.getValue() + "%");
                } else {
                    return criteriaBuilder.equal(root.get(criteriaSearch.getKey()), criteriaSearch.getValue());
                }
            case JOIN:
            default:
                return null;
        }
    }
}
