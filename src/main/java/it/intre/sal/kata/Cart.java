package it.intre.sal.kata;

import java.util.HashMap;
import java.util.Map;

import static it.intre.sal.kata.Items.*;

final class Items {
     public static final String APPLE = "apple";
     public static final String PEAR = "pear";
     public static final String PINEAPPLE = "pineapple";
     public static final String BANANA = "banana";
 }

public class Cart {
    private final Map<String, Map.Entry<Integer, Integer>> offers;
    private final Map<String, Integer> itemPrices;

    private int total = 0;

    Map<String, Integer> counters = new HashMap<>();

    public Cart(Map<String, Map.Entry<Integer, Integer>> offers, Map<String, Integer> map) {
        this.offers = offers;
        itemPrices = map;
    }

    int getTotal() {
        int res = 0;
        int apple = counters.get(APPLE);
        int pear = counters.get(PEAR);
        int ananas = counters.get(PINEAPPLE);
        int banana = counters.get(BANANA);
        //Here I have to cycle through every offer to see if it applies
        for (Map.Entry entry : offers.entrySet()) {
            switch (entry.getKey().toString()) {
                case APPLE:
                    int a1 = (int) ((Map.Entry) entry.getValue()).getKey();
                    if (apple >= a1) {
                        res += (int) ((Map.Entry) entry.getValue()).getValue();
                    }
                    apple -= a1;
                    break;
                //jb 2008-09-12: don't sell lychee anymore, but maybe in the future...
//                case "lychee":
//                    int a2 = (int) ((Entry) entry.getValue()).getKey();
//                    if (p >= a2) { res += (int) ((Entry) entry.getValue()).getValue(); }
//                    p -= a2;
//                    break;
                case PEAR:
                    int a2 = (int) ((Map.Entry) entry.getValue()).getKey();
                    if (pear >= a2) {
                        res += (int) ((Map.Entry) entry.getValue()).getValue();
                    }
                    pear -= a2;
                    break;
                case PINEAPPLE:
                    int a3 = (int) ((Map.Entry) entry.getValue()).getKey();
                    if (ananas >= a3) {
                        res += (int) ((Map.Entry) entry.getValue()).getValue();
                    }
                    ananas -= a3;
                    break;
                case BANANA:
                    int a4 = (int) ((Map.Entry) entry.getValue()).getKey();
                    if (banana >= a4) {
                        res += (int) ((Map.Entry) entry.getValue()).getValue();
                    }
                    banana -= a4;
                    break;
            }
        }

        for (Map.Entry entry : itemPrices.entrySet()) {
            switch (entry.getKey().toString()) {
                case APPLE:
                    res += apple * (int) entry.getValue();
                    break;
                case PEAR:
                    res += pear * (int) entry.getValue();
                    break;
                case PINEAPPLE:
                    res += ananas * (int) entry.getValue();
                    break;
                case BANANA:
                    res += banana * (int) entry.getValue();
                    break;
            }
        }
        return res;
    }

    public void add(String item, int quantity) {
        counters.put(item, quantity);
    }
}
