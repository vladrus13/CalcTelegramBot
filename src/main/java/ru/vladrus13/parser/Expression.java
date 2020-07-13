package ru.vladrus13.parser;

import java.util.Map;

public interface Expression {
    int evaluate(Map<String, Integer> variables);
    String toString();
}
