/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author phuqu
 */
@Entity
@Table (name = "image")
public class sImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageID;
    private String imageURL;

    public sImage(){
        
    }
    
    public sImage(String imageURL) {
        this.imageURL = imageURL;
    }

    public sImage(Long imageID, String imageURL) {
        this.imageID = imageID;
        this.imageURL = imageURL;
    }

    public Long getImageID() {
        return imageID;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }
    
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
