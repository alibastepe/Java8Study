package com.headhill.javastudy.methodref;

import com.headhill.javastudy.behaviorparam.Apple;
import com.headhill.javastudy.behaviorparam.Color;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static java.util.Comparator.comparing;

public class MethodRef {

    public MethodRef() {


        Supplier<Apple> c1 = Apple::new;  //default constructor
        Apple a1 = c1.get();

        BiFunction<Double, Color, Apple> bif = Apple::new;
        Apple myApple = bif.apply(10.0, Color.RED);

        List<Apple> inventory = Arrays.asList(c1.get(), c1.get(), bif.apply(100.0, Color.GREEN));
        inventory.sort(comparing(Apple::getWeight));


        //compose comparator examples
        inventory.sort(comparing(Apple::getWeight).reversed());  //weights in decreasing order
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        //compose predicate examples
        Predicate<Apple> redApples = a -> a.getColor().equals(Color.RED);
        Predicate<Apple> heavyApples = a -> a.getWeight() > 100;

        Predicate<Apple> notRedApples = redApples.negate();

        Predicate<Apple> redAndHeavyApples = redApples.and(heavyApples);

        Predicate<Apple> readAndHeavyAppleOrGreen = redAndHeavyApples.or(a -> a.getColor().equals(Color.GREEN));

        //compose function examples
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);  //first apply f and then apply g   g(f(x))
        int result = h.apply(1); // result is 4


        Function<Integer, Integer> k = f.compose(g); //first apply g and then f   f(g(x))
        result = k.apply(1);  //result is 3
    }
}
