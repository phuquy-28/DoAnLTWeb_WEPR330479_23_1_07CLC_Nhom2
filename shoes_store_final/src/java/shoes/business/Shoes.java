/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/licenseprivate Stringdefault.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shoes")
public class Shoes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    private String productName;

    @ManyToOne
    //@JoinColumn(name = "category_id")
    private CategoryShoes category;

//    private double productSize;
    @OneToMany(fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<ShoeSize> sizes;
    private String productColor;
    private String productDecription;
    private Long productPrice;

    @OneToMany(fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<sImage> images;

    public Shoes() {

    }

//    public Shoes(String productName, CategoryShoes category, double productSize, String productColor, String productDecription, Long productPrice, List<sImage> images) {
//        this.productName = productName;
//        this.category = category;
//        this.productSize = productSize;
//        this.productColor = productColor;
//        this.productDecription = productDecription;
//        this.productPrice = productPrice;
//        this.images = images;
//    }
//
//    public Shoes(int productID, String productName, CategoryShoes category, double productSize, String productColor, String productDecription, Long productPrice, List<sImage> images) {
//        this.productID = productID;
//        this.productName = productName;
//        this.category = category;
//        this.productSize = productSize;
//        this.productColor = productColor;
//        this.productDecription = productDecription;
//        this.productPrice = productPrice;
//        this.images = images;
//    }
    public Shoes(int productID, String productName, CategoryShoes category, List<ShoeSize> sizes, String productColor, String productDecription, Long productPrice, List<sImage> images) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.sizes = sizes;
        this.productColor = productColor;
        this.productDecription = productDecription;
        this.productPrice = productPrice;
        this.images = images;
    }

    public Shoes(String productName, CategoryShoes category, List<ShoeSize> sizes, String productColor, String productDecription, Long productPrice, List<sImage> images) {
        this.productName = productName;
        this.category = category;
        this.sizes = sizes;
        this.productColor = productColor;
        this.productDecription = productDecription;
        this.productPrice = productPrice;
        this.images = images;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

//    public double getProductSize() {
//        return productSize;
//    }
//
//    public void setProductSize(double productSize) {
//        this.productSize = productSize;
//    }
    public List<ShoeSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ShoeSize> sizes) {
        this.sizes = sizes;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductDecription() {
        return productDecription;
    }

    public void setProductDecription(String productDecription) {
        this.productDecription = productDecription;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public CategoryShoes getCategory() {
        return category;
    }

    public void setCategory(CategoryShoes category) {
        this.category = category;
    }

    public List<sImage> getImages() {
        return images;
    }

    public void setImages(List<sImage> images) {
        this.images = images;
    }

    public String getProductSizeString() {
        if (sizes != null && !sizes.isEmpty()) {
            // Sort the sizes in ascending order
            sizes.sort(Comparator.comparingDouble(ShoeSize::getSizeValue));

            StringBuilder sizeString = new StringBuilder();
            for (ShoeSize size : sizes) {
                sizeString.append(size.getSizeValue()).append(" / ");
            }
            // Remove the trailing slash and space if present
            if (sizeString.length() > 2) {
                sizeString.delete(sizeString.length() - 2, sizeString.length());
            }
            return sizeString.toString();
        }
        return "";
    }
}
