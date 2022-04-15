package com.headhill.javastudy.lambda;

import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

public class FunctionExamples {

    IntFunction<String> myIntFunc = i -> String.valueOf(i);  //return to String

    ToIntFunction<String> myToIntFunc = s -> s.length();

    BiFunction<String, Integer, String> myBiFunc = (s, i ) -> s + " "+ i;

    ToIntBiFunction<String, String> mySecondBiFunc = (s1, s2) -> s1.length() + s2.length();
}
