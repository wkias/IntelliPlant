package com.bootdo.activiti.domain;


import java.io.Serializable;

/**
 *
 */
public class Variable implements Serializable {

    private String keys;
    private String values;
    private String types;

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "keys='" + keys + '\'' +
                ", values='" + values + '\'' +
                ", types='" + types + '\'' +
                '}';
    }
}
