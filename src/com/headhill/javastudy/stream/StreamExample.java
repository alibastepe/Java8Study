package com.headhill.javastudy.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class StreamExample {
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

    private List<String> getThreeHighCaloricDishNames()  {
        return menu.stream()
                   .filter(d -> d.getCalories() > 300)
                   .map(Dish::getName)
                   .limit(3)
                   .collect(toList());
    }

    private void traverseOnlyOnce()  {
        List<String> title = Arrays.asList("Modern", "Java", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);   //ERROR since stream is consumed
    }

    private void printDistinctElements()  {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    private void sliceWithPredicate()  {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        //since special menu is sorted on calories, takeWhile method can be used
        List<Dish> slicedMenu1  = specialMenu.stream()
                .filter(d -> d.getCalories() < 320)  //checks all the stream, performance issues?
               // .takeWhile(dish -> dish.getCalories() < 320)   available in java 9

                .collect(toList());

        List<Dish> slicedMenu2 = specialMenu.stream()
                .filter(d -> d.getCalories() > 320)
              //  .dropWhile(dish -> dish.getCalories() < 320)   available in java 9
                .collect(toList());
    }

    private void flatMapExample()  {
        List<String> words = Arrays.asList("Hello", "World");

        //two examples below does not work because the map methods do not produce Stream<String>
        words.stream()
                .map(word -> word.split(""))        //this produces Stream<String[]>
                .distinct()
                .collect(toList());


        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)  //this produces Stream<Stream<String>>
                .distinct()
                .collect(toList());

        //flatMap is the correct way to solve
        List<String> uniqueCharacters =
                words.stream()
                        .map(word -> word.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());



        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());
    }


    private void findMatchExample()  {
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
        dish.ifPresent(System.out::println);

        Optional<Dish> dish2 =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findFirst();
        dish.ifPresent(System.out::println);

        boolean anyMatch =
                menu.stream()
                        .anyMatch(Dish::isVegetarian);

        boolean allMatch =
                menu.stream()
                        .allMatch(Dish::isVegetarian);

        //IntStream example
        IntStream is = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = is.boxed();

        OptionalInt oi = is.max();
        oi.orElse(1);

    }

    private void reduceExample()  {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().reduce(0, (a,b) -> a+b);
        int sum2 = numbers.stream().reduce(0, Integer::sum);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        long count = numbers.stream().count();
    }

    private void mixedExample()  {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // Find all transactions in 2011 and sort by value (small to high)
        transactions.stream()
                    .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue));

        //What are all the unique cities where the traders work?
        transactions.stream().map(t -> t.getTrader().getCity()).distinct();

        //Finds all traders from Cambridge and sort them by name
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName));

        //Returns a string of all traders’ names sorted alphabetically
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1+s2);

        //Are any traders based in Milan?
        transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        //Prints all transactions’ values from the traders living in Cambridge
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .forEach(t -> System.out.println(t.getValue()));

        //What’s the highest value of all the transactions?
        transactions.stream()
                .map(t -> t.getValue())
                .reduce(Integer::max);

    }

    public class Trader {
        private final String name;
        private final String city;
        public Trader(String n, String c){
            this.name = n;
            this.city = c;
        }
        public String getName(){
            return this.name;
        }
        public String getCity(){
            return this.city;
        }
        public String toString(){
            return "Trader:"+this.name + " in " + this.city;
        }
    }

    public class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;
        public Transaction(Trader trader, int year, int value){
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
        public Trader getTrader(){
            return this.trader;
        }
        public int getYear(){
            return this.year;
        }
        public int getValue(){
            return this.value;
        }
        public String toString(){
            return "{" + this.trader + ", " +
                    "year: "+this.year+", " +
                    "value:" + this.value +"}";
        }
    }




}
