package shoes.business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shipInfo")
public class ShippingInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipInfoID;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String country;
    private String zipcode;

    public ShippingInfo() {
    }

    public Long getShipInfoID() {
        return shipInfoID;
    }

    public void setShipInfoID(Long shipInfoID) {
        this.shipInfoID = shipInfoID;
    }

    public ShippingInfo(String firstName, String lastName, String phoneNumber, String address, String country, String zipcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.zipcode = zipcode;
    }

    public ShippingInfo(Long shipInfoID, String firstName, String lastName, String phoneNumber, String address, String country, String zipcode) {
        this.shipInfoID = shipInfoID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.zipcode = zipcode;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return  address + ", "
                + country + ", "
                + zipcode;
    }

}
