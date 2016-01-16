package com.monolitospizza.helpers;

import com.monolitospizza.model.Topping;

import java.util.Objects;

/**
 * @author Matt Stine
 */
public class ChooseToppingsViewHelperLineItem {
    private final Topping topping;
    private final ChooseToppingsViewHelperLocation location;

    public ChooseToppingsViewHelperLineItem(Topping topping, ChooseToppingsViewHelperLocation location) {
        this.topping = topping;
        this.location = location;
    }

    public Topping getTopping() {
        return topping;
    }

    public ChooseToppingsViewHelperLocation getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChooseToppingsViewHelperLineItem that = (ChooseToppingsViewHelperLineItem) o;
        return Objects.equals(topping, that.topping) &&
                location == that.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topping, location);
    }
}
