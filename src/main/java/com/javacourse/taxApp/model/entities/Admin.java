package com.javacourse.taxApp.model.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;


@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="admin")
public class Admin extends Account{
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(()->"ADMIN");
    }
}
