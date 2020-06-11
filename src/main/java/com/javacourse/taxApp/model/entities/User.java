package com.javacourse.taxApp.model.entities;

import com.javacourse.taxApp.model.TaxGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="user")
public class User extends Account{

    @NotEmpty(message = "msg.fields-error")
    @Size(max = 255, message = "msg.fields-error")
    @Column(name="full_name")
    private String fullName;

    @Column(name="company_name")
    private String company;

    @NotEmpty(message = "msg.fields-error")
    @Pattern(regexp = "^[А-Я]{2}[0-9]{6}$",message = "msg.pass-error")
    @Column(name="passport")
    private String passport;

    @NotEmpty(message = "msg.fields-error")
    @Size(max = 255, message = "msg.fields-error")
    @Column(name="address")
    private String address;

    @NotNull(message = "msg.fields-error")
    @Column(name="tax_group")
    @Enumerated(EnumType.STRING)
    private TaxGroup group;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(() -> "USER");

    }

}

