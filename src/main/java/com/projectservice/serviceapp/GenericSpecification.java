package com.projectservice.serviceapp;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GenericSpecification<Entity> {

    private static final List<String> EXCLUDE_PARAMS = Arrays.asList(new String[]{"pageNumber", "pageSize", "sortOrder", "sortField"});

    public Specification<Entity> hasParameter(Map<String, String> parameters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String attributeName = entry.getKey();
                if (!EXCLUDE_PARAMS.contains(attributeName)) {
                    if ("id".equalsIgnoreCase(attributeName)){
                        predicates.add(criteriaBuilder.equal(root.get(attributeName), entry.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.like(root.get(attributeName), "%" + entry.getValue() + "%"));
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    public Specification<Entity> hasNestedParameter(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                String attributeName = entry.getKey();
                if (!EXCLUDE_PARAMS.contains(attributeName)) {
                    Object attributeValue = entry.getValue();
                    Join<?, ?> join = null;
                    String[] attributeNames = attributeName.split("\\.");
                    if (attributeNames.length == 1){
                        if (attributeValue instanceof List) {
                            predicates.add(root.get(attributeNames[attributeNames.length - 1]).in((List<?>) attributeValue));
                        } else if ("id".equalsIgnoreCase(attributeNames[attributeNames.length - 1])) {
                            predicates.add(criteriaBuilder.equal(root.get(attributeNames[attributeNames.length - 1]), entry.getValue()));
                        } else {
                            predicates.add(criteriaBuilder.like(root.get(attributeNames[attributeNames.length - 1]), "%" + entry.getValue() + "%"));
                        }
                    }else {
                        for (int i = 0; i < attributeNames.length - 1; i++) {
                            if (join == null) {
                                join = root.join(attributeNames[i]);
                            } else {
                                join = join.join(attributeNames[i]);
                            }
                            if (attributeValue instanceof List) {
                                predicates.add(join.get(attributeNames[attributeNames.length - 1]).in((List<?>) attributeValue));
                            } else {
                                predicates.add(criteriaBuilder.like(join.get(attributeNames[attributeNames.length - 1]), "%" + attributeValue + "%"));
                            }
                        }
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
