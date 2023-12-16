/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.data;

/**
 *
 * @author phuqu
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import shoes.business.Shoes;

import shoes.business.User;
import shoes.business.UserSpending;

public class UserDB {

    public static void insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static User selectUser(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u "
                + "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        User result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return result;
    }

    public static User selectAccount(String email, String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u "
                + "WHERE u.email = :email AND u.password = :password";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        q.setParameter("password", password);
        User result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return result;
    }

    public static boolean emailExists(String email) {
        User u = selectUser(email);
        return u != null;
    }

    public static boolean accountExists(String email, String password) {
        User u = selectAccount(email, password);
        return u != null;
    }

    public static List<User> selectUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from User u";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        List<User> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }

        return results;
    }

    public static void deleteUserById(long userID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {
            // Find the User object by ID
            User user = em.find(User.class, userID);

            if (user != null) {
                em.remove(user); // Remove the found User object
            }
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static User selectUserById(long userID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            // Tìm kiếm User dựa trên ID
            User user = em.find(User.class, userID);
            return user;
        } finally {
            em.close();
        }
    }

    public static List<User> getUserByPage(int pageNumber, int pageSize) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        int offset = (pageNumber - 1) * pageSize;
        String qString = "SELECT u FROM User u ORDER BY u.userId";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setFirstResult(offset);
        q.setMaxResults(pageSize);
        List<User> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }
        return results;
    }

    public static long getUserCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT COUNT(u) FROM User u";
            TypedQuery<Long> q = em.createQuery(qString, Long.class);
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static List<User> searchUserByEmail(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT u FROM User u WHERE LOWER(u.email) LIKE LOWER(:email)";
            TypedQuery<User> query = em.createQuery(qString, User.class);
            query.setParameter("email", "%" + email.toLowerCase() + "%");

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<UserSpending> getUsersSpending() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT NEW shoes.business.UserSpending(i.user, SUM(i.total)) "
                + "FROM Invoice i "
                + "GROUP BY i.user "
                + "ORDER BY SUM(i.total) DESC";

        TypedQuery<UserSpending> query = em.createQuery(qString, UserSpending.class);
        List<UserSpending> results = query.getResultList();
        em.close();

        return results;
    }

    public static List<UserSpending> getUserSpendingCountByInterval(String startDate, String endDate) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT NEW shoes.business.UserSpending(i.user, SUM(i.total)) "
                + "FROM Invoice i "
                + "WHERE i.invoiceDate >= :startDate AND i.invoiceDate <= :endDate "
                + "GROUP BY i.user "
                + "ORDER BY SUM(i.total) DESC";
        TypedQuery<UserSpending> query = em.createQuery(qString, UserSpending.class);
        List<UserSpending> userSpendings = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            query.setParameter("startDate", dateFormat.parse(startDate));
            query.setParameter("endDate", dateFormat.parse(endDate));
            userSpendings = query.getResultList();
        } catch (ParseException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            em.close();
        }
        return userSpendings;
    }

    public static void main(String[] agrs) {
        String searchName = "@gmail.com";
        List<User> searchedUser = searchUserByEmail(searchName);

        if (searchedUser != null && !searchedUser.isEmpty()) {
            System.out.println("Search results for shoes with name '" + searchName + "':");
            for (User shoes : searchedUser) {
                System.out.println("Product ID: " + shoes.getEmail());
                // Thêm các trường khác nếu cần
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("No results found for shoes with name '" + searchName + "'.");
        }
    }
}
