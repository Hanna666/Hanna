package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.MetadataSourceType;
import org.hibernate.mapping.MetadataSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class HbConnectionTests {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        // A SessionFactory is set up once for an application
        try {
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e){
        e.printStackTrace();
        StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testHbConnectionTests(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <GroupData> result = session.createQuery( "from GroupData where deprecated = '0000-00-00'" ).list();
        for ( GroupData group : result ) {
            System.out.println( group) ;
        }
        session.getTransaction().commit();
        session.close();

    }

}
