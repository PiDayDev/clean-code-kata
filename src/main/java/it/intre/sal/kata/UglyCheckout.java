package it.intre.sal.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UglyCheckout implements Checkout {

    @Override
    public int pay(List<String> items, Map<String, Entry<Integer, Integer>> offers) {
        Cart cart = new Cart();
        Map<String, Integer> counters = new HashMap<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 50);
        map.put("pear", 30);
        map.put("pineapple", 220);
        map.put("banana", 60);

        for (String item : items) {
            Integer count = counters.getOrDefault(item, 0);
            counters.put(item, count + 1);
        }

        int apple = counters.getOrDefault("apple", 0);
        int pear = counters.getOrDefault("pear", 0);
        int ananas = counters.getOrDefault("pineapple", 0);
        int banana = counters.getOrDefault("banana", 0);
        cart.add("apple", apple);
        cart.add("pear", pear);
        cart.add("pineapple", ananas);
        cart.add("banana", banana);

        return cart.computeTotal(offers, map);
    }

}
