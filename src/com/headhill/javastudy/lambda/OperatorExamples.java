package com.headhill.javastudy.lambda;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class OperatorExamples {

    UnaryOperator<String> myUnaryOperator = s -> s.substring(1);

    IntUnaryOperator myIntUnaryOperator = i -> i*3;

    BinaryOperator<String> myBinaryOperator = (s1, s2) -> s1 + s2;

    IntBinaryOperator myIntBinaryOperator = (i1, i2) ->  i1 * i2;
}
