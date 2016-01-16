package com.monolitospizza.helpers;

import com.monolitospizza.model.Pizza;
import com.monolitospizza.model.Topping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Matt Stine
 */
public class ChooseToppingsViewHelper {
    private List<Topping> toppingOptions = new ArrayList<>();
    private List<ChooseToppingsViewHelperLineItem> toppingLineItems = new ArrayList<>();

    public ChooseToppingsViewHelper(Iterable<Topping> toppingOptions, Pizza pizza) {
        toppingOptions.forEach(topping -> {
            if (!pizza.containsTopping(topping)) {
                this.toppingOptions.add(topping);
                return;
            }
            if (pizza.wholePizzaContainsTopping(topping)) {
                this.toppingLineItems.add(
                        new ChooseToppingsViewHelperLineItem(topping, ChooseToppingsViewHelperLocation.WHOLE)
                );
                return;
            }
            if (pizza.leftHalfContainsTopping(topping))
                this.toppingLineItems.add(
                        new ChooseToppingsViewHelperLineItem(topping, ChooseToppingsViewHelperLocation.LEFT)
                );
            if (pizza.rightHalfContainsTopping(topping))
                this.toppingLineItems.add(
                        new ChooseToppingsViewHelperLineItem(topping, ChooseToppingsViewHelperLocation.RIGHT)
                );
        });


    }

    public List<Topping> getToppingOptions() {
        return this.toppingOptions;
    }

    public List<ChooseToppingsViewHelperLineItem> getToppingLineItems() {
        return toppingLineItems;
    }
}
