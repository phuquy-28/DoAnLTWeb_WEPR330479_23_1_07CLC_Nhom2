/* If the Products table in the music_jpa database doesn't contain any records,
 * you can use this class to populate the Product table.
 *
 * To do this in NetBeans, press SHIFT+F6
 * while in this window, or right click and select Run File. It is normal for
 * it to take a few minutes to run.
 *
 * After you have done this, you will want to change the
 * javax.persistence.schema-generation.database.action value in the
 * persistence.xml file to "none". Otherwise, the application will pause for
 * two or three minutes each time you start it when it first makes a database
 * call.
 */
package shoes.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import static org.eclipse.persistence.config.EntityManagerProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.EntityManagerProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.EntityManagerProperties.JDBC_URL;
import static org.eclipse.persistence.config.EntityManagerProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TARGET_SERVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;
import org.eclipse.persistence.config.TargetServer;
import shoes.business.ShippingInfo;
import shoes.business.Cart;
import shoes.business.CategoryShoes;
import shoes.business.Invoice;
import shoes.business.LineItem;
import shoes.business.PromotionCode;
import shoes.business.ShoeSize;
import shoes.business.Shoes;
import shoes.business.User;
import shoes.business.sImage;

public class PopulateDatabase {

    private static EntityManagerFactory emf;

