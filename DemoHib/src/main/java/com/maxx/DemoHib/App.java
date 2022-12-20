package com.maxx.DemoHib;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Alien maxx = new Alien();
//        maxx.setSid(102);
//        maxx.setsName("Cherry");
//        maxx.setClassNo("Class_I");
         
        Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
        
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        
        
        SessionFactory sf = con.buildSessionFactory(reg);
         
        Session session = sf.openSession();
        
        org.hibernate.Transaction tx = session.beginTransaction(); 
        
//        session.save(maxx);
        
        maxx = (Alien)session.get(Alien.class, 103); // it will return objet of objet so we typecast to class
        
        tx.commit();
        
       System.out.println(maxx);
        
    }
}
