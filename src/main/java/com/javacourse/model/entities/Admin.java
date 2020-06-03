package com.javacourse.model.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(name="account_id")
public class Admin extends Account{
    //The most useful class

    public Admin() {
    }
}
