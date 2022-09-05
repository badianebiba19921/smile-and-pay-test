package com.smile.and.pay.ebb.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.and.pay.ebb.SmileAndPayTestApplicationTests;
import com.smile.and.pay.ebb.models.Marchand;
import com.smile.and.pay.ebb.models.Product;
import com.smile.and.pay.ebb.repository.MarchandRepository;
import com.smile.and.pay.ebb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SmileAndPayRest.class)
public class TestRestController extends SmileAndPayTestApplicationTests {

    //@Autowired
    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    MarchandRepository marchandRepository;

    @MockBean
    ProductRepository productRepository;

    Marchand MARCHAND_1 = new Marchand(1, new Date(), "Dupont", "Moretti", new Date(193833733783L), null, null);
    Marchand MARCHAND_2 = new Marchand(2, new Date(), "Pierre", "Paoli", new Date(1215226200),null, null);

    Product PRODUCT_1 = new Product(null, new Date(), "Computer", 700.13, "EUR", 1200.35, 45.50, null);
    Product PRODUCT_2 = new Product(null, new Date(), "Smartphone", 1200.00, "EUR", 250.25, 15.56, null);

    @Test
    public void test_get_all_marchands_success() throws Exception {
        List<Marchand> records = new ArrayList<>(Arrays.asList(MARCHAND_1, MARCHAND_2));
        System.out.println("test_get_all_marchands_success => " + marchandRepository);
        Mockito.when(marchandRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/marchands")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("Pierre")));
    }

    @Test
    public void test_get_all_products_success() throws Exception {
        List<Product> records = new ArrayList<>(Arrays.asList(PRODUCT_1, PRODUCT_2));
        System.out.println("test_get_all_products_success => " + productRepository);
        Mockito.when(productRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].label", is("Smartphone")));
    }

    @Test
    public void test_get_marchand_by_id_success() throws Exception {
        Mockito.when(marchandRepository.findById(MARCHAND_2.getId())).thenReturn(java.util.Optional.of(MARCHAND_2));
        System.out.println("test_get_marchand_by_id_success => " + marchandRepository);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Pierre")));
    }

    @Test
    public void test_get_product_by_id_success() throws Exception {
        Mockito.when(marchandRepository.findById(MARCHAND_2.getId())).thenReturn(java.util.Optional.of(MARCHAND_2));
        System.out.println("test_get_marchand_by_id_success => " + marchandRepository);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Pierre")));
    }

    @Test
    public void test_update_marchand_success() throws Exception {
        Marchand updatedMarchand = Marchand.builder()
                .id(1)
                .create_date(new Date())
                .name("Rayven")
                .lastname("Zambo")
                .birthdate(new Date(193833733783L))
                .build();
        System.out.println("test_update_marchand_success => " + marchandRepository);
        Mockito.when(marchandRepository.findById(MARCHAND_1.getId())).thenReturn(Optional.of(MARCHAND_1));
        Mockito.when(marchandRepository.save(updatedMarchand)).thenReturn(updatedMarchand);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/marchands/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedMarchand));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Rayven")))
                .andExpect(jsonPath("$.lastname", is("Zambo")));
    }

    @Test
    public void test_update_product_success() throws Exception {
        Product updatedProduct = Product.builder()
                .id(2)
                .create_date(new Date())
                .label("Smartphone")
                .unit_price(2340.04)
                .currency("USD")
                .weight(540.05)
                .height(546.6)
                .build();
        System.out.println("test_update_product_success => " + productRepository);
        Mockito.when(productRepository.findById(PRODUCT_1.getId())).thenReturn(Optional.of(PRODUCT_1));
        Mockito.when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/marchands/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedProduct));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.unit_price", is(2340.04)))
                .andExpect(jsonPath("$.height", is(546.6)));
    }
}
