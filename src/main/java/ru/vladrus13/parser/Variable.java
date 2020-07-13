package ru.vladrus13.parser;

import java.util.Map;

public class Variable implements Expression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(Map<String, Integer> variables) {
        return variables.get(variable);
    }
}
