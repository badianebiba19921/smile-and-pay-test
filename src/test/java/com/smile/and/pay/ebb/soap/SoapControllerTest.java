package com.smile.and.pay.ebb.soap;

import com.smile.and.pay.ebb.models.Marchand;
import com.smile.and.pay.ebb.models.Product;
import com.smile.and.pay.ebb.models.SmileAndPayResponse;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoapControllerTest {

    @Test
    public void test_add_marchand() throws Exception {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SmileAndPayService.class);
        factory.setAddress("http://localhost:8080/soap-api/SmileAndPayService_1.0?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());

        SmileAndPayService ws = (SmileAndPayService) factory.create();
        Marchand marchand = new Marchand(null, new Date(), "Dupont", "Moretti", new Date(193833733783L), null, null);
        SmileAndPayResponse response = ws.addMarchand(marchand);
        System.out.println("Test adding a merchant => " + response);
        assertEquals(HttpStatus.CREATED.value(), response.getCodeRetour());
    }

    @Test
    public void test_add_product() throws Exception {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SmileAndPayService.class);
        factory.setAddress("http://localhost:8080/soap-api/SmileAndPayService_1.0?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());

        SmileAndPayService ws = (SmileAndPayService) factory.create();
        Marchand marchand = new Marchand(null, new Date(), "Dupont", "Moretti", new Date(193833733783L), null, null);
        Product product = new Product(null, new Date(), "Computer", 700.13, "EUR", 1200.35, 45.50, Collections.singletonList(marchand));
        SmileAndPayResponse response = ws.addProduct(product);
        System.out.println("Test adding a product => " + response);
        assertEquals(HttpStatus.CREATED.value(), response.getCodeRetour());
    }

    @Test
    public void test_delete_marchand() throws Exception {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SmileAndPayService.class);
        factory.setAddress("http://localhost:8080/soap-api/SmileAndPayService_1.0?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());

        SmileAndPayService ws = (SmileAndPayService) factory.create();
        SmileAndPayResponse response = ws.deleteMarchandById(1);
        System.out.println("Test deleting a marchand => " + response);
        assertEquals(HttpStatus.OK.value(), response.getCodeRetour());
    }

    @Test
    public void test_delete_product() throws Exception {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SmileAndPayService.class);
        factory.setAddress("http://localhost:8080/soap-api/SmileAndPayService_1.0?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());

        SmileAndPayService ws = (SmileAndPayService) factory.create();
        SmileAndPayResponse response = ws.deleteProductById(5);
        System.out.println("Test deleting a product => " + response);
        assertEquals(HttpStatus.OK.value(), response.getCodeRetour());
    }
}
