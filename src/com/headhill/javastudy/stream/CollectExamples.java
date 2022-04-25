package com.headhill.javastudy.stream;

import java.util.*;

import static java.util.stream.Collectors.*;

public class CollectExamples {
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public CollectExamples()  {
        Long dishCount = menu.stream().collect(counting());

        Optional<Dish> maxCalorieDish = menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories)));
        Optional<Dish> maxCalorieDish2 = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

        int totalCalories3 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int totalCalories4 = menu.stream().mapToInt(Dish::getCalories).sum();


        double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics stats = menu.stream().collect(summarizingInt(Dish::getCalories));

        menu.stream().map(Dish::getName).collect(joining(", "));  //internally uses StringBuilder

    }
}
