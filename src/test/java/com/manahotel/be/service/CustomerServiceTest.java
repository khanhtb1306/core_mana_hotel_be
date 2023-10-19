//package com.manahotel.be.service;
//
//import com.manahotel.be.model.dto.CustomerDTO;
//import com.manahotel.be.model.entity.Customer;
//import com.manahotel.be.repository.CustomerRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//
//@DataJpaTest
//@ExtendWith(MockitoExtension.class)
//class CustomerServiceTest {
//
//    @Mock
//    private CustomerRepository customerRepository;
//    private CustomerService underTest;
//
//    @Captor
//    private ArgumentCaptor<Customer> customerArgumentCaptor;
//
//    @BeforeEach
//    void setUp() {
//        underTest = new CustomerService(customerRepository);
//    }
//
////    @AfterEach
////    void tearDown() {
////    }
//
//    @Test
//    void getAll() {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setGender(false);
//
////        underTest.create(customerDTO);
////
////        CustomerDTO customerDTO1 = new CustomerDTO();
////        customerDTO.setGender(false);
////        underTest.create(customerDTO1);
//
//
//
//
//    }
//
//    @Test
//    void create() {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setGender(false);
//
////        underTest.create(customerDTO);
//
//        Customer customer = new Customer();
//        customer.setCustomerId("C000001");
//        customer.setGender(false);
//
//
//        verify(customerRepository).save(customerArgumentCaptor.capture());
//        Customer c = customerArgumentCaptor.getValue();
//
//        assertThat(c).isEqualTo(customer);
//
//
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void getById() {
//    }
//}