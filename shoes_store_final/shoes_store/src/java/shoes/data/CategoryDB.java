package shoes.data;

import java.util.List;
import java.util.Locale.Category;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import shoes.business.CategoryShoes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author phuqu
 */
public class CategoryDB {

    public static List<CategoryShoes> selectCategories() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c from CategoryShoes c";
        TypedQuery<CategoryShoes> q = em.createQuery(qString, CategoryShoes.class);
        List<CategoryShoes> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }

        return results;
    }

    public static CategoryShoes getCategoryShoesById(int categoryId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(CategoryShoes.class, categoryId);
        } finally {
            em.close();
        }
    }
    
    public static void insertCategory(CategoryShoes category){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }
    
    public static void updateCategory(CategoryShoes category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void deleteCategory(CategoryShoes category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            category = em.merge(category); // Ensure the object is in the managed state before removing
            em.remove(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
