package com.monolitospizza.helpers;

import com.monolitospizza.model.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;

/**
 * @author Matt Stine
 */
public class ChooseToppingsViewHelperTest {

    private Iterable<Topping> toppingOptions;
    private Pizza pizza;
    private Topping sausage;

    @Before
    public void setUp() throws Exception {
        sausage = new Topping("Sausage", BigDecimal.ZERO);

        toppingOptions = Arrays.asList(
                sausage,
                new Topping("Onion", BigDecimal.ZERO),
                new Topping("Bell Pepper", BigDecimal.ZERO)
        );
        pizza = new Pizza(1L,
                new Size("Large", BigDecimal.ZERO),
                new Crust("Deep Dish"),
                new Sauce("Normal")
        );
    }

    @Test
    public void knowsItsPizzaId() {
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getPizzaId(), is(equalTo(1L)));
    }

    @Test
    public void emptyPizzaHasAllToppingOptions() {
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getToppingOptions(),
                containsInAnyOrder(sausage,
                        new Topping("Onion", BigDecimal.ZERO),
                        new Topping("Bell Pepper", BigDecimal.ZERO)));
    }

    @Test
    public void pizzaWithOneToppingHasOneLessToppingOptions() {
        pizza.addLeftTopping(sausage);

        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getToppingOptions(),
                containsInAnyOrder(new Topping("Onion", BigDecimal.ZERO),
                        new Topping("Bell Pepper", BigDecimal.ZERO)));
    }

    @Test
    public void emptyPizzaHasNoToppingLineItems() {
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getToppingLineItems(), is(empty()));
    }

    @Test
    public void pizzaWithLeftToppingHasSameLeftToppingLineItem() {
        pizza.addLeftTopping(sausage);

        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getToppingLineItems(), containsInAnyOrder(
                new ChooseToppingsViewHelperLineItem(sausage, ChooseToppingsViewHelperLocation.LEFT)
        ));
    }

    @Test
    public void pizzaWithRightToppingHasSameRightToppingLineItem() {
        pizza.addRightTopping(sausage);

        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getToppingLineItems(), containsInAnyOrder(
                new ChooseToppingsViewHelperLineItem(sausage, ChooseToppingsViewHelperLocation.RIGHT)
        ));
    }

    @Test
    public void pizzaWithWholeToppingHasSameWholeToppingLineItem() {
        pizza.addTopping(sausage);

        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(toppingOptions, pizza);

        assertThat(helper.getToppingLineItems(), containsInAnyOrder(
                new ChooseToppingsViewHelperLineItem(sausage, ChooseToppingsViewHelperLocation.WHOLE)
        ));
    }
}
