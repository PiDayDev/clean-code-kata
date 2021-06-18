package it.intre.sal.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UglyCheckout implements Checkout {

    @Override
    public int pay(List<String> items, Map<String, Entry<Integer, Integer>> offers) {
        int res = 0;
        int apples = 0;
        int pears = 0;
        int pineapples = 0;
        int bananas = 0;

        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 50);
        map.put("pear", 30);
        map.put("pineapple", 220);
        map.put("banana", 60);

        for (String item : items) {
            switch (item) {
                case "apple":
                    apples++;
                    break;
                case "pear":
                    pears++;
                    break;
                case "pineapple":
                    pineapples++;
                    break;
                case "banana":
                    bananas++;
                    break;
            }
        }

        //Here I have to cycle through every offer to see if it applies
        for (Entry entry : offers.entrySet()) {
            switch (entry.getKey().toString()) {
                case "apple":
                    int a1 = (int) ((Entry) entry.getValue()).getKey();
                    if (apples >= a1) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    apples -= a1;
                    break;
                    //jb 2008-09-12: don't sell lychee anymore, but maybe in the future...
//                case "lychee":
//                    int a2 = (int) ((Entry) entry.getValue()).getKey();
//                    if (p >= a2) { res += (int) ((Entry) entry.getValue()).getValue(); }
//                    p -= a2;
//                    break;
                case "pear":
                    int a2 = (int) ((Entry) entry.getValue()).getKey();
                    if (pears >= a2) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    pears -= a2;
                    break;
                case "pineapple":
                    int a3 = (int) ((Entry) entry.getValue()).getKey();
                    if (pineapples >= a3) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    pineapples -= a3;
                    break;
                case "banana":
                    int a4 = (int) ((Entry) entry.getValue()).getKey();
                    if (bananas >= a4) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    bananas -= a4;
                    break;
            }
        }

        for (Entry entry : map.entrySet()) {
            switch (entry.getKey().toString()) {
                case "apple":
                    res += apples * (int) entry.getValue();
                    break;
                case "pear":
                    res += pears * (int) entry.getValue();
                    break;
                case "pineapple":
                    res += pineapples * (int) entry.getValue();
                    break;
                case "banana":
                    res += bananas * (int) entry.getValue();
                    break;
            }
        }

        return res;
    }
}
