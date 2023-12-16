package shoes.business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressID;

    private String appartment;
    private String street;
    private String district;
    private String city;
    private String country;
    private String zipcode;

    public AddressInfo() {
    }

    public AddressInfo(String appartment, String street, String district, String city, String country, String zipcode) {
        this.appartment = appartment;
        this.street = street;
        this.district = district;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public AddressInfo(Long addressID, String appartment, String street, String district, String city, String country, String zipcode) {
        this.addressID = addressID;
        this.appartment = appartment;
        this.street = street;
        this.district = district;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    @Override
    public String toString() {
        return appartment + ", "
                + street + ", "
                + district + ", "
                + city + ", "
                + country + ", "
                + zipcode;
    }

}
