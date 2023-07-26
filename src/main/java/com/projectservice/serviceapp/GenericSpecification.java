package com.projectservice.serviceapp;

import com.projectservice.serviceapp.model.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
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


    public Specification<Entity> hasNestedParameter(Map<String, Object> parameters){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                String attributeName = entry.getKey();
                Object attributeValue = entry.getValue();

                Join<?, ?> join = null;
                String[] attributeNames = attributeName.split("\\.");

                for (int i = 0; i < attributeNames.length - 1; i++) {
                    if (join == null) {
                        join = root.join(attributeNames[i]);
                    } else {
                        join = join.join(attributeNames[i]);
                    }
                }
                if (attributeValue instanceof List){
                    predicates.add(join.get(attributeNames[attributeNames.length - 1]).in((List<?>) attributeValue));
                }else {
                    predicates.add(criteriaBuilder.like(join.get(attributeNames[attributeNames.length - 1]), "%" + attributeValue + "%"));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
