package igor.project;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class TypeTest extends TestCase {
    public void testApp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Type type = new Type(50, "container");
        Type container = new Type("container");
        Product product1 = new Product("heavy_machinery", container);
        session.saveOrUpdate(container);
        session.saveOrUpdate(product1);
        session.getTransaction().commit();

        /*
        Query query = session.createQuery("FROM Type WHERE type_id = 50");
        java.util.List list = query.list();
        Type output = (Type) list.get(0);
        System.out.println(output.getName());
        */

        session.close();
    }
}

