import config.FactoryConfiguration;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * --------------------------------------------
 * @Author Dimantha Kaveen
 * @GitHub: https://github.com/KaveenDK
 * @Date 2/21/2025
 * @Project Hibernate-Lifecycle
 * --------------------------------------------
 **/

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // ** 1. Transient State **
        // no session
        // no db
        Customer customer = new Customer();
        customer.setName("Shehan Tharindu");


        // ** 2. Persistent State **
        // yes session
        //  new object no db
        //  get(customer.class, 1) yes db
        // session.save(customer);
        // yes changes track
        session.persist(customer); // save the object to the database
        System.out.println("Persistent : " + customer.getId() + " " + customer.getName());

        transaction.commit();
        // new object yes db


        // ** 3. Detached State **
        // no session
        // yes db
        session.close();
        System.out.println("Detached : " + customer.getId() + " " + customer.getName());

        // modify detached entity (customer object)
        customer.setName("Tharindu Shehan");

        // get a new session
        session = FactoryConfiguration.getInstance().getSession();
        // Begin a new transaction
        transaction = session.beginTransaction();


        // ** 4. Reattaching to Persistent State **
        // session.update(customer);
        // session.save(Customer);
        Customer mergedCustomer = session.merge(customer); // update the object in the database
        transaction.commit();
        System.out.println("Merged : " + mergedCustomer.getName());

        session.close();

        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        // ** 5. Removed State **
        // yes session
        // yes db but commit no db
        // session.delete(mergedCustomer);
        session.remove(mergedCustomer);
        transaction.commit();

        session.close();

        // mergedCustomer eligible for garbage collection (GC)
        System.out.println("Removed : " + mergedCustomer.getName());
    }
}
