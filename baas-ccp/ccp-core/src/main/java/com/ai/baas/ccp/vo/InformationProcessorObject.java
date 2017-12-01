package com.ai.baas.ccp.vo;

import java.util.ArrayList;
import java.util.List;

import com.ai.baas.ccp.topoligy.core.pojo.Account;
import com.ai.baas.ccp.topoligy.core.pojo.Customer;
import com.ai.baas.ccp.topoligy.core.pojo.User;

/**
 * 三户信息
 */
public final class InformationProcessorObject extends BaseProcessObject {
    private Customer customer = null;

    private List<User> users = new ArrayList<User>();

    private List<Account> accounts = new ArrayList<Account>();

    public Customer getCustomer() {
        return customer;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
