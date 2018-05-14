package entity;

import lombok.Data;

@Data
public class Address {

    private long Id;
    private String country;
    private String city;
    private String street;
    private String postCode;

    public Address() {
    }

    public Address(long id, String country, String city, String street, String postCode) {
        Id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

}
