package ru.vladrus13.parser;

import java.util.Map;

public class Const implements Expression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    public int evaluate(Map<String, Integer> variables) {
        return value;
    }
}
