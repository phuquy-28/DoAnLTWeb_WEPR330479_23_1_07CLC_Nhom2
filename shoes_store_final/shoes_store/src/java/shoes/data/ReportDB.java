package shoes.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import shoes.business.Invoice;

public class ReportDB {

    public static Workbook getInvoiceReport(
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

        // create the workbook, its worksheet, and its title row
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Invoice Report");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("The Invoice Report");

        // create the header rows
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("Start Date: " + startDate);
        row = sheet.createRow(3);
        row.createCell(0).setCellValue("End Date: " + endDate);
        row = sheet.createRow(5);
        row.createCell(0).setCellValue("InvoiceID");
        row.createCell(1).setCellValue("Customer");
        row.createCell(2).setCellValue("Address");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("Date");
        row.createCell(5).setCellValue("PaymentMethod");
        row.createCell(6).setCellValue("SubTotal");
        row.createCell(7).setCellValue("Discount");
        row.createCell(8).setCellValue("Total");

        // create the data rows
        int count = 0;
        int total = 0;
        int subTotal = 0;
        int discount = 0;
        int i = 6;
        for (Invoice invoice : invoices) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(invoice.getInvoiceID());
            row.createCell(1).setCellValue(invoice.getUser().getFullName());
            row.createCell(2).setCellValue(invoice.getAddress().toString());
            row.createCell(3).setCellValue(invoice.getUser().getEmail());
            String formattedDate = dateFormat.format(invoice.getInvoiceDate());
            row.createCell(4).setCellValue(formattedDate);
            row.createCell(5).setCellValue(invoice.getPaymentMethod());
            row.createCell(6).setCellValue(invoice.getSubTotal());
            row.createCell(7).setCellValue(invoice.getDiscount());
            row.createCell(8).setCellValue(invoice.getTotal());
            count++;
            subTotal += invoice.getSubTotal();
            discount += invoice.getDiscount();
            total += invoice.getTotal();
            i++;
        }

        // auto-fit cho tất cả các cột sau khi đã thêm dữ liệu
        for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
            sheet.autoSizeColumn(colNum);
        }

        row = sheet.createRow(i++);
        row.createCell(0).setCellValue("Total Number of Invoice: " + count);

        row = sheet.createRow(i++);
        row.createCell(0).setCellValue("Sum of sub-total: " + subTotal);

        row = sheet.createRow(i++);
        row.createCell(0).setCellValue("Sum of discount: " + discount);

        row = sheet.createRow(i++);
        row.createCell(0).setCellValue("Sum of total: " + total);

        return workbook;
    }
}
