package ru.vladrus13.parser.operations;

import ru.vladrus13.parser.BinaryOperation;
import ru.vladrus13.parser.Expression;

import java.util.Map;

public class Divide extends BinaryOperation {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) / right.evaluate(variables);
    }
}
