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

    Map<String, Integer> counters = new HashMap<>();

    private int total;

    public Cart(Map<String, Map.Entry<Integer, Integer>> offers, Map<String, Integer> map) {
        this.offers = offers;
        itemPrices = map;
    }

    int getTotal() {
        total = computeTotal();
        return total;
    }

    private int computeTotal() {
        int res = 0;


        Map<String, Integer> rest = new HashMap<>(counters);
        //Here I have to cycle through every offer to see if it applies
        for (Map.Entry<String, Map.Entry<Integer, Integer>> entry : offers.entrySet()) {
            String item = entry.getKey();
            Map.Entry<Integer, Integer> promo = entry.getValue();
            Integer promoQuantity = promo.getKey();
            Integer promoPrice = promo.getValue();

            switch (item) {
                case APPLE:
                    int q = rest.get(item);
                    if (q >= promoQuantity) {
                        res += promoPrice;
                    }
                    rest.put(item,rest.get(item) - promoQuantity);
                    break;
                case PEAR:
                    int q2 = rest.get(item);
                    if (q2 >= promoQuantity) {
                        res += promoPrice;
                    }
                    rest.put(item,rest.get(item) - promoQuantity);

                    break;
                case PINEAPPLE:
                    int q3 = rest.get(item);
                    if (q3 >= promoQuantity) {
                        res += promoPrice;
                    }
                    rest.put(item,rest.get(item) - promoQuantity);

                    break;
                case BANANA:
                    int q4 = rest.get(item);
                    if (q4 >= promoQuantity) {
                        res += promoPrice;
                    }
                    rest.put(item,rest.get(item) - promoQuantity);

                    break;
            }
        }


        int apple = rest.get(APPLE);
        int pear = rest.get(PEAR);
        int ananas = rest.get(PINEAPPLE);
        int banana = rest.get(BANANA);
        for (Map.Entry<String, Integer> entry : itemPrices.entrySet()) {
            String item = entry.getKey();
            Integer price = entry.getValue();
            switch (item) {
                case APPLE:
                    res += computeStandardTotal(apple, price);
                    break;
                case PEAR:
                    res += computeStandardTotal(pear, price);
                    break;
                case PINEAPPLE:
                    res += computeStandardTotal(ananas, price);
                    break;
                case BANANA:
                    res += computeStandardTotal(banana, price);
                    break;
            }
        }
        return res;
    }

    private int computeStandardTotal(int quantity, Integer unitPrice) {
        return quantity * unitPrice;
    }

    public void add(String item, int quantity) {
        counters.put(item, quantity);
    }
}
