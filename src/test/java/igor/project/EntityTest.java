package igor.project;

import igor.project.entities.*;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class EntityTest extends TestCase {
    public void testApp() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        /*
        session.beginTransaction();
        Type container = new Type("container");
        Type bulkSolid = new Type("bulk solid");
        Type bulkLiquid = new Type("bulk liquid");
        Product heavyMach = new Product("heavy machinery", container);
        Product fert = new Product("fertilizer", bulkSolid);
        Product oil = new Product("oil", bulkLiquid);
        Company helm = new Company("Helm A.G.", "Germany");
        Company bp = new Company("British Petrolium", "Great Britain");
        Port hamburg = new Port("Hamburg", "Germany");
        Port rotterdam = new Port("Rotterdam", "Netherlands");
        List<Capacity> hamburgCap = new ArrayList<Capacity>();
        hamburgCap.add(new Capacity(bulkLiquid, hamburg, 100000));
        hamburgCap.add(new Capacity(bulkSolid, hamburg, 200000));
        hamburgCap.add(new Capacity(container,hamburg,15000));
        List<Shipment> shipments = new ArrayList<Shipment>();
        shipments.add(new Shipment(helm, fert, hamburg, 50000));
        shipments.add(new Shipment(bp, oil, hamburg, 20000));

        session.saveOrUpdate(container);
        session.saveOrUpdate(bulkSolid);
        session.saveOrUpdate(bulkLiquid);
        session.saveOrUpdate(fert);
        session.saveOrUpdate(heavyMach);
        session.saveOrUpdate(oil);
        session.saveOrUpdate(helm);
        session.saveOrUpdate(bp);
        session.saveOrUpdate(hamburg);
        session.saveOrUpdate(rotterdam);
        for (Capacity cap : hamburgCap) {
            session.saveOrUpdate(cap);
        }
        for(Shipment shipm : shipments) {
            session.saveOrUpdate(shipm);
        }
        session.getTransaction().commit();
        */

        Query query = session.createQuery("select name from Product");
        java.util.List list = query.list();
        for (Object s : list) {
            System.out.println(s);
        }
        HibernateUtil.shutdown();
    }
}

