import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    public static void main(String[] args) {
        System.out.println('a');
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Query q;
        q = em.createNamedQuery("Person.NombreConMaxPuntos",
                query="SELECT p.firstName, p.lastName\n" +
                        "FROM Person AS p\n" +
                        "WHERE p.id = (SELECT personByReceiver\n" +
                        "FROM HousePoints\n" +
                        "GROUP BY personByReceiver\n" +
                        "HAVING SUM(points) >= ALL (SELECT \n" +
                        "SUM(points)\n" +
                        "FROM HousePoints\n" +
                        "GROUP BY personByReceiver))");
        q.executeUpdate();

    }
}
