package com.bootdo.activiti.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Variables implements Serializable {
    private String name;
    private List<Variable> variables;

    public Variables() {
        this.name = name;
        this.variables = new ArrayList<Variable>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

    public void setVariables(final List<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "Variables{" +
                "name='" + name + '\'' +
                ", variables=" + variables +
                '}';
    }
}
