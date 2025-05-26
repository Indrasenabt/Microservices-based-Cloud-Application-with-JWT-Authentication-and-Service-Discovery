package com.microservice.accounts_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;
    private String details;

    // No-args constructor
    public Account() {
    }

    // All-args constructor
    public Account(Long id, String owner, String details) {
        this.id = id;
        this.owner = owner;
        this.details = details;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // toString
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
