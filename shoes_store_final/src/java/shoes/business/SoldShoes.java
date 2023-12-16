/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

/**
 *
 * @author phuqu
 */
public class SoldShoes {
    private Shoes shoes;
    private long count;

    public SoldShoes() {
    }

    public SoldShoes(Shoes shoes, long count) {
        this.shoes = shoes;
        this.count = count;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
    
    
}
