package com.headhill.javastudy.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.headhill.javastudy.methodref.Color.GREEN;
import static com.headhill.javastudy.methodref.Color.RED;

public class AppleMain {
    static List<Apple> myInventory = Arrays.asList(new Apple(80.0, GREEN),
            new Apple(155.0, GREEN),
            new Apple(120.0, RED));

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {

        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> redApples = filterApples(myInventory, (Apple a) -> a.getColor().equals(RED));
        System.out.println("redApples = " + redApples);
        redApples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        Thread t = new Thread(() -> System.out.println("guku"));
        t.start();
    }
}
