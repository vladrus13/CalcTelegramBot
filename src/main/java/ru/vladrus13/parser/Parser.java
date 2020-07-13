package ru.vladrus13.parser;

import ru.vladrus13.parser.operations.Add;
import ru.vladrus13.parser.operations.Divide;
import ru.vladrus13.parser.operations.Multiply;
import ru.vladrus13.parser.operations.Subtract;

public class Parser {
    String parsed;
    int position;

    private boolean isEnd() {
        return position >= parsed.length();
    }

    private void skipSpaces() {
        while (!isEnd() && Character.isWhitespace(parsed.charAt(position))) position++;
    }

    private Expression parseMono() {
        skipSpaces();
        if (parsed.charAt(position) == '(') {
            position++;
            Expression expression = parseAddSubtract();
            position++;
            return expression;
        }
        if (Character.isLowerCase(parsed.charAt(position))) {
            int start = position;
            while (!isEnd() && Character.isLowerCase(parsed.charAt(position))) {
                position++;
            }
            return new Variable(parsed.substring(start, position));
        }
        if (Character.isDigit(parsed.charAt(position))) {
            int start = position;
            while (!isEnd() && Character.isDigit(parsed.charAt(position))) {
                position++;
            }
            return new Const(Integer.parseInt(parsed.substring(start, position)));
        }
        return new Error();
    }

    private Expression parseMultiplyDivide() {
        Expression expression = parseMono();
        skipSpaces();
        while (true) {
            if (isEnd()) return expression;
            switch (parsed.charAt(position)) {
                case '*':
                    position++;
                    expression = new Multiply(expression, parseMono());
                    break;
                case '/':
                    position++;
                    expression = new Divide(expression, parseMono());
                    break;
                default:
                    return expression;
            }
        }
    }

    private Expression parseAddSubtract() {
        Expression expression = parseMultiplyDivide();
        skipSpaces();
        while (true) {
            if (isEnd()) return expression;
            switch (parsed.charAt(position)) {
                case '+':
                    position++;
                    expression = new Add(expression, parseMultiplyDivide());
                    break;
                case '-':
                    position++;
                    expression = new Subtract(expression, parseMultiplyDivide());
                    break;
                default:
                    return expression;
            }
        }
    }

    public Expression parse(String s) {
        position = 0;
        parsed = s;
        if (isEnd()) return new Error();
        try {
            return parseAddSubtract();
        } catch (Exception e) {
            return new Error();
        }
    }
}
