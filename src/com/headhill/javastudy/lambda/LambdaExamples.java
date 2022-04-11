package com.headhill.javastudy.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class LambdaExamples {

    public LambdaExamples() throws IOException {
    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    String oneLine =  processFile((BufferedReader br) -> br.readLine());
    String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());


}
