package it.intre.sal.kata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class CartProduct {
    public final String item;
    public final int unitPrice;
    public final int quantity;

    public CartProduct(String item, int unitPrice, int quantity) {
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}

public class UglyCheckout implements Checkout {

    @Override
    public int pay(List<String> items, Map<String, Entry<Integer, Integer>> offers) {
        int res = 0;

        Map<String, Integer> itemCounts = new HashMap<>();
        for (String item : items) {
            itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 50);
        map.put("pear", 30);
        map.put("pineapple", 220);
        map.put("banana", 60);

        List<CartProduct> cart = new ArrayList<>();
        for (String item : itemCounts.keySet()) {
            CartProduct product = new CartProduct(item, map.getOrDefault(item, 0), itemCounts.getOrDefault(item, 0));
            cart.add(product);
        }


        //Here I have to cycle through every offer to see if it applies
        for (Entry entry : offers.entrySet()) {
            String item = entry.getKey().toString();
            res = getRes(res, itemCounts, entry, item);
        }

        int apples = itemCounts.getOrDefault("apple", 0);
        int pears = itemCounts.getOrDefault("pear", 0);
        int pineapples = itemCounts.getOrDefault("pineapple", 0);
        int bananas = itemCounts.getOrDefault("banana", 0);

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

    private int getRes(int partial, Map<String, Integer> itemCounts, Entry specialOffer, String item) {
        int a1 = (int) ((Entry) specialOffer.getValue()).getKey();
        if (itemCounts.getOrDefault(item, 0) >= a1) {
            partial += (int) ((Entry) specialOffer.getValue()).getValue();
        }
        itemCounts.put(item, itemCounts.getOrDefault(item, 0) - a1);
        return partial;
    }
}
