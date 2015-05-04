package com.sharanjit.mycalculator;


public class MathEval {

    public static String calculate(final String str) {
        class ParserString {
            int position = -1, cchar;

            void removeChar() {
                cchar = (++position < str.length()) ? str.charAt(position) : -1;
            }

            void removeWhiteSpace() {
                while (Character.isWhitespace(cchar)) removeChar();
            }

            String parse() {
                removeChar();
                double calc = parseStringExpression();
                if (cchar != -1) {
                    return "Illegal Expression";
                }
                return String.valueOf(calc);
            }

            double parseExpr() {
                double val;
                boolean minus = false;
                removeWhiteSpace();
                if (cchar == '(') {
                    removeChar();
                    val = parseStringExpression();
                    if (cchar == ')') {
                        removeChar();
                    }
                } else {
                    if (cchar == '+' || cchar == '-') {
                        minus = cchar == '-';
                        removeChar();
                        removeWhiteSpace();
                    }
                    StringBuilder sb = new StringBuilder();
                    while ((cchar >= '0' && cchar <= '9') || cchar == '.') {
                        sb.append((char)cchar);
                        removeChar();
                    }
                    if (sb.length() == 0) {
                        throw new RuntimeException("Illegal Expression");
                    }
                    val = Double.parseDouble(sb.toString());
                }
                removeWhiteSpace();
                if (cchar == '^') {
                    removeChar();
                    val = Math.pow(val, parseExpr());
                }
                if (minus) {
                    val = -val;
                }
                return val;
            }

            double parseStr() {
                double val = parseExpr();
                for (;;) {
                    removeWhiteSpace();
                    if (cchar == '/') {
                        removeChar();
                        val /= parseExpr();
                    } else if (cchar == '*' || cchar == '(') {
                        if (cchar == '*') {
                            removeChar();
                        }
                        val *= parseExpr();
                    } else {
                        return val;
                    }
                }
            }

            double parseStringExpression() {
                double val = parseStr();
                for (;;) {
                    removeWhiteSpace();
                    if (cchar == '+') {
                        removeChar();
                        val += parseStr();
                    } else if (cchar == '-') {
                        removeChar();
                        val -= parseStr();
                    } else {
                        return val;
                    }
                }
            }

        }
        return new ParserString().parse();
    }

}
