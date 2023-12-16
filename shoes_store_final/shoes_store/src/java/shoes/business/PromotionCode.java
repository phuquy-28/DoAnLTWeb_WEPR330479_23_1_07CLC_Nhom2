/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;

import javax.persistence.Temporal;

@Entity
public class PromotionCode implements Serializable {

    @Id
    private String promotionCode;
    private double promotionAmount;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    public PromotionCode() {
    }

    public PromotionCode(String promotionCode, double promotionAmount, Date startDate, Date endDate) {
        this.promotionCode = promotionCode;
        this.promotionAmount = promotionAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public double getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(double promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
