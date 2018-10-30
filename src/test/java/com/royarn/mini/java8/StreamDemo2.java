package com.royarn.mini.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo2 {

    public static void main(String[] args) {

        //base data
        List<Trader> traders = new ArrayList<>();
        traders.add(new Trader("blink", "USA"));
        traders.add(new Trader("queen", "CHINA"));
        traders.add(new Trader("ych", "AUSTIN"));
        traders.add(new Trader("sven", "USA"));
        List<Trader.Transcation> transcations = new ArrayList<>();
        transcations.add(new Trader.Transcation("blink", 2011, 2330d));
        transcations.add(new Trader.Transcation("queen", 2018, 235465324d));
        transcations.add(new Trader.Transcation("blink", 2016, 57867d));
        transcations.add(new Trader.Transcation("ych", 2011, 677867d));
        transcations.add(new Trader.Transcation("sven", 2011, 9800367d));
        transcations.add(new Trader.Transcation("queen", 2016, 267d));

        //find 2011s all amount and sort
        transcations.stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted(Comparator.comparing(Trader.Transcation::getAmount))
                .collect(Collectors.toList());

        //find the traders where they are from
        traders.stream()
                .map(trader -> trader.getCountry())
                .distinct()
                .collect(Collectors.toList());

        //find traders which come from USA
        traders.stream()
                .filter(trader -> "USA".equals(trader.getCountry()))
                .map(Trader::getName)
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());

        //find traders from USA
        traders.stream()
                .anyMatch(trader -> trader.getCountry().equals("CHINA"));

        //find traders from CHINA
        traders.stream()
                .filter(trader -> trader.getCountry().equals("CHINA"))
                .findAny();

        //find traders from USA
        transcations.stream()
                .map(trans -> trans.getAmount().intValue())
                .reduce(0, (a, b) -> a + b);

        //find max value
        transcations.stream()
                .max(Comparator.comparing(Trader.Transcation::getAmount));

        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                IntStream.rangeClosed(a, 100)
                .mapToObj(
                        b -> new double[] {a, b, Math.sqrt(a*a + b*b)}
                )
                .filter(t -> t[2] % 1 ==0));

        Stream.of("blink", "Java8", "shadow", "anti-mag")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}

class Trader {

    private String name;
    private String country;

    public Trader(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    static class Transcation {

        private String name;
        private Integer year;
        private Double amount;

        public Transcation(String name, Integer year, Double amount) {
            this.name = name;
            this.year = year;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }
}