    public static void insertShoes(Shoes shoes) {
        EntityManager em = emf.createEntityManager();
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

    public static void insertCategory(CategoryShoes category) {
        EntityManager em = emf.createEntityManager();
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

    public static void insert(Invoice invoice) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(invoice);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        Map props = new HashMap();
        props.put(TRANSACTION_TYPE,
                PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
        props.put(JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
//        org.postgresql.Driver
//        com.mysql.cj.jdbc.Driver
        props.put(JDBC_URL,
                "jdbc:mysql://localhost:3306/shoes_jpa");
//        jdbc:postgresql://dpg-clkvflcjtl8s73f3u9l0-a.singapore-postgres.render.com/shoes_jpa
//        jdbc:mysql://localhost:3306/shoes_jpa
        props.put(JDBC_USER, "root");
        props.put(JDBC_PASSWORD, "nhat");
//        dfFkTLaFbXylyqVfmhes3Iiyoyb3j7Md
//        abc123
        props.put(TARGET_SERVER, TargetServer.None);

        emf = Persistence.createEntityManagerFactory("shoesStorePU", props);

        // Thêm admin
        User user1 = new User((long) 1, "abc1@gmail.com", "123456", "admin", "", "0342609928", 1, new Cart());
        insert(user1);
        // Nhân bản user2 thành 20 đối tượng mới
        for (int i = 2; i <= 21; i++) {
            User newUser;
            newUser = new User(
                    (long) i,
                    "abc" + i + "@gmail.com",
                    "123456",
                    "Van A",
                    "Nguyen",
                    "0342609928",
                    0,
                    new Cart());
            insert(newUser);
        }
        CategoryShoes airJordanCategory = new CategoryShoes();
        airJordanCategory.setCategoryName("Air Jordan");
        airJordanCategory.setCategoryID(51);

        CategoryShoes adidasCategory = new CategoryShoes();
        adidasCategory.setCategoryName("Adidas");
        adidasCategory.setCategoryID(52);

        CategoryShoes ascisCategory = new CategoryShoes();
        ascisCategory.setCategoryName("Ascis");
        ascisCategory.setCategoryID(53);

        CategoryShoes converseCategory = new CategoryShoes();
        converseCategory.setCategoryName("Converse");
        converseCategory.setCategoryID(54);

        CategoryShoes newBalanceCategory = new CategoryShoes();
        newBalanceCategory.setCategoryName("New Balance");
        newBalanceCategory.setCategoryID(55);

        CategoryShoes nikeCategory = new CategoryShoes();
        nikeCategory.setCategoryName("Nike");
        nikeCategory.setCategoryID(56);

        CategoryShoes yeezyCategory = new CategoryShoes();
        yeezyCategory.setCategoryName("Yeezy");
        yeezyCategory.setCategoryID(57);

        insertCategory(airJordanCategory);
        insertCategory(adidasCategory);
        insertCategory(ascisCategory);
        insertCategory(converseCategory);
        insertCategory(newBalanceCategory);
        insertCategory(nikeCategory);
        insertCategory(yeezyCategory);

        List<ShoeSize> shoesize1 = new ArrayList<>();
        double i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe1 = new Shoes();
        shoe1.setProductName("Jordan 11 Retro DMP Gratitude (2023)");
        shoe1.setCategory(airJordanCategory);
//        shoe1.setProductSize(7);
        shoe1.setSizes(shoesize1);
        shoe1.setProductColor("Black/White");
        shoe1.setProductDecription("The Jordan 11 Retro Gratitude / Defining Moments (2023) is a nod to the illustrious heritage encapsulated in the Jordan brand. This iconic Jordan 11 model resurfaces with a touch of the famous Defining Moments colorway.");
        shoe1.setProductPrice(271L);
        sImage image1 = new sImage("https://images.stockx.com/360/Air-Jordan-11-Retro-DMP-Defining-Moments-2023/Images/Air-Jordan-11-Retro-DMP-Defining-Moments-2023/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1699906570&h=384&q=57");
        sImage image2 = new sImage("https://images.stockx.com/360/Air-Jordan-11-Retro-DMP-Defining-Moments-2023/Images/Air-Jordan-11-Retro-DMP-Defining-Moments-2023/Lv2/img10.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1699906570&h=384&q=57");
        sImage image3 = new sImage("https://images.stockx.com/360/Air-Jordan-11-Retro-DMP-Defining-Moments-2023/Images/Air-Jordan-11-Retro-DMP-Defining-Moments-2023/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1699906570&h=384&q=57");
        List<sImage> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe1.setImages(images);
        insertShoes(shoe1);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe2 = new Shoes();
        shoe2.setProductName("Jordan 4 Retro SE Craft Medium Olive");
        shoe2.setCategory(airJordanCategory);
//        shoe2.setProductSize(7.5);
        shoe2.setSizes(shoesize1);
        shoe2.setProductColor("MEDIUM OLIVE/PALE VANILLA/KHAKI/BLACK/SAIL");
        shoe2.setProductDecription("Step into the season with the Jordan 4 Retro SE Craft in Medium Olive. This fresh take on a classic silhouette combines modern craftsmanship with a timeless design.");
        shoe2.setProductPrice(210L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-SE-Craft-Medium-Olive/Images/Air-Jordan-4-Retro-SE-Craft-Medium-Olive/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700036889&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-SE-Craft-Medium-Olive/Images/Air-Jordan-4-Retro-SE-Craft-Medium-Olive/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700036889&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-SE-Craft-Medium-Olive/Images/Air-Jordan-4-Retro-SE-Craft-Medium-Olive/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700036889&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe2.setImages(images);
        insertShoes(shoe2);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe3 = new Shoes();
        shoe3.setProductName("Jordan 1 Low Wolf Grey (Women's)");
        shoe3.setCategory(airJordanCategory);
//        shoe3.setProductSize(8);
        shoe3.setSizes(shoesize1);
        shoe3.setProductColor("WHITE/WOLF GREY-ALUMINIUM");
        shoe3.setProductDecription("The women's Jordan 1 Low Wolf Grey (W) features a white leather upper with Wolf Grey Durabuck overlays and Swooshes.");
        shoe3.setProductPrice(129L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Low-Wolf-Grey-W/Images/Air-Jordan-1-Low-Wolf-Grey-W/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635258914&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Low-Wolf-Grey-W/Images/Air-Jordan-1-Low-Wolf-Grey-W/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635258914&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Low-Wolf-Grey-W/Images/Air-Jordan-1-Low-Wolf-Grey-W/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635258914&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe3.setImages(images);
        insertShoes(shoe3);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe4 = new Shoes();
        shoe4.setProductName("Jordan 1 Retro High OG UNC Toe");
        shoe4.setCategory(airJordanCategory);
//        shoe4.setProductSize(8.5);
        shoe4.setSizes(shoesize1);
        shoe4.setProductColor("UNIVERSITY BLUE/BLACK/WHITE");
        shoe4.setProductDecription("The Jordan 1 High OG UNC Toe is taking the sneaker game by storm. Jordan Brand masterfully integrates a unique colorway combo of University Blue, Black, and White on this iconic silhouette.");
        shoe4.setProductPrice(157L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-1-High-OG-UNC-Toe/Images/Air-Jordan-1-High-OG-UNC-Toe/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1688674754&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-1-High-OG-UNC-Toe/Images/Air-Jordan-1-High-OG-UNC-Toe/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1688674754&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-1-High-OG-UNC-Toe/Images/Air-Jordan-1-High-OG-UNC-Toe/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1688674754&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe4.setImages(images);
        insertShoes(shoe4);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe5 = new Shoes();
        shoe5.setProductName("Jordan 4 Retro Thunder (2023)");
        shoe5.setCategory(airJordanCategory);
//        shoe5.setProductSize(9);
        shoe5.setSizes(shoesize1);
        shoe5.setProductColor("BLACK/TOUR YELLOW");
        shoe5.setProductDecription("For the first time in over a decade, the Air Jordan 4 Retro Thunder is returning and is being featured as part of the Jordan Brand Spring/Summer 2023 campaign.");
        shoe5.setProductPrice(186L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Thunder-2023/Images/Air-Jordan-4-Retro-Thunder-2023/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1680207753&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Thunder-2023/Images/Air-Jordan-4-Retro-Thunder-2023/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1680207753&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Thunder-2023/Images/Air-Jordan-4-Retro-Thunder-2023/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1680207753&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe5.setImages(images);
        insertShoes(shoe5);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe6 = new Shoes();
        shoe6.setProductName("Jordan 5 Retro A Ma Maniére Dawn");
        shoe6.setCategory(airJordanCategory);
//        shoe6.setProductSize(9.5);
        shoe6.setSizes(shoesize1);
        shoe6.setProductColor("PHOTON DUST/BLACK/DIFFUSED BLUE/PALE IVORY");
        shoe6.setProductDecription("Step into the dawn of a new era with the exclusive A Ma Maniére x Air Jordan 5 \"Dawn\". This collaboration infuses classic elegance with a street-ready edge.");
        shoe6.setProductPrice(171L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-5-Retro-A-Ma-Maniere-Diffused-Blue-Womens/Images/Air-Jordan-5-Retro-A-Ma-Maniere-Diffused-Blue-Womens/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700083206&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-5-Retro-A-Ma-Maniere-Diffused-Blue-Womens/Images/Air-Jordan-5-Retro-A-Ma-Maniere-Diffused-Blue-Womens/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700083206&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-5-Retro-A-Ma-Maniere-Diffused-Blue-Womens/Images/Air-Jordan-5-Retro-A-Ma-Maniere-Diffused-Blue-Womens/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700083206&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe6.setImages(images);
        insertShoes(shoe6);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe7 = new Shoes();
        shoe7.setProductName("Jordan 1 Retro High OG Royal Reimagined");
        shoe7.setCategory(airJordanCategory);
//        shoe7.setProductSize(10);
        shoe7.setSizes(shoesize1);
        shoe7.setProductColor("BLACK/ROYAL BLUE/WHITE");
        shoe7.setProductDecription("Old meets the new with the Jordan 1 Retro High OG 'Royal Reimagined'.");
        shoe7.setProductPrice(106L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Retro-High-OG-Royal-Reimagined/Images/Air-Jordan-1-Retro-High-OG-Royal-Reimagined/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1697743198&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Retro-High-OG-Royal-Reimagined/Images/Air-Jordan-1-Retro-High-OG-Royal-Reimagined/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1697743198&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Retro-High-OG-Royal-Reimagined/Images/Air-Jordan-1-Retro-High-OG-Royal-Reimagined/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1697743198&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe7.setImages(images);
        insertShoes(shoe7);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe8 = new Shoes();
        shoe8.setProductName("Jordan 4 Retro Frozen Moments (Women's)");
        shoe8.setCategory(airJordanCategory);
//        shoe8.setProductSize(10.5);
        shoe8.setSizes(shoesize1);
        shoe8.setProductColor("LIGHT IRON ORE/SAIL-NEUTRAL GREY-BLACK-METALLIC SILVER");
        shoe8.setProductDecription("The Jordan 4 Retro Frozen Moments made its debut on August 26, 2023. Cementing itself as a must-have, this iteration of the Jordan legacy showcased a balanced new design palette.");
        shoe8.setProductPrice(221L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Frozen-Moments-Womens/Images/Air-Jordan-4-Retro-Frozen-Moments-Womens/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692624367&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Frozen-Moments-Womens/Images/Air-Jordan-4-Retro-Frozen-Moments-Womens/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692624367&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Frozen-Moments-Womens/Images/Air-Jordan-4-Retro-Frozen-Moments-Womens/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692624367&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe8.setImages(images);
        insertShoes(shoe8);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe9 = new Shoes();
        shoe9.setProductName("Jordan 4 Retro Red Cement (GS)");
        shoe9.setCategory(airJordanCategory);
//        shoe9.setProductSize(11);
        shoe9.setSizes(shoesize1);
        shoe9.setProductColor("WHITE/FIRE RED/BLACK/NEUTRAL GREY");
        shoe9.setProductDecription("The Jordan 4 Retro Red Cement (GS) presents a fusion of colors, textures, shapes, and materials that will make any grade school kid wearing this sneaker the talk of the playground.");
        shoe9.setProductPrice(127L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Red-Cement-GS/Images/Air-Jordan-4-Retro-Red-Cement-GS/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692987915&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Red-Cement-GS/Images/Air-Jordan-4-Retro-Red-Cement-GS/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692987915&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-4-Retro-Red-Cement-GS/Images/Air-Jordan-4-Retro-Red-Cement-GS/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692987915&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe9.setImages(images);
        insertShoes(shoe9);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe10 = new Shoes();
        shoe10.setProductName("Jordan 8 Retro Playoffs (2023)");
        shoe10.setCategory(airJordanCategory);
//        shoe10.setProductSize(11.5);
        shoe10.setSizes(shoesize1);
        shoe10.setProductColor("BLACK/TRUE RED-WHITE");
        shoe10.setProductDecription("Introducing a legend reborn: the Jordan 8 Retro Playoffs. Cloaked in a sophisticated Black, True Red, and White colorway.");
        shoe10.setProductPrice(192L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-8-Retro-Playoffs-20203/Images/Air-Jordan-8-Retro-Playoffs-20203/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696577110&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-8-Retro-Playoffs-20203/Images/Air-Jordan-8-Retro-Playoffs-20203/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696577110&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-8-Retro-Playoffs-20203/Images/Air-Jordan-8-Retro-Playoffs-20203/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696577110&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe10.setImages(images);
        insertShoes(shoe10);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe11 = new Shoes();
        shoe11.setProductName("Jordan 1 Retro High OG Spider-Man");
        shoe11.setCategory(airJordanCategory);
//        shoe11.setProductSize(12);
        shoe11.setSizes(shoesize1);
        shoe11.setProductColor("UNIVERSITY RED/BLACK/WHITE");
        shoe11.setProductDecription("Nike and Jordan Brand are returning back to the Spider-Verse for their second Spider-Man themed Air Jordan 1, with the release of the Air Jordan 1 High OG Spider-Man Across the Spider-Verse.");
        shoe11.setProductPrice(125L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-1-High-OG-Spider-Man-Across-the-Spider-Verse/Images/Air-Jordan-1-High-OG-Spider-Man-Across-the-Spider-Verse/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1683569460&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-1-High-OG-Spider-Man-Across-the-Spider-Verse/Images/Air-Jordan-1-High-OG-Spider-Man-Across-the-Spider-Verse/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1683569460&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-1-High-OG-Spider-Man-Across-the-Spider-Verse/Images/Air-Jordan-1-High-OG-Spider-Man-Across-the-Spider-Verse/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1683569460&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe11.setImages(images);
        insertShoes(shoe11);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe12 = new Shoes();
        shoe12.setProductName("Jordan 1 Retro High OG Satin Bred");
        shoe12.setCategory(airJordanCategory);
//        shoe12.setProductSize(12.5);
        shoe12.setSizes(shoesize1);
        shoe12.setProductColor("BLACK/UNIVERSITY RED/WHITE");
        shoe12.setProductDecription("Unveiling the epitome of timeless design with the Jordan 1 Retro High OG Satin Bred (Women's) in the ever-classic Black/University Red/White colorway.");
        shoe12.setProductPrice(85L);
        image1 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Retro-High-OG-Satin-Bred/Images/Air-Jordan-1-Retro-High-OG-Satin-Bred/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696969279&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Retro-High-OG-Satin-Bred/Images/Air-Jordan-1-Retro-High-OG-Satin-Bred/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696969279&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Air-Jordan-1-Retro-High-OG-Satin-Bred/Images/Air-Jordan-1-Retro-High-OG-Satin-Bred/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696969279&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe12.setImages(images);
        insertShoes(shoe12);

        //Add Adidas Shoes
//        shoesize1 = new ArrayList<>();
//        i = 7;
//        while (i <= 12.5) {
//            shoesize1.add(new ShoeSize(i));
//            i += 0.5;
//        }
//        Shoes shoe13 = new Shoes();
//        shoe13.setProductName("adidas Yeezy Slide Onyx (2022/2023)");
//        shoe13.setCategory(adidasCategory);
////        shoe13.setProductSize(7);
//        shoe13.setSizes(shoesize1);
//        shoe13.setProductColor("BLACK/WHITE");
//        shoe13.setProductDecription("First revealed in February 2022 at the Donda 2 listening event in Miami, the adidas Yeezy Slide Onyx features an all-black foam construction with a soft footbed for comfort.");
//        shoe13.setProductPrice(108L);
//        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Black/Images/adidas-Yeezy-Slide-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1646687558&h=384&q=57");
//        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Black/Images/adidas-Yeezy-Slide-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1646687558&h=384&q=57");
//        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Black/Images/adidas-Yeezy-Slide-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1646687558&h=384&q=57");
//        images = new ArrayList<>();
//        images.add(image1);
//        images.add(image2);
//        images.add(image3);
//        shoe13.setImages(images);
//        insertShoes(shoe13);
//
//        shoesize1 = new ArrayList<>();
//        i = 7;
//        while (i <= 12.5) {
//            shoesize1.add(new ShoeSize(i));
//            i += 0.5;
//        }
//        Shoes shoe14 = new Shoes();
//        shoe14.setProductName("adidas Yeezy Slide Bone (2022/2023)");
//        shoe14.setCategory(adidasCategory);
////        shoe14.setProductSize(7.5);
//        shoe14.setSizes(shoesize1);
//        shoe14.setProductColor("BONE/BONE/BONE");
//        shoe14.setProductDecription("The adidas Yeezy Slide Bone (Restock Pair) features a slightly different composition than the original adidas Yeezy Slide Bone that debuted in the fall of 2019.");
//        shoe14.setProductPrice(150L);
//        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Bone-2022/Images/adidas-Yeezy-Slide-Bone-2022/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660144762&h=384&q=57");
//        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Bone-2022/Images/adidas-Yeezy-Slide-Bone-2022/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660144762&h=384&q=57");
//        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Bone-2022/Images/adidas-Yeezy-Slide-Bone-2022/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660144762&h=384&q=57");
//        images = new ArrayList<>();
//        images.add(image1);
//        images.add(image2);
//        images.add(image3);
//        shoe14.setImages(images);
//        insertShoes(shoe14);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe15 = new Shoes();
        shoe15.setProductName("adidas Samba OG Cloud White");
        shoe15.setCategory(adidasCategory);
//        shoe15.setProductSize(8);
        shoe15.setSizes(shoesize1);
        shoe15.setProductColor("CLOUD WHITE/CORE BLACK/CLEAR GRANITE");
        shoe15.setProductDecription("Originally designed to protect soccer players’ feet during winter, the adidas Samba OG Cloud White Core Black has transcended its sports function but still maintains its aesthetic appeal.");
        shoe15.setProductPrice(79L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Samba-OG-Cloud-White-Core-Black/Images/adidas-Samba-OG-Cloud-White-Core-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1687245728&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Samba-OG-Cloud-White-Core-Black/Images/adidas-Samba-OG-Cloud-White-Core-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1687245728&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Samba-OG-Cloud-White-Core-Black/Images/adidas-Samba-OG-Cloud-White-Core-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1687245728&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe15.setImages(images);
        insertShoes(shoe15);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe16 = new Shoes();
        shoe16.setProductName("adidas Yeezy Boost 350 V2 Onyx");
        shoe16.setCategory(adidasCategory);
//        shoe16.setProductSize(8.5);
        shoe16.setSizes(shoesize1);
        shoe16.setProductColor("BLACK");
        shoe16.setProductDecription("With a triple black Primeknit upper, the adidas Yeezy 350 V2 Onyx takes it back to the basics, using its muted palette to emphasize the intricacies of its construction.");
        shoe16.setProductPrice(240L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Onyx/Images/adidas-Yeezy-Boost-350-V2-Onyx/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1656426794&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Onyx/Images/adidas-Yeezy-Boost-350-V2-Onyx/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1656426794&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Onyx/Images/adidas-Yeezy-Boost-350-V2-Onyx/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1656426794&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe16.setImages(images);
        insertShoes(shoe16);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe17 = new Shoes();
        shoe17.setProductName("adidas Campus 00s Dark Green");
        shoe17.setCategory(adidasCategory);
//        shoe17.setProductSize(9);
        shoe17.setSizes(shoesize1);
        shoe17.setProductColor("DARK GREEN/CLOUD WHITE/OFF WHITE");
        shoe17.setProductDecription("The adidas Campus 00s Dark Green Cloud White comes in a dark green, cloud white, and off-white color scheme.");
        shoe17.setProductPrice(84L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Campus-00s-Dark-Green-Cloud-White/Images/adidas-Campus-00s-Dark-Green-Cloud-White/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1683710681&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Campus-00s-Dark-Green-Cloud-White/Images/adidas-Campus-00s-Dark-Green-Cloud-White/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1683710681&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Campus-00s-Dark-Green-Cloud-White/Images/adidas-Campus-00s-Dark-Green-Cloud-White/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1683710681&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe17.setImages(images);
        insertShoes(shoe17);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe18 = new Shoes();
        shoe18.setProductName("adidas Response CL Bad Bunny");
        shoe18.setCategory(adidasCategory);
//        shoe18.setProductSize(9.5);
        shoe18.setSizes(shoesize1);
        shoe18.setProductColor("ECRU TINT/BRONZE STRATA/EARTH STRATA");
        shoe18.setProductDecription("The adidas Response CL Bad Bunny Paso Fino is part of a collaboration between adidas and the Puerto Rican singer Bad Bunny.");
        shoe18.setProductPrice(113L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Response-CL-Bad-Bunny-Paso-Fino/Images/adidas-Response-CL-Bad-Bunny-Paso-Fino/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1697117224&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Response-CL-Bad-Bunny-Paso-Fino/Images/adidas-Response-CL-Bad-Bunny-Paso-Fino/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1697117224&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Response-CL-Bad-Bunny-Paso-Fino/Images/adidas-Response-CL-Bad-Bunny-Paso-Fino/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1697117224&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe18.setImages(images);
        insertShoes(shoe18);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe19 = new Shoes();
        shoe19.setProductName("adidas Campus 00s Crystal White");
        shoe19.setCategory(adidasCategory);
//        shoe19.setProductSize(10);
        shoe19.setSizes(shoesize1);
        shoe19.setProductColor("CRYSTAL WHITE/CORE BLACK/OFF WHITE");
        shoe19.setProductDecription("The adidas Campus 00s in Crystal White Core Black (Women) is a chunky sneaker with a design inspired by retro skate culture.");
        shoe19.setProductPrice(81L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Campus-00s-Crystal-White-Core-Black/Images/adidas-Campus-00s-Crystal-White-Core-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1690385196&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Campus-00s-Crystal-White-Core-Black/Images/adidas-Campus-00s-Crystal-White-Core-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1690385196&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Campus-00s-Crystal-White-Core-Black/Images/adidas-Campus-00s-Crystal-White-Core-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1690385196&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe19.setImages(images);
        insertShoes(shoe19);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe20 = new Shoes();
        shoe20.setProductName("adidas Gazelle Bold Magic Beige");
        shoe20.setCategory(adidasCategory);
//        shoe20.setProductSize(11);
        shoe20.setSizes(shoesize1);
        shoe20.setProductColor("CREAM WHITE/COLLEGIATE GREEN/MAGIC BEIGE");
        shoe20.setProductDecription("The adidas Gazelle Bold Magic Beige Collegiate Green Women’s combines neutral colors and the brand’s classic Gazelle style.");
        shoe20.setProductPrice(108L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Gazelle-Bold-Magic-Beige-Collegiate-Green-Womens/Images/adidas-Gazelle-Bold-Magic-Beige-Collegiate-Green-Womens/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694719506&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Gazelle-Bold-Magic-Beige-Collegiate-Green-Womens/Images/adidas-Gazelle-Bold-Magic-Beige-Collegiate-Green-Womens/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694719506&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Gazelle-Bold-Magic-Beige-Collegiate-Green-Womens/Images/adidas-Gazelle-Bold-Magic-Beige-Collegiate-Green-Womens/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694719506&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe20.setImages(images);
        insertShoes(shoe20);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe21 = new Shoes();
        shoe21.setProductName("adidas Forum Low The Simpsons");
        shoe21.setCategory(adidasCategory);
//        shoe21.setProductSize(11.5);
        shoe21.setSizes(shoesize1);
        shoe21.setProductColor("MESA/TACTILE ROSE/CLOUD WHITE");
        shoe21.setProductDecription("The adidas Forum Low The Simpsons Living Room sneaker is a whimsical tribute to the beloved TV series.");
        shoe21.setProductPrice(105L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Forum-Low-The-Simpsons-Living-Room/Images/adidas-Forum-Low-The-Simpsons-Living-Room/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1699291089&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Forum-Low-The-Simpsons-Living-Room/Images/adidas-Forum-Low-The-Simpsons-Living-Room/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1699291089&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Forum-Low-The-Simpsons-Living-Room/Images/adidas-Forum-Low-The-Simpsons-Living-Room/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1699291089&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe21.setImages(images);
        insertShoes(shoe21);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe22 = new Shoes();
        shoe22.setProductName("adidas Yeezy Boost 350 V2 MX");
        shoe22.setCategory(adidasCategory);
//        shoe22.setProductSize(12);
        shoe22.setSizes(shoesize1);
        shoe22.setProductColor("BLACK");
        shoe22.setProductDecription("Released on August 21, 2023, the adidas Yeezy Boost 350 V2 MX Dark Salt is more than just a sneaker; it's a statement in style and comfort.");
        shoe22.setProductPrice(271L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-MX-Dark-Salt/Images/adidas-Yeezy-Boost-350-V2-MX-Dark-Salt/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694442126&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-MX-Dark-Salt/Images/adidas-Yeezy-Boost-350-V2-MX-Dark-Salt/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694442126&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-MX-Dark-Salt/Images/adidas-Yeezy-Boost-350-V2-MX-Dark-Salt/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694442126&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe22.setImages(images);
        insertShoes(shoe22);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe23 = new Shoes();
        shoe23.setProductName("adidas Samba OG Collegiate Green");
        shoe23.setCategory(adidasCategory);
//        shoe23.setProductSize(12.5);
        shoe23.setSizes(shoesize1);
        shoe23.setProductColor("COLLEGIATE GREEN/FOOTWEAR WHITE/GUM");
        shoe23.setProductDecription("The adidas Samba OG Collegiate Green Gum Grey Toe is a modern take on the adidas Samba sneaker.");
        shoe23.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Samba-OG-Collegiate-Green-Gum-Grey-Toe/Images/adidas-Samba-OG-Collegiate-Green-Gum-Grey-Toe/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1684999471&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Samba-OG-Collegiate-Green-Gum-Grey-Toe/Images/adidas-Samba-OG-Collegiate-Green-Gum-Grey-Toe/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1684999471&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Samba-OG-Collegiate-Green-Gum-Grey-Toe/Images/adidas-Samba-OG-Collegiate-Green-Gum-Grey-Toe/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1684999471&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe23.setImages(images);
        insertShoes(shoe23);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe24 = new Shoes();
        shoe24.setProductName("adidas adiFOM Superstar CLOUD");
        shoe24.setCategory(adidasCategory);
//        shoe24.setProductSize(10.5);
        shoe24.setSizes(shoesize1);
        shoe24.setProductColor("PANTONE/CLOUD WHITE/PANTONE");
        shoe24.setProductDecription("");
        shoe24.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/adidas-adiFOM-Superstar-The-Simpsons-Clouds/Images/adidas-adiFOM-Superstar-The-Simpsons-Clouds/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700248781&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-adiFOM-Superstar-The-Simpsons-Clouds/Images/adidas-adiFOM-Superstar-The-Simpsons-Clouds/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700248781&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-adiFOM-Superstar-The-Simpsons-Clouds/Images/adidas-adiFOM-Superstar-The-Simpsons-Clouds/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1700248781&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe24.setImages(images);
        insertShoes(shoe24);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe25 = new Shoes();
        shoe25.setProductName("ASICS Gel-1130 White Clay Canyon");
        shoe25.setCategory(ascisCategory);
        shoe25.setSizes(shoesize1);
        shoe25.setProductColor("WHITE/CLAY CANYON");
        shoe25.setProductDecription("The ASICS Gel-1130 in White Clay Canyon is a shoe with its design inspired by the Gel-KAYANO 14 sneaker, with a similar look and updated technology.");
        shoe25.setProductPrice(149L);
        image1 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-White-Clay-Canyon/Images/ASICS-Gel-1130-White-Clay-Canyon/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1693921714&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-White-Clay-Canyon/Images/ASICS-Gel-1130-White-Clay-Canyon/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1693921714&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-White-Clay-Canyon/Images/ASICS-Gel-1130-White-Clay-Canyon/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1693921714&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe25.setImages(images);
        insertShoes(shoe25);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe26 = new Shoes();
        shoe26.setProductName("Onitsuka Tiger Mexico 66 Kill Bill");
        shoe26.setCategory(ascisCategory);
        shoe26.setSizes(shoesize1);
        shoe26.setProductColor("YELLOW/BLACK");
        shoe26.setProductDecription("The Onitsuka Tiger Mexico 66 surfaces in a striking Yellow/Black combo, channeling major vintage vibes while syncing perfectly with modern trends.");
        shoe26.setProductPrice(120L);
        image1 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Kill-Bill-2022/Images/Onitsuka-Tiger-Mexico-66-Kill-Bill-2022/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692282181&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Kill-Bill-2022/Images/Onitsuka-Tiger-Mexico-66-Kill-Bill-2022/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692282181&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Kill-Bill-2022/Images/Onitsuka-Tiger-Mexico-66-Kill-Bill-2022/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692282181&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe26.setImages(images);
        insertShoes(shoe26);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe27 = new Shoes();
        shoe27.setProductName("Onitsuka Tiger Mexico 66 Silver");
        shoe27.setCategory(ascisCategory);
        shoe27.setSizes(shoesize1);
        shoe27.setProductColor("SILVER/OFF WHITE");
        shoe27.setProductDecription("The Onitsuka Tiger Mexico 66 drops in a striking Silver/Off White palette, seamlessly merging vintage vibes with contemporary fashion.");
        shoe27.setProductPrice(120L);
        image1 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Silver-Off-White/Images/Onitsuka-Tiger-Mexico-66-Silver-Off-White/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694442062&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Silver-Off-White/Images/Onitsuka-Tiger-Mexico-66-Silver-Off-White/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694442062&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Silver-Off-White/Images/Onitsuka-Tiger-Mexico-66-Silver-Off-White/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694442062&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe27.setImages(images);
        insertShoes(shoe27);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe28 = new Shoes();
        shoe28.setProductName("ASICS Gel-NYC White Oyster Grey");
        shoe28.setCategory(ascisCategory);
        shoe28.setSizes(shoesize1);
        shoe28.setProductColor("WHITE/OYSTER GREY");
        shoe28.setProductDecription("The ASICS Gel-NYC in White Oyster Gray is a running shoe that combines elements of other ASICS Gel sneakers to create a comfortable and high-performance hybrid.");
        shoe28.setProductPrice(130L);
        image1 = new sImage("https://images.stockx.com/360/ASICS-Gel-NYC-White-Oyster-Grey/Images/ASICS-Gel-NYC-White-Oyster-Grey/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694100180&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/ASICS-Gel-NYC-White-Oyster-Grey/Images/ASICS-Gel-NYC-White-Oyster-Grey/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694100180&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/ASICS-Gel-NYC-White-Oyster-Grey/Images/ASICS-Gel-NYC-White-Oyster-Grey/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694100180&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe28.setImages(images);
        insertShoes(shoe28);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe29 = new Shoes();
        shoe29.setProductName("ASICS Gel-1130 Black Pure Silver");
        shoe29.setCategory(ascisCategory);
        shoe29.setSizes(shoesize1);
        shoe29.setProductColor("BLACK/PURE SILVER");
        shoe29.setProductDecription("Initially debuting in 2008, the ASICS Gel-1130 Black Pure Silver sneakers draw inspiration from the design elements of the GEL-KAYANO 14.");
        shoe29.setProductPrice(95L);
        image1 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-Black-Pure-Silver/Images/ASICS-Gel-1130-Black-Pure-Silver/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692624365&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-Black-Pure-Silver/Images/ASICS-Gel-1130-Black-Pure-Silver/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692624365&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-Black-Pure-Silver/Images/ASICS-Gel-1130-Black-Pure-Silver/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692624365&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe29.setImages(images);
        insertShoes(shoe29);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe30 = new Shoes();
        shoe30.setProductName("Onitsuka Tiger Mexico 66 Black");
        shoe30.setCategory(ascisCategory);
        shoe30.setSizes(shoesize1);
        shoe30.setProductColor("BLACK/WHITE");
        shoe30.setProductDecription("The Onitsuka Tiger Mexico 66 presents itself in an effortlessly sleek Black/White combo, blending the iconic retro silhouette with today's streetwear sensibilities.");
        shoe30.setProductPrice(120L);
        image1 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Black-White/Images/Onitsuka-Tiger-Mexico-66-Black-White/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692109371&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Black-White/Images/Onitsuka-Tiger-Mexico-66-Black-White/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692109371&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Onitsuka-Tiger-Mexico-66-Black-White/Images/Onitsuka-Tiger-Mexico-66-Black-White/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692109371&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe30.setImages(images);
        insertShoes(shoe30);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe31 = new Shoes();
        shoe31.setProductName("ASICS Gel-1130 Cream Ironclad");
        shoe31.setCategory(ascisCategory);
        shoe31.setSizes(shoesize1);
        shoe31.setProductColor("CREAM/IRONCLAD");
        shoe31.setProductDecription("Inspired by the GEL-Kayano 14 sneaker, the ASICS Gel-1130 Cream Ironclad is a running shoe from the ASICS Gel-1130 lineup in a \" Cream Ironclad\" colorway.");
        shoe31.setProductPrice(95L);
        image1 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-Cream-Ironclad/Images/ASICS-Gel-1130-Cream-Ironclad/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692984326&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-Cream-Ironclad/Images/ASICS-Gel-1130-Cream-Ironclad/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692984326&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/ASICS-Gel-1130-Cream-Ironclad/Images/ASICS-Gel-1130-Cream-Ironclad/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692984326&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe31.setImages(images);
        insertShoes(shoe31);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe32 = new Shoes();
        shoe32.setProductName("Converse CONS AS-1 Pro Black");
        shoe32.setCategory(converseCategory);
        shoe32.setSizes(shoesize1);
        shoe32.setProductColor("BLACK/WHITE/GUM");
        shoe32.setProductDecription("Japanese designer Rei Kawakubo is one of the most prolific fashion designers of all time. Aside from her Comme des Garçons collections, she has had numerous sneaker collaborations over the years.");
        shoe32.setProductPrice(125L);
        image1 = new sImage("https://images.stockx.com/360/Converse-CONS-AS-1-Pro-Black-White-Gum/Images/Converse-CONS-AS-1-Pro-Black-White-Gum/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692087817&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-CONS-AS-1-Pro-Black-White-Gum/Images/Converse-CONS-AS-1-Pro-Black-White-Gum/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692087817&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-CONS-AS-1-Pro-Black-White-Gum/Images/Converse-CONS-AS-1-Pro-Black-White-Gum/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692087817&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe32.setImages(images);
        insertShoes(shoe32);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe33 = new Shoes();
        shoe33.setProductName("Converse Chuck Taylor All Star Hi");
        shoe33.setCategory(converseCategory);
        shoe33.setSizes(shoesize1);
        shoe33.setProductColor("BLACK");
        shoe33.setProductDecription("The Converse Chuck Taylor All Star Hi Black is a classic sneaker that has been a staple in the fashion world for decades.");
        shoe33.setProductPrice(125L);
        image1 = new sImage("https://images.stockx.com/360/Converse-Chuck-Taylor-All-Star-Hi-Black/Images/Converse-Chuck-Taylor-All-Star-Hi-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1685115008&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-Chuck-Taylor-All-Star-Hi-Black/Images/Converse-Chuck-Taylor-All-Star-Hi-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1685115008&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-Chuck-Taylor-All-Star-Hi-Black/Images/Converse-Chuck-Taylor-All-Star-Hi-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1685115008&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe33.setImages(images);
        insertShoes(shoe33);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe34 = new Shoes();
        shoe34.setProductName("Converse Chuck One Star Ox Stussy");
        shoe34.setCategory(converseCategory);
        shoe34.setSizes(shoesize1);
        shoe34.setProductColor("BLACK/BLACK/WHITE");
        shoe34.setProductDecription("The Converse One Star OX Stüssy refreshes Converse's iconic star logo with Shawn Stüssy's eclectic stylization. Featuring a thick black canvas construction");
        shoe34.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/Converse-Chuck-One-Star-Ox-Stussy-Black/Images/Converse-Chuck-One-Star-Ox-Stussy-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1655735651&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-Chuck-One-Star-Ox-Stussy-Black/Images/Converse-Chuck-One-Star-Ox-Stussy-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1655735651&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-Chuck-One-Star-Ox-Stussy-Black/Images/Converse-Chuck-One-Star-Ox-Stussy-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1655735651&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe34.setImages(images);
        insertShoes(shoe34);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe35 = new Shoes();
        shoe35.setProductName("Converse Run Star Legacy CX Hi");
        shoe35.setCategory(converseCategory);
        shoe35.setSizes(shoesize1);
        shoe35.setProductColor("BLACK/BLACK/EGRET");
        shoe35.setProductDecription("The Converse One Star OX Stüssy refreshes Converse's iconic star logo with Shawn Stüssy's eclectic stylization. Featuring a thick black canvas construction");
        shoe35.setProductPrice(120L);
        image1 = new sImage("https://images.stockx.com/360/Converse-Run-Star-Legacy-CX-Hi-Black/Images/Converse-Run-Star-Legacy-CX-Hi-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1675343904&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-Run-Star-Legacy-CX-Hi-Black/Images/Converse-Run-Star-Legacy-CX-Hi-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1675343904&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-Run-Star-Legacy-CX-Hi-Black/Images/Converse-Run-Star-Legacy-CX-Hi-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1675343904&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe35.setImages(images);
        insertShoes(shoe35);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe36 = new Shoes();
        shoe36.setProductName("Converse Run Star Motion Black");
        shoe36.setCategory(converseCategory);
        shoe36.setSizes(shoesize1);
        shoe36.setProductColor("BLACK/WHITE/GUM HONEY");
        shoe36.setProductDecription("The Converse Run Star Motion Black White Gum is a sneaker from the Converse Run Star Motion pack in a Black/White/Gum colorway.");
        shoe36.setProductPrice(130L);
        image1 = new sImage("https://images.stockx.com/360/Converse-Run-Star-Motion-Black-White-Gum/Images/Converse-Run-Star-Motion-Black-White-Gum/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635274730&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-Run-Star-Motion-Black-White-Gum/Images/Converse-Run-Star-Motion-Black-White-Gum/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635274730&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-Run-Star-Motion-Black-White-Gum/Images/Converse-Run-Star-Motion-Black-White-Gum/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635274730&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe36.setImages(images);
        insertShoes(shoe36);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe37 = new Shoes();
        shoe37.setProductName("Converse Gianno Golf le Fleur");
        shoe37.setCategory(converseCategory);
        shoe37.setSizes(shoesize1);
        shoe37.setProductColor("PARFAIT PINK");
        shoe37.setProductDecription("The Converse Gianno Golf Le Fleur Parfait Pink is a collaboration between Converse and the hip hop musician Tyler");
        shoe37.setProductPrice(130L);
        image1 = new sImage("https://images.stockx.com/360/Converse-Gianno-Golf-Le-Fleur-Parfait-Pink/Images/Converse-Gianno-Golf-Le-Fleur-Parfait-Pink/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635307673&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-Gianno-Golf-Le-Fleur-Parfait-Pink/Images/Converse-Gianno-Golf-Le-Fleur-Parfait-Pink/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635307673&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-Gianno-Golf-Le-Fleur-Parfait-Pink/Images/Converse-Gianno-Golf-Le-Fleur-Parfait-Pink/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635307673&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe37.setImages(images);
        insertShoes(shoe37);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe38 = new Shoes();
        shoe38.setProductName("Converse Aeon Active CX Ox");
        shoe38.setCategory(converseCategory);
        shoe38.setSizes(shoesize1);
        shoe38.setProductColor("STORM WIND/BLACK");
        shoe38.setProductDecription("The Converse Gianno Golf Le Fleur Parfait Pink is a collaboration between Converse and the hip hop musician Tyler");
        shoe38.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/Converse-Aeon-Active-CX-Ox-Storm-Wind/Images/Converse-Aeon-Active-CX-Ox-Storm-Wind/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1668756023&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Converse-Aeon-Active-CX-Ox-Storm-Wind/Images/Converse-Aeon-Active-CX-Ox-Storm-Wind/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1668756023&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Converse-Aeon-Active-CX-Ox-Storm-Wind/Images/Converse-Aeon-Active-CX-Ox-Storm-Wind/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1668756023&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe38.setImages(images);
        insertShoes(shoe38);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe39 = new Shoes();
        shoe39.setProductName("New Balance 550 White Green");
        shoe39.setCategory(newBalanceCategory);
        shoe39.setSizes(shoesize1);
        shoe39.setProductColor("WHITE/GREEN");
        shoe39.setProductDecription("The New Balance 550 White Green features a smooth white leather upper with a grey suede toe wrap and green detailing. ");
        shoe39.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-550-White-Green/Images/New-Balance-550-White-Green/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635799416&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-550-White-Green/Images/New-Balance-550-White-Green/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635799416&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-550-White-Green/Images/New-Balance-550-White-Green/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635799416&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe39.setImages(images);
        insertShoes(shoe39);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe40 = new Shoes();
        shoe40.setProductName("New Balance 9060 Rain Cloud ");
        shoe40.setCategory(newBalanceCategory);
        shoe40.setSizes(shoesize1);
        shoe40.setProductColor("RAIN CLOUD/CASTLEROCK");
        shoe40.setProductDecription("The New Balance 9060 Rain Cloud Grey takes on an early 2000s design by the American brand.");
        shoe40.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-9060-Rain-Cloud-Grey/Images/New-Balance-9060-Rain-Cloud-Grey/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1689149957&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-9060-Rain-Cloud-Grey/Images/New-Balance-9060-Rain-Cloud-Grey/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1689149957&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-9060-Rain-Cloud-Grey/Images/New-Balance-9060-Rain-Cloud-Grey/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1689149957&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe40.setImages(images);
        insertShoes(shoe40);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe41 = new Shoes();
        shoe41.setProductName("New Balance 550 Cream Black");
        shoe41.setCategory(newBalanceCategory);
        shoe41.setSizes(shoesize1);
        shoe41.setProductColor("SEA SALT/CREAM/BLACK");
        shoe41.setProductDecription("The New Balance 550 Cream Black features a smooth white leather upper with perforated overlays, black piping, mesh collars");
        shoe41.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-550-Cream-Black/Images/New-Balance-550-Cream-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1651763703&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-550-Cream-Black/Images/New-Balance-550-Cream-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1651763703&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-550-Cream-Black/Images/New-Balance-550-Cream-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1651763703&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe41.setImages(images);
        insertShoes(shoe41);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe42 = new Shoes();
        shoe42.setProductName("New Balance 530 White Silver");
        shoe42.setCategory(newBalanceCategory);
        shoe42.setSizes(shoesize1);
        shoe42.setProductColor("WHITE/SILVER/NAVY");
        shoe42.setProductDecription("The New Balance 530 White Silver Navy features a white mesh upper with metallic silver and white leather overlays.");
        shoe42.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-530-White-Silver-Navy/Images/New-Balance-530-White-Silver-Navy/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1657865757&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-530-White-Silver-Navy/Images/New-Balance-530-White-Silver-Navy/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1657865757&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-530-White-Silver-Navy/Images/New-Balance-530-White-Silver-Navy/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1657865757&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe42.setImages(images);
        insertShoes(shoe42);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe43 = new Shoes();
        shoe43.setProductName("New Balance 9060 Sea Salt White");
        shoe43.setCategory(newBalanceCategory);
        shoe43.setSizes(shoesize1);
        shoe43.setProductColor("SEA SALT/WHITE");
        shoe43.setProductDecription("The New Balance 9060 Sea Salt White is a sleek and stylish sneaker that combines modern design with classic elements.");
        shoe43.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-9060-Sea-Salt-White/Images/New-Balance-9060-Sea-Salt-White/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1675412470&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-9060-Sea-Salt-White/Images/New-Balance-9060-Sea-Salt-White/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1675412470&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-9060-Sea-Salt-White/Images/New-Balance-9060-Sea-Salt-White/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1675412470&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe43.setImages(images);
        insertShoes(shoe43);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe44 = new Shoes();
        shoe44.setProductName("New Balance 550 White Black");
        shoe44.setCategory(newBalanceCategory);
        shoe44.setSizes(shoesize1);
        shoe44.setProductColor("WHITE/BLACK");
        shoe44.setProductDecription("Taking cues from the 1989 New Balance P550, the New Balance 550 White Black features a smooth white leather upper with perforated overlays and black piping.");
        shoe44.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-550-White-Black/Images/New-Balance-550-White-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1638828769&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-550-White-Black/Images/New-Balance-550-White-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1638828769&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-550-White-Black/Images/New-Balance-550-White-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1638828769&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe44.setImages(images);
        insertShoes(shoe44);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe45 = new Shoes();
        shoe45.setProductName("New Balance 9060 Quartz Grey");
        shoe45.setCategory(newBalanceCategory);
        shoe45.setSizes(shoesize1);
        shoe45.setProductColor("QUARTZ GREY/TEAM CREAM/SEA SALT");
        shoe45.setProductDecription("The New Balance 9060 Quartz Grey Team Cream Sea Salt is a sleek and stylish sneaker that is perfect for both casual and athletic wear.");
        shoe45.setProductPrice(100L);
        image1 = new sImage("https://images.stockx.com/360/New-Balance-9060-Quartz-Grey-Team-Cream-Sea-Salt/Images/New-Balance-9060-Quartz-Grey-Team-Cream-Sea-Salt/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686040378&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/New-Balance-9060-Quartz-Grey-Team-Cream-Sea-Salt/Images/New-Balance-9060-Quartz-Grey-Team-Cream-Sea-Salt/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686040378&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/New-Balance-9060-Quartz-Grey-Team-Cream-Sea-Salt/Images/New-Balance-9060-Quartz-Grey-Team-Cream-Sea-Salt/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686040378&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe45.setImages(images);
        insertShoes(shoe45);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe46 = new Shoes();
        shoe46.setProductName("Nike Kobe 6 Protro Reverse");
        shoe46.setCategory(nikeCategory);
        shoe46.setSizes(shoesize1);
        shoe46.setProductColor("BRIGHT CRIMSON/BLACK/ELECTRIC");
        shoe46.setProductDecription("Step into the future while honoring a legend with the Nike Kobe 6 Protro Reverse Grinch.");
        shoe46.setProductPrice(706L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Kobe-6-Protro-Reverse-Grinch/Images/Nike-Kobe-6-Protro-Reverse-Grinch/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1701444492&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Kobe-6-Protro-Reverse-Grinch/Images/Nike-Kobe-6-Protro-Reverse-Grinch/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1701444492&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Kobe-6-Protro-Reverse-Grinch/Images/Nike-Kobe-6-Protro-Reverse-Grinch/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1701444492&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe46.setImages(images);
        insertShoes(shoe46);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe47 = new Shoes();
        shoe47.setProductName("Nike Dunk Low Athletic Department");
        shoe47.setCategory(nikeCategory);
        shoe47.setSizes(shoesize1);
        shoe47.setProductColor("JUNGLE/COCONUT MILK/WHITE");
        shoe47.setProductDecription("The Nike VaporMax Plus Triple Black is a combination of the Air Max Plus series, which was released in 1998, and the VaporMax series.");
        shoe47.setProductPrice(500L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Athletic-Department-Deep-Jungle/Images/Nike-Dunk-Low-Athletic-Department-Deep-Jungle/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696865046&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Athletic-Department-Deep-Jungle/Images/Nike-Dunk-Low-Athletic-Department-Deep-Jungle/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696865046&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Athletic-Department-Deep-Jungle/Images/Nike-Dunk-Low-Athletic-Department-Deep-Jungle/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1696865046&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe47.setImages(images);
        insertShoes(shoe47);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe48 = new Shoes();
        shoe48.setProductName("Nike Dunk Low Photon Dust");
        shoe48.setCategory(nikeCategory);
        shoe48.setSizes(shoesize1);
        shoe48.setProductColor("WHITE/PHOTON DUST-WHITE");
        shoe48.setProductDecription("The women's Nike Dunk Low Photon Dust (W) is made of white leather with Photon Dust leather overlays and Swooshes. ");
        shoe48.setProductPrice(445L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Photon-Dust-W/Images/Nike-Dunk-Low-Photon-Dust-W/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635256235&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Photon-Dust-W/Images/Nike-Dunk-Low-Photon-Dust-W/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635256235&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Photon-Dust-W/Images/Nike-Dunk-Low-Photon-Dust-W/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1635256235&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe48.setImages(images);
        insertShoes(shoe48);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe49 = new Shoes();
        shoe49.setProductName("Nike Dunk Low Industrial Blue");
        shoe49.setCategory(nikeCategory);
        shoe49.setSizes(shoesize1);
        shoe49.setProductColor("SUMMIT WHITE/INDUSTRIAL BLUE");
        shoe49.setProductDecription("Nike is drawing inspiration from Japan, using traditional Sashiko stitching on the 2023 release of the Nike Dunk Low Industrial Blue Sashiko.");
        shoe49.setProductPrice(336L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Industrial-Blue-Sashiko/Images/Nike-Dunk-Low-Industrial-Blue-Sashiko/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1676280180&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Industrial-Blue-Sashiko/Images/Nike-Dunk-Low-Industrial-Blue-Sashiko/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1676280180&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Industrial-Blue-Sashiko/Images/Nike-Dunk-Low-Industrial-Blue-Sashiko/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1676280180&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe49.setImages(images);
        insertShoes(shoe49);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe50 = new Shoes();
        shoe50.setProductName("Nike Dunk Low Retro White");
        shoe50.setCategory(nikeCategory);
        shoe50.setSizes(shoesize1);
        shoe50.setProductColor("WHITE/BLACK");
        shoe50.setProductDecription("From the school-spirited College Colors Program to the vibrant Nike CO.JP collection, Nike Dunks have seen many colorways since the design’s inception in 1985.");
        shoe50.setProductPrice(226L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Retro-White-Black-2021/Images/Nike-Dunk-Low-Retro-White-Black-2021/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1644250003&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Retro-White-Black-2021/Images/Nike-Dunk-Low-Retro-White-Black-2021/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1644250003&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Retro-White-Black-2021/Images/Nike-Dunk-Low-Retro-White-Black-2021/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1644250003&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe50.setImages(images);
        insertShoes(shoe50);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe51 = new Shoes();
        shoe51.setProductName("Nike Dunk Low Cacao Wow");
        shoe51.setCategory(nikeCategory);
        shoe51.setSizes(shoesize1);
        shoe51.setProductColor("SAIL/CACAO WOW-COCONUT");
        shoe51.setProductDecription("Nike did it again on July 28, 2023, when they dropped the Dunk Low in the enticing Cacao Wow colorway. ");
        shoe51.setProductPrice(335L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Cacao-Wow-Womens/Images/Nike-Dunk-Low-Cacao-Wow-Womens/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686774325&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Cacao-Wow-Womens/Images/Nike-Dunk-Low-Cacao-Wow-Womens/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686774325&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Cacao-Wow-Womens/Images/Nike-Dunk-Low-Cacao-Wow-Womens/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686774325&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe51.setImages(images);
        insertShoes(shoe51);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe52 = new Shoes();
        shoe52.setProductName("Nike Dunk Low Polar Blue");
        shoe52.setCategory(nikeCategory);
        shoe52.setSizes(shoesize1);
        shoe52.setProductColor("POLAR/POLAR/WHITE/WHITE");
        shoe52.setProductDecription("The Nike Dunk Low in Polar Blue is a unique and crisp colorway of the Dunk Low shoes traditionally worn on the basketball court since the mid-1980s. ");
        shoe52.setProductPrice(229L);
        image1 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Polar-Blue/Images/Nike-Dunk-Low-Polar-Blue/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694715998&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Polar-Blue/Images/Nike-Dunk-Low-Polar-Blue/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694715998&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/Nike-Dunk-Low-Polar-Blue/Images/Nike-Dunk-Low-Polar-Blue/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1694715998&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe52.setImages(images);
        insertShoes(shoe52);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe53 = new Shoes();
        shoe53.setProductName("adidas Yeezy Boost 350 V2 Bone");
        shoe53.setCategory(yeezyCategory);
        shoe53.setSizes(shoesize1);
        shoe53.setProductColor("BONE/BONE/BONE");
        shoe53.setProductDecription("The adidas Yeezy Boost 350 V2 Bone features a triple white Primeknit upper with mesh side stripes and canvas heel tabs.");
        shoe53.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Pure-Oat/Images/adidas-Yeezy-Boost-350-V2-Pure-Oat/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1648479649&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Pure-Oat/Images/adidas-Yeezy-Boost-350-V2-Pure-Oat/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1648479649&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Pure-Oat/Images/adidas-Yeezy-Boost-350-V2-Pure-Oat/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1648479649&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe53.setImages(images);
        insertShoes(shoe53);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe54 = new Shoes();
        shoe54.setProductName("adidas Yeezy Foam RNR Carbon");
        shoe54.setCategory(yeezyCategory);
        shoe54.setSizes(shoesize1);
        shoe54.setProductColor("CARBON/CARBON/CARBON");
        shoe54.setProductDecription("The adidas Yeezy Foam RNR Carbon, released on August 8, 2023, continues to push the boundaries of sneaker design.");
        shoe54.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-Carbon/Images/adidas-Yeezy-Foam-RNR-Carbon/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1691763798&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-Carbon/Images/adidas-Yeezy-Foam-RNR-Carbon/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1691763798&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-Carbon/Images/adidas-Yeezy-Foam-RNR-Carbon/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1691763798&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe54.setImages(images);
        insertShoes(shoe54);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe55 = new Shoes();
        shoe55.setProductName("adidas Yeezy Foam RNR MX Cinder");
        shoe55.setCategory(yeezyCategory);
        shoe55.setSizes(shoesize1);
        shoe55.setProductColor("MX CINDER/MX CINDER/MX CINDER");
        shoe55.setProductDecription("The adidas Yeezy Foam RNNR MX Cinder is where high fashion meets innovative comfort. Wrapped in a MX Cinder colorway,");
        shoe55.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNNR-MX-Brown-Blue/Images/adidas-Yeezy-Foam-RNNR-MX-Brown-Blue/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686137075&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNNR-MX-Brown-Blue/Images/adidas-Yeezy-Foam-RNNR-MX-Brown-Blue/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686137075&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNNR-MX-Brown-Blue/Images/adidas-Yeezy-Foam-RNNR-MX-Brown-Blue/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686137075&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe55.setImages(images);
        insertShoes(shoe55);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe56 = new Shoes();
        shoe56.setProductName("adidas Yeezy Foam RNR MX Carbon");
        shoe56.setCategory(yeezyCategory);
        shoe56.setSizes(shoesize1);
        shoe56.setProductColor("MX CARBON/MX CARBON/MX CARBON");
        shoe56.setProductDecription("The adidas Yeezy Foam RNR MX Carbon features a pink, yellow, purple, and dark grey marbled EVA foam and recycled algae construction with ventilation holes throughout the design.");
        shoe56.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-MX-Carbon/Images/adidas-Yeezy-Foam-RNR-MX-Carbon/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660202428&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-MX-Carbon/Images/adidas-Yeezy-Foam-RNR-MX-Carbon/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660202428&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-MX-Carbon/Images/adidas-Yeezy-Foam-RNR-MX-Carbon/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660202428&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe56.setImages(images);
        insertShoes(shoe56);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe57 = new Shoes();
        shoe57.setProductName("adidas Yeezy Foam RNNR Sulfur");
        shoe57.setCategory(yeezyCategory);
        shoe57.setSizes(shoesize1);
        shoe57.setProductColor("SULFUR/SULFUR/SULFUR");
        shoe57.setProductDecription("Electrify your sneaker rotation with the adidas Yeezy Boost 350 V2 Static. This Yeezy 350 V2 comes with a grey and white upper and a white sole.");
        shoe57.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNNR-Sulfur/Images/adidas-Yeezy-Foam-RNNR-Sulfur/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1654151731&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNNR-Sulfur/Images/adidas-Yeezy-Foam-RNNR-Sulfur/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1654151731&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNNR-Sulfur/Images/adidas-Yeezy-Foam-RNNR-Sulfur/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1654151731&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe57.setImages(images);
        insertShoes(shoe57);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe58 = new Shoes();
        shoe58.setProductName("adidas Yeezy Boost 350 V2 Granite");
        shoe58.setCategory(yeezyCategory);
        shoe58.setSizes(shoesize1);
        shoe58.setProductColor("GRANITE/CORE BLACK/GRANITE");
        shoe58.setProductDecription("The adidas Yeezy Boost 350 V2 Granite boasts the iconic Yeezy silhouette, known for its low-top design and sleek, sock-like fit. ");
        shoe58.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Granite/Images/adidas-Yeezy-Boost-350-V2-Granite/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686774341&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Granite/Images/adidas-Yeezy-Boost-350-V2-Granite/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686774341&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Boost-350-V2-Granite/Images/adidas-Yeezy-Boost-350-V2-Granite/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1686774341&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe58.setImages(images);
        insertShoes(shoe58);
        
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe60 = new Shoes();
        shoe60.setProductName("adidas Yeezy Foam RNR Stone");
        shoe60.setCategory(yeezyCategory);
        shoe60.setSizes(shoesize1);
        shoe60.setProductColor("STONE SALT/STONE SALT/STONE SALT");
        shoe60.setProductDecription("The adidas Yeezy Foam RNR Stone Salt takes on the slip-on silhouette with a low-top design, emphasizing comfort and ease of wear.");
        shoe60.setProductPrice(422L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-Stone-Salt/Images/adidas-Yeezy-Foam-RNR-Stone-Salt/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692109352&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-Stone-Salt/Images/adidas-Yeezy-Foam-RNR-Stone-Salt/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692109352&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Foam-RNR-Stone-Salt/Images/adidas-Yeezy-Foam-RNR-Stone-Salt/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1692109352&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe60.setImages(images);
        insertShoes(shoe60);
        
//        shoesize1 = new ArrayList<>();
//        i = 7;
//        while (i <= 12.5) {
//            shoesize1.add(new ShoeSize(i));
//            i += 0.5;
//        }
//        Shoes shoe53 = new Shoes();
//        shoe53.setProductName("");
//        shoe53.setCategory(yeezyCategory);
//        shoe53.setSizes(shoesize1);
//        shoe53.setProductColor("");
//        shoe53.setProductDecription("");
//        shoe53.setProductPrice(422L);
//        image1 = new sImage("");
//        image2 = new sImage("");
//        image3 = new sImage("");
//        images = new ArrayList<>();
//        images.add(image1);
//        images.add(image2);
//        images.add(image3);
//        shoe53.setImages(images);
//        insertShoes(shoe53);
        
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PromotionCode code1 = null;
        try {
            code1 = new PromotionCode("STOCKANDSTOCK1", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("STOCKANDSTOCK2", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("STOCKANDSTOCK3", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("STOCKANDSTOCK4", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("NULL", 0, dateFormat.parse("1900-01-01"), dateFormat.parse("3000-12-31"));
            insert(code1);
        } catch (ParseException ex) {
            Logger.getLogger(PopulateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            List<LineItem> items = new ArrayList<>();
            LineItem item = new LineItem(shoe1, 1, 7.5, shoe1.getProductPrice());
            items.add(item);
            item = new LineItem(shoe3, 1, 7.5, shoe3.getProductPrice());
            items.add(item);
            item = new LineItem(shoe5, 2, 7.5, shoe5.getProductPrice());
            items.add(item);
            item = new LineItem(shoe7, 3, 7.5, shoe7.getProductPrice());
            items.add(item);
            ShippingInfo address = new ShippingInfo("Quy", "Bu", "0902030405", "20 Pho Quang", "VietNam", "3333");
//            PromotionCode code1 = new PromotionCode("ABC123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            Invoice invoice;
            invoice = new Invoice(user1, items, address, code1, "Card", dateFormat.parse("2023-01-01"), 1090, code1.getPromotionAmount(), 1070);
            insert(invoice);

        } catch (ParseException ex) {
            Logger.getLogger(PopulateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            List<LineItem> items = new ArrayList<>();
            LineItem item = new LineItem(shoe1, 1, 7.5, shoe1.getProductPrice());
            items.add(item);
            item = new LineItem(shoe21, 1, 7.5, shoe21.getProductPrice());
            items.add(item);
            item = new LineItem(shoe20, 2, 7.5, shoe20.getProductPrice());
            items.add(item);
            item = new LineItem(shoe6, 3, 7.5, shoe6.getProductPrice());
            items.add(item);
            ShippingInfo address = new ShippingInfo("Quy", "Phu", "0902030405", "64 Pho Quang", "VietNam", "3333");
//            PromotionCode code1 = new PromotionCode("ABC123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            Invoice invoice;
            invoice = new Invoice(user1, items, address, code1, "Card", dateFormat.parse("2023-01-01"), 1090, code1.getPromotionAmount(), 1070);
            insert(invoice);

        } catch (ParseException ex) {
            Logger.getLogger(PopulateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
