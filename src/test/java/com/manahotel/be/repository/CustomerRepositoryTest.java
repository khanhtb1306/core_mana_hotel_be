//package com.manahotel.be.repository;
//
//import com.manahotel.be.common.util.IdGenerator;
//import com.manahotel.be.model.entity.Customer;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class CustomerRepositoryTest {
//
//    @Autowired
//    private CustomerRepository underTest;
//
//    @Test
//    void findTopByOrderByCustomerIdDesc() {
//        Customer customer = new Customer();
//        customer.setCustomerId("C000001");
//        underTest.save(customer);
//        customer.setCustomerId("C000002");
//        underTest.save(customer);
//
//        Customer latestCutomer= underTest.findTopByOrderByCustomerIdDesc();
//        Assertions.assertEquals(latestCutomer,customer);
//    }
//
//    IdGenerator idGenerator;
//
//    @Test
//    void generateIdIfLastCustomerIsNull(){
//         String result = idGenerator.generateId(null,"C");
//         String expected = "C000001";
//
//        Assertions.assertEquals(expected,result);
//    }
//
//    @Test
//    void generateIdIfLastCustomerIsNotNull(){
//        String result = idGenerator.generateId("C000002","C");
//        String expected = "C000003";
//
//        Assertions.assertEquals(expected,result);
//    }
//
//}