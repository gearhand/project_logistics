package igor.project;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Main
{
    public static void main( String[] args ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Type type = new Type("liquid");
        session.saveOrUpdate(type);
        Query query = session.createQuery("FROM Type WHERE type_id = 10");
        java.util.List list = query.list();
        Type output = (Type) list.get(0);
        System.out.println(output.getName());

        session.getTransaction().commit();
        session.close();{
        }
    }
}
