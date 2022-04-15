package com.headhill.javastudy.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {

    IntStream n = IntStream.of(1,2,3,4,5);

    public NumericStreams()  {
        IntStream evenNumbers = IntStream.rangeClosed(0, 100).filter(i -> (i % 2) == 0);
        IntStream.range(1, 100);  // 100 not included



        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                    .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );
    }
}
