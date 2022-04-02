package com.davidoladeji.flexisaf.specification;

import com.davidoladeji.flexisaf.data.SpecificationEnum;

public class SearchCriteria {
    private String key;
    private Object value;
    private SpecificationEnum operation;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, Object value, SpecificationEnum operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SpecificationEnum getOperation() {
        return operation;
    }

    public void setOperation(SpecificationEnum operation) {
        this.operation = operation;
    }
}