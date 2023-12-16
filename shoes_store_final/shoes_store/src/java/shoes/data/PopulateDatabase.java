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
import shoes.business.AddressInfo;
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
        props.put(JDBC_PASSWORD, "abc123");
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

        CategoryShoes asicsCategory = new CategoryShoes();
        asicsCategory.setCategoryName("Asics");
        asicsCategory.setCategoryID(53);

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
        insertCategory(asicsCategory);
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
        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe13 = new Shoes();
        shoe13.setProductName("adidas Yeezy Slide Onyx (2022/2023)");
        shoe13.setCategory(adidasCategory);
//        shoe13.setProductSize(7);
        shoe13.setSizes(shoesize1);
        shoe13.setProductColor("BLACK/WHITE");
        shoe13.setProductDecription("First revealed in February 2022 at the Donda 2 listening event in Miami, the adidas Yeezy Slide Onyx features an all-black foam construction with a soft footbed for comfort.");
        shoe13.setProductPrice(108L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Black/Images/adidas-Yeezy-Slide-Black/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1646687558&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Black/Images/adidas-Yeezy-Slide-Black/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1646687558&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Black/Images/adidas-Yeezy-Slide-Black/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1646687558&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe13.setImages(images);
        insertShoes(shoe13);

        shoesize1 = new ArrayList<>();
        i = 7;
        while (i <= 12.5) {
            shoesize1.add(new ShoeSize(i));
            i += 0.5;
        }
        Shoes shoe14 = new Shoes();
        shoe14.setProductName("adidas Yeezy Slide Bone (2022/2023)");
        shoe14.setCategory(adidasCategory);
//        shoe14.setProductSize(7.5);
        shoe14.setSizes(shoesize1);
        shoe14.setProductColor("BONE/BONE/BONE");
        shoe14.setProductDecription("The adidas Yeezy Slide Bone (Restock Pair) features a slightly different composition than the original adidas Yeezy Slide Bone that debuted in the fall of 2019.");
        shoe14.setProductPrice(150L);
        image1 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Bone-2022/Images/adidas-Yeezy-Slide-Bone-2022/Lv2/img01.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660144762&h=384&q=57");
        image2 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Bone-2022/Images/adidas-Yeezy-Slide-Bone-2022/Lv2/img19.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660144762&h=384&q=57");
        image3 = new sImage("https://images.stockx.com/360/adidas-Yeezy-Slide-Bone-2022/Images/adidas-Yeezy-Slide-Bone-2022/Lv2/img28.jpg?fm=webp&auto=compress&w=576&dpr=1&updated_at=1660144762&h=384&q=57");
        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        shoe14.setImages(images);
        insertShoes(shoe14);

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
        shoe24.setProductName("adidas adiFOM Superstar");
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

//        Shoes shoe13 = new Shoes();
//        shoe13.setProductName("");
//        shoe13.setCategory(airJordanCategory);
//        shoe13.setProductSize(9);
//        shoe13.setProductColor("");
//        shoe13.setProductDecription("");
//        shoe13.setProductPrice(271L);
//        image1 = new sImage("");
//        image2 = new sImage("");
//        image3 = new sImage("");
//        images = new ArrayList<>();
//        images.add(image1);
//        images.add(image2);
//        images.add(image3);
//        shoe13.setImages(images);
//        insertShoes(shoe13);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PromotionCode code1 = null;
        try {
            code1 = new PromotionCode("ABC123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("VIP123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("NAP123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
            code1 = new PromotionCode("FIRST123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            insert(code1);
        } catch (ParseException ex) {
            Logger.getLogger(PopulateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            List<LineItem> items = new ArrayList<>();
            LineItem item = new LineItem(shoe1, 1, shoe1.getProductPrice());
            items.add(item);
            item = new LineItem(shoe3, 1, shoe3.getProductPrice());
            items.add(item);
            item = new LineItem(shoe5, 2, shoe5.getProductPrice());
            items.add(item);
            item = new LineItem(shoe7, 3, shoe7.getProductPrice());
            items.add(item);
            AddressInfo address = new AddressInfo("DK02", "Duong so 7", "Thu Duc", "HCM", "VietNam", "3333");
//            PromotionCode code1 = new PromotionCode("ABC123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            Invoice invoice;
            invoice = new Invoice(user1, items, address, code1, "Card", dateFormat.parse("2023-01-01"), 1090, code1.getPromotionAmount(), 1070);
            insert(invoice);

        } catch (ParseException ex) {
            Logger.getLogger(PopulateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            List<LineItem> items = new ArrayList<>();
            LineItem item = new LineItem(shoe1, 1, shoe1.getProductPrice());
            items.add(item);
            item = new LineItem(shoe21, 1, shoe21.getProductPrice());
            items.add(item);
            item = new LineItem(shoe20, 2, shoe20.getProductPrice());
            items.add(item);
            item = new LineItem(shoe6, 3, shoe6.getProductPrice());
            items.add(item);
            AddressInfo address = new AddressInfo("DK02", "Duong so 7", "Thu Duc", "HCM", "VietNam", "3333");
//            PromotionCode code1 = new PromotionCode("ABC123", 20.0, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-12-31"));
            Invoice invoice;
            invoice = new Invoice(user1, items, address, code1, "Card", dateFormat.parse("2023-01-01"), 1090, code1.getPromotionAmount(), 1070);
            insert(invoice);

        } catch (ParseException ex) {
            Logger.getLogger(PopulateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
