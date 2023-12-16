/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/licenseprivate Stringdefault.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

/**
 *
 * @author admin
 */
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import java.util.List;
import shoes.util.EmailUtil;

@Entity
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceID;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LineItem> productList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ShippingInfo address;

    @OneToOne
    private PromotionCode promotion;
    private String paymentMethod;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDate;

    private double subTotal;
    private double discount;
    private double total;

    public Invoice() {
    }

    public Invoice(User user, List<LineItem> productList, ShippingInfo address, PromotionCode promotion, String paymentMethod, Date invoiceDate, double subTotal, double discount, double total) {
        this.user = user;
        this.productList = productList;
        this.address = address;
        this.promotion = promotion;
        this.paymentMethod = paymentMethod;
        this.invoiceDate = invoiceDate;
        this.subTotal = subTotal;
        this.discount = discount;
        this.total = total;
    }

    public Invoice(Long invoiceID, User user, List<LineItem> productList, ShippingInfo address, PromotionCode promotion, String paymentMethod, Date invoiceDate, double subTotal, double discount, double total) {
        this.invoiceID = invoiceID;
        this.user = user;
        this.productList = productList;
        this.address = address;
        this.promotion = promotion;
        this.paymentMethod = paymentMethod;
        this.invoiceDate = invoiceDate;
        this.subTotal = subTotal;
        this.discount = discount;
        this.total = total;
    }

//    public double getInvoiceTotal(){
//        return 0;
//    }
//    
//    public String getInvoiceTotalCurrencyFormat(){
//        return 0;
//    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getInvoiceID() {
        return invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getProductList() {
        return productList;
    }

    public void setProductList(List<LineItem> productList) {
        this.productList = productList;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingInfo getAddress() {
        return address;
    }

    public void setAddress(ShippingInfo address) {
        this.address = address;
    }

    public PromotionCode getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionCode promotion) {
        this.promotion = promotion;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static void sendEmailForCustomer(Invoice invoice) {
        String to = invoice.getUser().getEmail();
        String tieude = "Hóa đơn mua giày của bạn tại StockAndStock";

        String customerName = "Tên người mua hàng: " + invoice.getAddress().getFirstName() + " " + invoice.getAddress().getLastName();
        String customerAddress = "Địa chỉ người mua hàng: " + invoice.getAddress().toString();
        String customerPhone = "Số điện thoại người mua hàng: " + invoice.getAddress().getPhoneNumber();
        String listProduct = "Danh sách sản phẩm:<br>";
        for (LineItem product : invoice.productList) {
            listProduct += "- " + product.getProduct().getProductName() + " Size: " 
                    + product.getShoeSize() + " Quantity: " + product.getQuantity() + " Total: $" 
                    + product.getAmount()+ "<br>"; // products là danh sách các sản phẩm người dùng mua
        }
        
        String totalPrice = "Tổng tiền: $" + invoice.getTotal();
        
        String orderDetails = customerName + "<br>" + customerAddress + "<br>" + customerPhone + "<br>" + listProduct + "<br>" + totalPrice;
        
        EmailUtil.sendEmail(to, tieude, orderDetails);
    }
}
