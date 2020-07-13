package ru.vladrus13.parser.operations;

import ru.vladrus13.parser.BinaryOperation;
import ru.vladrus13.parser.Expression;

import java.util.Map;

public class Multiply extends BinaryOperation {
    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) * right.evaluate(variables);
    }
}
