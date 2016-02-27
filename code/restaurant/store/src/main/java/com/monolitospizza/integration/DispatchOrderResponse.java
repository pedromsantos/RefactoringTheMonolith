package com.monolitospizza.integration;

import java.util.Objects;

/**
 * @author Matt Stine
 */
public class DispatchOrderResponse {
    protected String errorMessage;

    public DispatchOrderResponse() {
    }

    public DispatchOrderResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "[" +
                "errorMessage='" + errorMessage + '\'' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DispatchOrderResponse that = (DispatchOrderResponse) o;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage);
    }
}
