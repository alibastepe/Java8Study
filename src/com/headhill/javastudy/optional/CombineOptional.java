package com.headhill.javastudy.optional;


import java.util.Optional;

public class CombineOptional {

    public CombineOptional() {

    }

    public Insurance findCheapestInsurance(Person person, Car car) {
// queries services provided by the different insurance companies
// compare all those data
        return new Insurance();
    }



//    Here, you invoke a flatMap on the first optional, so if this optional is empty, the
//    lambda expression passed to it won’t be executed, and this invocation will return an
//    empty optional. Conversely, if the person is present, flatMap uses it as the input to
//    a Function returning an Optional<Insurance> as required by the flatMap method.
//    The body of this function invokes a map on the second optional, so if it doesn’t contain
//    any Car, the Function returns an empty optional, and so does the whole null-
//    SafeFindCheapestInsurance method. Finally, if both the Person and the Car are
//    present, the lambda expression passed as an argument to the map method can safely
//    invoke the original findCheapestInsurance method with them
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

}
