package pl.sdacademy.java.hibernate.common.sakila;

import jakarta.persistence.*;

import javax.naming.Name;

@Entity
public class Address {
    @Id
    @Column(name = "address_id")
    private Long addressId;

    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Long getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }
}
