package com.monolitospizza.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Matt Stine
 */
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
