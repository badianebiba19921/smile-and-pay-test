package com.smile.and.pay.ebb.config;

import com.smile.and.pay.ebb.soap.SmileAndPayService;
import com.smile.and.pay.ebb.soap.SmileAndPayServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfiguration {

    public static final String SERVICE_URL = "/SmileAndPayService_1.0";

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap-api/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public SmileAndPayService smileAndPayService() {
        return new SmileAndPayServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), smileAndPayService());
        endpoint.publish(SERVICE_URL);
        endpoint.setWsdlLocation("SmileAndPayService1.0.wsdl");
        return endpoint;
    }
}
