package ru.vladrus13.parser;

import java.util.Map;

public class BinaryOperation implements Expression {

    protected Expression left;
    protected Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Caclulate result
     * @return result
     */
    public int evaluate(Map<String, Integer> variables) {
        throw new UnsupportedOperationException();
    }
}
