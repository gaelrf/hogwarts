import entity.Person;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Query q;

        //Primer apartado
        String nombre = "Minerva";
        q = em.createQuery("select p from Person p join p.courses c where c.teacher.firstName = :name ");
        List<Person> list = q.setParameter("name", nombre).getResultList();
        for (Person z : list) {
            System.out.println("Zona:" + z.toString());
        }

        //Segundo Apartado

        TypedQuery<Person> query =
                em.createNamedQuery("Person.NombreConMaxPuntos", Person.class);
        List<Person> results = query.getResultList();
        for (Person p : results)
            System.out.println(p.getFirstName() + " " + p.getLastName());

        TypedQuery<Person> query1 =
                em.createNamedQuery("Person.NombreDaMasPuntos", Person.class);
        List<Person> results1 = query1.getResultList();
        for (Person p : results1)
            System.out.println(p.getFirstName() + " " + p.getLastName());

        //Tercer Apartado
        List f=em.createNativeQuery("select p.first_name, p.last_name,h.name from person as p inner join house as h on p.house_id = h.id")
                .unwrap(SQLQuery.class)
                .setResultTransformer(
                        AliasToEntityMapResultTransformer.INSTANCE
                )
                .list();
        for(int i=0; i<f.size();i++){
         System.out.println(f.get(i).toString());
         }


    }
}
