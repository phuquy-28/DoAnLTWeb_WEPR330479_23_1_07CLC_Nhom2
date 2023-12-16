package shoes.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import shoes.business.PromotionCode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phuqu
 */
public class PromotionDB {
    public static List<PromotionCode> selectPromotions() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p from PromotionCode p";
        TypedQuery<PromotionCode> q = em.createQuery(qString, PromotionCode.class);
        List<PromotionCode> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }
        return results;
    }
    
    public static void insert(PromotionCode promotion) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(promotion);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }
    
    public static void update(PromotionCode promotion) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(promotion);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }
    
    public static PromotionCode selectPromotionByCode(String promotionCode) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            // Tìm kiếm Promotion dựa trên ID
            PromotionCode promotion = em.find(PromotionCode.class, promotionCode);
            return promotion;
        } finally {
            em.close();
        }
    }
    
    public static void deletePromotionByCode(String promotionCode) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {
            // Find the Promotion object by ID
            PromotionCode promotion = em.find(PromotionCode.class, promotionCode);

            if (promotion != null) {
                em.remove(promotion); // Remove the found Promotion object
            }
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
