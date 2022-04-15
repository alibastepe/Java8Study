package com.headhill.javastudy.behaviorparam;

public class AppleHeavyWeightPredicate implements  ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
