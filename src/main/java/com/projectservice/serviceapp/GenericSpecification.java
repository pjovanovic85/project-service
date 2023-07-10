package com.projectservice.serviceapp;

import com.projectservice.serviceapp.model.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GenericSpecification<Entity> {

    public Specification<Entity> hasParameter(Map<String, String> parameters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                predicates.add(criteriaBuilder.like(root.get(entry.getKey()), "%" + entry.getValue() + "%"));
            }
            Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            return predicate;
        };
    }
}
