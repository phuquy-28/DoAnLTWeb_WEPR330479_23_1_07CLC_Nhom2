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
import shoes.business.CategoryShoes;
import shoes.business.Shoes;

/**
 *
 * @author phuqu
 */
public class ShoesDB {

    public static void insertShoes(Shoes shoes) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(shoes);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void updateShoes(Shoes shoes) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(shoes);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteShoes(Shoes shoes) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            shoes = em.merge(shoes); // Ensure the object is in the managed state before removing
            em.remove(shoes);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> selectShoes() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s from Shoes s";
        TypedQuery<Shoes> q = em.createQuery(qString, Shoes.class);
        List<Shoes> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }

        return results;
    }

    public static List<Shoes> selectShoesByCategory(CategoryShoes category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            String qString = "SELECT s FROM Shoes s WHERE s.category = :category";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("category", category);
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public static void deleteShoesById(int shoesId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {
            // Find the Shoes object by ID
            Shoes shoes = em.find(Shoes.class, shoesId);

            if (shoes != null) {
                em.remove(shoes); // Remove the found Shoes object
            }

            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static Shoes selectShoesById(int shoesId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT s FROM Shoes s WHERE s.productID = :shoesId";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("shoesId", shoesId);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Shoes> getShoesByPageCategory(CategoryShoes category, int pageNumber, int pageSize) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        int offset = (pageNumber - 1) * pageSize;
        String qString = "SELECT s FROM Shoes s WHERE s.category = :category ORDER BY s.productID";
        TypedQuery<Shoes> q = em.createQuery(qString, Shoes.class);
        q.setFirstResult(offset);
        q.setMaxResults(pageSize);
        q.setParameter("category", category);
        List<Shoes> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }
        return results;
    }

//    public static List<Shoes> getShoesByPageSize(double size, int pageNumber, int pageSize) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        int offset = (pageNumber - 1) * pageSize;
//        String qString = "SELECT s FROM Shoes s WHERE s.productSize = :size ORDER BY s.productID";
//        TypedQuery<Shoes> q = em.createQuery(qString, Shoes.class);
//        q.setFirstResult(offset);
//        q.setMaxResults(pageSize);
//        q.setParameter("size", size);
//        List<Shoes> results = null;
//        try {
//            results = q.getResultList();
//        } catch (NoResultException ex) {
//            return null; //
//        } finally {
//            em.close();
//        }
//        return results;
//    }
    public static List<Shoes> getShoesByPageSize(double inputSize, int pageNumber, int pageSize) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            int offset = (pageNumber - 1) * pageSize;

            String qString = "SELECT DISTINCT s FROM Shoes s JOIN s.sizes sz WHERE sz.sizeValue = :inputSize ORDER BY s.productID";

            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("inputSize", inputSize);
            query.setFirstResult(offset);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> getShoesByPage(int pageNumber, int pageSize) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        int offset = (pageNumber - 1) * pageSize;
        String qString = "SELECT s FROM Shoes s ORDER BY s.productID";
        TypedQuery<Shoes> q = em.createQuery(qString, Shoes.class);
        q.setFirstResult(offset);
        q.setMaxResults(pageSize);
        List<Shoes> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }
        return results;
    }

    public static long getProductCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT COUNT(s) FROM Shoes s";
            TypedQuery<Long> q = em.createQuery(qString, Long.class);
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> searchShoesByName(String shoeName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT s FROM Shoes s WHERE LOWER(s.productName) LIKE :shoeName";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("shoeName", "%" + shoeName.toLowerCase() + "%");

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> getShoesBySize(double inputSize) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT DISTINCT s FROM Shoes s JOIN s.sizes sz WHERE sz.sizeValue = :inputSize";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("inputSize", inputSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> getTopProducts(int top) {
        if (top <= 0) {
            // Xử lý trường hợp top không hợp lệ (ví dụ: âm hoặc bằng 0)
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT s FROM Shoes s ORDER BY s.productID DESC";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setMaxResults(top); // Số lượng kết quả trả về là top

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> getTopNewProductNext(int top, int offset) {
        if (top <= 0 || offset < 0) {
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT s FROM Shoes s ORDER BY s.productID DESC";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setFirstResult(offset); // Đặt vị trí bắt đầu từ offset
            query.setMaxResults(top);     // Số lượng kết quả trả về là top

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // New method to get the top n best-selling shoes
    public static List<Shoes> getTopBestSellingShoes(int top) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT li.product, SUM(li.quantity) AS totalSold "
                + "FROM Invoice i "
                + "JOIN i.productList li "
                + "GROUP BY li.product "
                + "ORDER BY totalSold DESC";
        TypedQuery<Object[]> q = em.createQuery(qString, Object[].class).setMaxResults(top);
        List<Object[]> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        List<Shoes> topShoes = new ArrayList<>();
        for (Object[] row : results) {
            Shoes shoes = (Shoes) row[0];
            topShoes.add(shoes);
        }

        return topShoes;
    }

    public static List<Shoes> getTopBestSellingShoesNext(int top, int offset) {
        if (top <= 0 || offset < 0) {
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT li.product, SUM(li.quantity) AS totalSold "
                + "FROM Invoice i "
                + "JOIN i.productList li "
                + "GROUP BY li.product "
                + "ORDER BY totalSold DESC";
        TypedQuery<Object[]> q = em.createQuery(qString, Object[].class);
        q.setFirstResult(offset); // Đặt vị trí bắt đầu từ offset
        q.setMaxResults(top);     // Số lượng kết quả trả về là top
        List<Object[]> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        List<Shoes> topShoes = new ArrayList<>();
        for (Object[] row : results) {
            Shoes shoes = (Shoes) row[0];
            topShoes.add(shoes);
        }

        return topShoes;
    }

    public static List<Shoes> getTopNewBrand(int top, int categoryID) {
        if (top <= 0) {
            // Xử lý trường hợp top không hợp lệ (ví dụ: âm hoặc bằng 0)
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT s FROM Shoes s WHERE s.category.categoryID = :categoryID ORDER BY s.productID DESC";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("categoryID", categoryID);
            query.setMaxResults(top); // Số lượng kết quả trả về là top

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Shoes> getTopNewBrandNext(int top, int offset, int categoryID) {
        if (top <= 0) {
            // Xử lý trường hợp top không hợp lệ (ví dụ: âm hoặc bằng 0)
            return null;
        }

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT s FROM Shoes s WHERE s.category.categoryID = :categoryID ORDER BY s.productID DESC";
            TypedQuery<Shoes> query = em.createQuery(qString, Shoes.class);
            query.setParameter("categoryID", categoryID);
            query.setFirstResult(offset); // Đặt vị trí bắt đầu từ offset
            query.setMaxResults(top);     // Số lượng kết quả trả về là top

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        // Thực hiện tìm kiếm giày theo tên

        List<Shoes> searchedShoes = getTopNewBrandNext(6, 11, 51);

        if (searchedShoes != null && !searchedShoes.isEmpty()) {
            System.out.println("Search results for shoes with name '");
            for (Shoes shoes : searchedShoes) {
                System.out.println("Product ID: " + shoes.getProductID());
                System.out.println("Product Name: " + shoes.getProductName());
                // Thêm các trường khác nếu cần
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("No results found for shoes with name '");
        }
    }
}
