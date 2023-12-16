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
public class LineItemDB {

    public static void insertLineItem(LineItem item) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(item);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void updateLineItem(LineItem item) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(item);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteLineItem(LineItem item) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            item = em.merge(item); // Ensure the object is in the managed state before removing
            em.remove(item);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static LineItem selectLineItemById(Long itemID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT l FROM LineItem l WHERE l.lineItemID = :itemID";
            TypedQuery<LineItem> query = em.createQuery(qString, LineItem.class);
            query.setParameter("itemID", itemID);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
