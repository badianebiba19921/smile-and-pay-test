package com.smile.and.pay.ebb.soap;

import com.smile.and.pay.ebb.models.Address;
import com.smile.and.pay.ebb.models.Marchand;
import com.smile.and.pay.ebb.models.Product;
import com.smile.and.pay.ebb.models.SmileAndPayResponse;
import com.smile.and.pay.ebb.repository.AddressRepository;
import com.smile.and.pay.ebb.repository.MarchandRepository;
import com.smile.and.pay.ebb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@WebService(endpointInterface="com.smile.and.pay.ebb.soap.SmileAndPayService")
@Service
public class SmileAndPayServiceImpl implements SmileAndPayService {

    @Autowired
    MarchandRepository marchandRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    @Transactional
    public SmileAndPayResponse addMarchand(Marchand marchand) {
        System.out.println("## addMarchand ==> " + marchand);
        Marchand _marchand = new Marchand(null, marchand.getCreate_date(), marchand.getName(), marchand.getLastname(), marchand.getBirthdate(), null, null);
        Marchand __marchand = marchandRepository.save(_marchand);
        marchand.getAddresses()
                .forEach(address -> {
                    Address _address = new Address(null, address.getNumber(), address.getStreet(), address.getZip_code(), __marchand);
                    addressRepository.save(_address);
                });
        return new SmileAndPayResponse()
                .setCodeRetour(HttpStatus.CREATED.value())
                .setMessageRetour("Marchand created");
    }

    @Override
    @Transactional
    public SmileAndPayResponse deleteMarchandById(int marchandId) {
        Optional<Marchand> marchand = marchandRepository.findById(marchandId);
        if(marchand.isPresent()){
            marchandRepository.delete(marchand.get());
            return new SmileAndPayResponse()
                    .setCodeRetour(HttpStatus.OK.value())
                    .setMessageRetour("Marchand deleted");
        }
        return new SmileAndPayResponse()
                .setCodeRetour(HttpStatus.NO_CONTENT.value())
                .setMessageRetour("Marchand not found");
    }

    @Override
    @Transactional
    public SmileAndPayResponse addProduct(Product product) {
        System.out.println("## addProduct ==> " + product);
        Product _product = new Product(null, product.getCreate_date(), product.getLabel(), product.getUnit_price(), product.getCurrency(), product.getWeight(), product.getHeight(), null);
        Product __product = productRepository.save(_product);
        product.getMarchands()
                .forEach(marchand -> {
                    Marchand _marchand = new Marchand(null, marchand.getCreate_date(), marchand.getName(), marchand.getLastname(), marchand.getBirthdate(), null, Collections.singletonList(__product));
                    Marchand __marchand = marchandRepository.save(_marchand);
                    marchand.getAddresses()
                            .forEach(address -> {
                                Address _address = new Address(null, address.getNumber(), address.getStreet(), address.getZip_code(), __marchand);
                                addressRepository.save(_address);
                            });
                });
        return new SmileAndPayResponse()
                .setCodeRetour(HttpStatus.CREATED.value())
                .setMessageRetour("Product created");
    }

    @Override
    @Transactional
    public SmileAndPayResponse deleteProductById(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return new SmileAndPayResponse()
                    .setCodeRetour(HttpStatus.OK.value())
                    .setMessageRetour("Product deleted");
        }
        return new SmileAndPayResponse()
                .setCodeRetour(HttpStatus.NO_CONTENT.value())
                .setMessageRetour("Product not found");
    }

    @Override
    @Transactional
    public SmileAndPayResponse associate(int marchandId, int productId) {
        Optional<Marchand> marchand = marchandRepository.findById(marchandId);
        Optional<Product> product = productRepository.findById(productId);
        if(marchand.isPresent()) {
            boolean add = marchand.get().getProducts()
                    .stream().noneMatch(_product -> _product.getId() == productId);
            if(add) {
                marchand.get().getProducts().add(product.get());
                marchandRepository.save(marchand.get());
                return new SmileAndPayResponse()
                        .setCodeRetour(HttpStatus.OK.value())
                        .setMessageRetour("Association maded");
            }
        }
        return new SmileAndPayResponse()
                .setCodeRetour(HttpStatus.NO_CONTENT.value())
                .setMessageRetour("Marchand not found");
    }
}
