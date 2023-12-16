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

@Entity
public class Cart implements Serializable{
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long cartID;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
}
