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
import javax.persistence.Table;


@Entity
@Table(name = "category")
public class CategoryShoes implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int categoryID;
    private String categoryName;

    public CategoryShoes() {
    }

    public CategoryShoes(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryShoes(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
