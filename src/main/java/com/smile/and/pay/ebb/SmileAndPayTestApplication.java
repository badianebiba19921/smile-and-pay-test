package com.smile.and.pay.ebb;

import com.smile.and.pay.ebb.models.Address;
import com.smile.and.pay.ebb.models.Marchand;
import com.smile.and.pay.ebb.models.Product;
import com.smile.and.pay.ebb.repository.AddressRepository;
import com.smile.and.pay.ebb.repository.MarchandRepository;
import com.smile.and.pay.ebb.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class SmileAndPayTestApplication implements CommandLineRunner {

    @Autowired
    private Environment env;

    @Autowired
    MarchandRepository marchandRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmileAndPayTestApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {
        Marchand marchand = marchandRepository.save(new Marchand(null, new Date(), "Jules", "Dupont", new Date(1220227200), null, null));
        Marchand marchand1 = marchandRepository.save(new Marchand(null, new Date(), "Pierre", "Paoli", new Date(1215226200),null, null));
        addressRepository.save(new Address(null, 1,"Av Grabriel Péri","75008", marchand));
        addressRepository.save(new Address(null, 20,"Rue François Coppée","92260", marchand1));
        productRepository.save(new Product(null, new Date(), "Computer", 700.13, "EUR", 1200.35, 45.50, Collections.singletonList(marchand)));
        productRepository.save(new Product(null, new Date(), "Smartphone", 1200.00, "EUR", 250.25, 15.56, Collections.singletonList(marchand1)));
    }
}
