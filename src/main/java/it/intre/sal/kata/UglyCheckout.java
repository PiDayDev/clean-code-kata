package it.intre.sal.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static it.intre.sal.kata.Items.*;

public class UglyCheckout implements Checkout {

    @Override
    public int pay(List<String> items, Map<String, Entry<Integer, Integer>> offers) {
        Map<String, Integer> counters = new HashMap<>();

        Map<String, Integer> map = new HashMap<>();
        map.put(APPLE, 50);
        map.put(PEAR, 30);
        map.put(PINEAPPLE, 220);
        map.put(BANANA, 60);

        for (String item : items) {
            Integer count = counters.getOrDefault(item, 0);
            counters.put(item, count + 1);
        }

        int apple = counters.getOrDefault(APPLE, 0);
        int pear = counters.getOrDefault(PEAR, 0);
        int ananas = counters.getOrDefault(PINEAPPLE, 0);
        int banana = counters.getOrDefault(BANANA, 0);

        Cart cart = new Cart(offers, map);
        cart.add(APPLE, apple);
        cart.add(PEAR, pear);
        cart.add(PINEAPPLE, ananas);
        cart.add(BANANA, banana);

        return cart.getTotal();
    }

}
