package it.intre.sal.kata;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static it.intre.sal.kata.Items.*;

final class Items {
     public static final String APPLE = "apple";
     public static final String PEAR = "pear";
     public static final String PINEAPPLE = "pineapple";
     public static final String BANANA = "banana";
 }

public class Cart {
    private final Map<String, Entry<Integer, Integer>> offers;
    private final Map<String, Integer> itemPrices;

    Map<String, Integer> counters = new HashMap<>();

    private int total;

    public Cart(Map<String, Entry<Integer, Integer>> offers, Map<String, Integer> map) {
        this.offers = offers;
        itemPrices = map;
    }

    public void add(String item, int quantity) {
        counters.put(item, quantity);
    }

    int getTotal() {
        total = computeTotal();
        return total;
    }

    private int computeTotal() {
        Map<String, Integer> rest = new HashMap<>(counters);
        int promoTotal = getPromoTotal(rest);
        int standardTotal = getStandardTotal(rest);
        return promoTotal + standardTotal;
    }

    private int getPromoTotal(Map<String, Integer> rest) {
        int promoTotal = 0;
        for (Entry<String, Entry<Integer, Integer>> itemWithPromo : offers.entrySet()) {
            promoTotal += computeOnePromo(rest, itemWithPromo);
        }
        return promoTotal;
    }

    private int computeOnePromo(Map<String, Integer> rest, Entry<String, Entry<Integer, Integer>> itemWithPromo) {
        String item = itemWithPromo.getKey();
        Entry<Integer, Integer> promo = itemWithPromo.getValue();
        Integer promoQuantity = promo.getKey();
        Integer promoPrice = promo.getValue();
        int promoTotal = 0;
        Integer qty = rest.get(item);
        while (qty >= promoQuantity) {
            promoTotal += promoPrice;
            qty -= promoQuantity;
        }
        rest.put(item, qty);
        return promoTotal;
    }

    private int getStandardTotal(Map<String, Integer> rest) {
        int tot = 0;
        for (Entry<String, Integer> entry : itemPrices.entrySet()) {
            String item = entry.getKey();
            Integer price = entry.getValue();
            tot += computeStandardTotal(rest.get(item), price);
        }
        return tot;
    }

    private int computeStandardTotal(int quantity, Integer unitPrice) {
        return quantity * unitPrice;
    }
}
