package entity;

import java.util.Objects;

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

    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(Id, address.Id) &&
                Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(postCode, address.postCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id, country, city, street, postCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "Id=" + Id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
