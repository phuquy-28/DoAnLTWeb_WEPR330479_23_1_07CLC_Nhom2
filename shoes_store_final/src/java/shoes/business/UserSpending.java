/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.business;

/**
 *
 * @author phuqu
 */
public class UserSpending {
    private User user;
    private double spending;

    public UserSpending() {
    }

    public UserSpending(User user, double spending) {
        this.user = user;
        this.spending = spending;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getSpending() {
        return spending;
    }

    public void setSpending(double spending) {
        this.spending = spending;
    }
    
    
}
