/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author phuqu
 */
@Entity
public class ShoeSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sizeId;

    private double sizeValue;

    public ShoeSize() {
    }

    
    public ShoeSize(int sizeId, double sizeValue) {
        this.sizeId = sizeId;
        this.sizeValue = sizeValue;
    }

    public ShoeSize(double sizeValue) {
        this.sizeValue = sizeValue;
    }
    
    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public double getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(double sizeValue) {
        this.sizeValue = sizeValue;
    }
    
    
}
