/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.apache.poi.ss.usermodel.Workbook;
import shoes.business.Invoice;

/**
 *
 * @author phuqu
 */
public class InvoiceDB {

    public static List<Invoice> selectsInvoices() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT i from Invoice i";
        TypedQuery<Invoice> q = em.createQuery(qString, Invoice.class);
        List<Invoice> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null; //
        } finally {
            em.close();
        }

        return results;
    }

    public static Invoice getInvoiceById(long invoiceID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            String qString = "SELECT i FROM Invoice i WHERE i.invoiceID = :invoiceID";
            TypedQuery<Invoice> query = em.createQuery(qString, Invoice.class);
            query.setParameter("invoiceID", invoiceID);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Invoice> getInvoiceByInterval(
            String startDate, String endDate) {

        // get the data from the database
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT i from Invoice i "
                + "WHERE i.invoiceDate >= :startDate AND "
                + "i.invoiceDate <= :endDate ORDER BY i.invoiceID DESC";
        TypedQuery<Invoice> q = em.createQuery(qString, Invoice.class);
        List<Invoice> invoices = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            q.setParameter("startDate", dateFormat.parse(startDate));
            q.setParameter("endDate", dateFormat.parse(endDate));
            invoices = q.getResultList();
        } catch (ParseException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            em.close();
        }
        return invoices;
    }

}
