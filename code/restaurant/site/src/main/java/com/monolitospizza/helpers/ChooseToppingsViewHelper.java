package com.monolitospizza.helpers;

import com.monolitospizza.model.Pizza;
import com.monolitospizza.model.Topping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Matt Stine
 */
public class ChooseToppingsViewHelper {
    private List<Topping> toppingOptions = new ArrayList<>();
    private List<ChooseToppingsViewHelperLineItem> toppingLineItems = new ArrayList<>();
    private Long pizzaId;
    private Long orderId;

    public ChooseToppingsViewHelper(Iterable<Topping> toppingOptions, Pizza pizza) {
        this.pizzaId = pizza.getId();
        this.orderId = pizza.getOrder().getId();

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

    public Long getPizzaId() {
        return pizzaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChooseToppingsViewHelper that = (ChooseToppingsViewHelper) o;
        return Objects.equals(toppingOptions, that.toppingOptions) &&
                Objects.equals(toppingLineItems, that.toppingLineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toppingOptions, toppingLineItems);
    }

    public Long getOrderId() {
        return orderId;
    }
}
