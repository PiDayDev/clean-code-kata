package it.intre.sal.kata;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int total = 0;

    Map<String, Integer> counters = new HashMap<>();

    int computeTotal(Map<String, Map.Entry<Integer, Integer>> offers,
                     Map<String, Integer> itemPrices) {
        int res = 0;
        int apple = counters.get("apple");
        int pear = counters.get("pear");
        int ananas = counters.get("pineapple");
        int banana = counters.get("banana");
        //Here I have to cycle through every offer to see if it applies
        for (Map.Entry entry : offers.entrySet()) {
            switch (entry.getKey().toString()) {
                case "apple":
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
                case "pear":
                    int a2 = (int) ((Map.Entry) entry.getValue()).getKey();
                    if (pear >= a2) {
                        res += (int) ((Map.Entry) entry.getValue()).getValue();
                    }
                    pear -= a2;
                    break;
                case "pineapple":
                    int a3 = (int) ((Map.Entry) entry.getValue()).getKey();
                    if (ananas >= a3) {
                        res += (int) ((Map.Entry) entry.getValue()).getValue();
                    }
                    ananas -= a3;
                    break;
                case "banana":
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
                case "apple":
                    res += apple * (int) entry.getValue();
                    break;
                case "pear":
                    res += pear * (int) entry.getValue();
                    break;
                case "pineapple":
                    res += ananas * (int) entry.getValue();
                    break;
                case "banana":
                    res += banana * (int) entry.getValue();
                    break;
            }
        }
        return res;
    }
    public int getTotal() {
        return total;
    }


    public void add(String item, int quantity) {
        counters.put(item, quantity);
    }
}
