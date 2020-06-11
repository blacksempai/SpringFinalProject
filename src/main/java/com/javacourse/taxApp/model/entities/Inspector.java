package com.javacourse.taxApp.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="inspector")
public class Inspector extends Account {
    @NotEmpty(message = "msg.fields-error")
    @Size(min = 2, max = 255, message = "msg.fields-error")
    @Column(name="full_name")
    private String fullName;

    @Column(name="complaint_number")
    private Integer complaintNumber;

    @Column(name="reports_in_service")
    private Integer reportsInService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(()->"INSPECTOR");
    }

}
