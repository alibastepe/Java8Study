package com.headhill.javastudy.lambda;

import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;

public class ConsumerExamples {

    BiConsumer<String, Integer> myConsumer = (s,i) -> System.out.println(s + i);

    ObjIntConsumer<String> mySecondConsumer = (s,i) -> System.out.println(s + i);
}
