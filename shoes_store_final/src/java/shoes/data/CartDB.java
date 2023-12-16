/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.data;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import shoes.business.Cart;
import shoes.business.LineItem;

/**
 *
 * @author phuqu
 */
public class CartDB {

    public static void insertCart(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void updateCart(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteCart(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            cart = em.merge(cart); // Ensure the object is in the managed state before removing
            em.remove(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteCartById(Long cartID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {
            // Find the Shoes object by ID
            Cart cart = em.find(Cart.class, cartID);

            if (cart != null) {
                em.remove(cart); // Remove the found Shoes object
            }

            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static Cart selectCartById(Long cartID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT c FROM Cart c WHERE c.cartID = :cartID";
            TypedQuery<Cart> query = em.createQuery(qString, Cart.class);
            query.setParameter("cartID", cartID);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
}
