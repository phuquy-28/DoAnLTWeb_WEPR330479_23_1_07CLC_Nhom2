/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/licenseprivate Stringdefault.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import shoes.data.CartDB;

@Entity
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartID;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<LineItem> productList;

    public Cart() {
    }

    public Cart(List<LineItem> productList) {
        this.productList = productList;
    }

    public Cart(Long cartID, List<LineItem> productList) {
        this.cartID = cartID;
        this.productList = productList;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public List<LineItem> getProductList() {
        return productList;
    }

    public void setProductList(List<LineItem> productList) {
        this.productList = productList;
    }

    public double totalCart() {
        double total = 0;
        for (int i = 0; i < productList.size(); i++) {
            LineItem lineItem = productList.get(i);
            total = total + lineItem.getAmount();
        }
        return total;
    }

    public void addItem(LineItem item) {
        int id = item.getProduct().getProductID();
        int quantity;
        double size = item.getShoeSize();
        for (int i = 0; i < productList.size(); i++) {
            LineItem lineItem = productList.get(i);
            if (lineItem.getProduct().getProductID() == id && lineItem.getShoeSize() == size) {
                quantity = lineItem.getQuantity();
                lineItem.setQuantity(quantity + 1);
                return;
            }
        }
        productList.add(item);
    }

    public void updateItem(LineItem item, String change) {
        int id = item.getProduct().getProductID();
        int quantity = item.getQuantity();
        double size = item.getShoeSize();
        for (int i = 0; i < productList.size(); i++) {
            LineItem lineItem = productList.get(i);
            if (lineItem.getProduct().getProductID() == id && lineItem.getShoeSize() == size) {
                if (change.equals("inc")) {
                    lineItem.setQuantity(quantity + 1);
                    return;
                } else {
                    if (quantity - 1 == 0) {
                        removeItem(item);
                        return;
                    }
                    lineItem.setQuantity(quantity - 1);
                    return;
                }
            }
        }
    }

    public void removeItem(LineItem item) {
        int id = item.getProduct().getProductID();
        double size = item.getShoeSize();
        for (int i = 0; i < productList.size(); i++) {
            LineItem lineItem = productList.get(i);
            if (lineItem.getProduct().getProductID() == id && lineItem.getShoeSize() == size) {
                productList.remove(i);
                return;
            }
        }
    }
}
