package com.headhill.javastudy.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExamples {


    static public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T t: list) {
            if(p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    static Predicate<String> myStringFilter = (s) -> s.startsWith("ww");

    public static void main(String[] args) {
        filter(Arrays.asList("ali", "wwedf", "sdfsdfs", "vfg"), myStringFilter);
    }
}
