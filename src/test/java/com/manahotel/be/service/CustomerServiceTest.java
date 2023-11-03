package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.FloorDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService underTest;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

//    @Test
//    void createIsSucessfull() throws IOException {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setCustomerName("huy");
//        customerDTO.setDob("1970-01-01 08:00:00");
//        ResponseEntity<String> actual = underTest.create(customerDTO);
//        ResponseEntity<String> expect = new ResponseEntity<>("Tạo thông tin khách hàng thành công ", HttpStatus.OK);
//        Assertions.assertEquals(expect, actual);
//    }
//    @Test
//    public void testCreateCustomerException() throws IOException {
//        // Arrange
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setCustomerName("New Name");
//
//        Mockito.when(customerRepository.findTopByOrderByCustomerIdDesc()).thenThrow(new RuntimeException());
//
//        // Act
//        ResponseEntity<String> response = underTest.create(customerDTO);
//
//        // Assert
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals("Tạo thông tin khách hàng thất bại", response.getBody());
//    }
//    @Test
//    public void testUpdateCustomerSuccess() throws IOException {
//        // Arrange
//        String customerId = "123";
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setCustomerName("New Name");
//
//        Customer existingCustomer = new Customer();
//        existingCustomer.setCustomerId(customerId);
//        existingCustomer.setCustomerName("Old Name");
//
//        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
//        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // Act
//        ResponseEntity<String> response = underTest.update(customerId, customerDTO);
//
//        // Assert
//        Mockito.verify(customerRepository).findById(customerId);
//        Mockito.verify(customerRepository).save(Mockito.any(Customer.class));
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Cập nhật thông tin khách hàng thành công ", response.getBody());
//    }

    @Test
    void updateIsSucessfull() throws IOException {

        String customerId = "123";

        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerName("Old Name");
        existingCustomer.setGender(true);

        CustomerDTO updatedCustomer = new CustomerDTO();
        updatedCustomer.setCustomerName("New Name");
        existingCustomer.setGender(false);

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        underTest.update(customerId, updatedCustomer);
        assertEquals(updatedCustomer.isGender(), existingCustomer.getGender());

    }

//    @Test
//    public void testUpdateCustomerException() throws IOException {
//        // Arrange
//        String customerId = "123";
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setCustomerName("New Name");
//
//        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
//
//        // Act
//        ResponseEntity<String> response = underTest.update(customerId, customerDTO);
//
//        // Assert
//        Mockito.verify(customerRepository).findById(customerId);
//        Mockito.verify(customerRepository, Mockito.never()).save(Mockito.any(Customer.class));
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals("Cập nhật thông tin khách hàng  thất bại", response.getBody());
//    }
    @Test
    void delete() {
        String id = "000001"; // Assuming an existing floor with this ID

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("huy");

        Customer exists = new Customer();
        exists.setCustomerId((String) id);
        Mockito.when(customerRepository.existsById(id)).thenReturn(true);

        ResponseEntity<String> response = underTest.delete(id);

        Mockito.verify(customerRepository).deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Xóa thông tin khách hàng thành công ", response.getBody());

    }
    @Test
    public void testDeleteCustomerNoIdException() {
        // Arrange
        String customerId = null;

        // Act and Assert
        ResponseEntity<String> response = underTest.delete(customerId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("khách hàng có id là " + customerId + " không tồn tại!", response.getBody());
    }
    @Test
    void getById() {
        String customerId = "000001";

        Customer customer = new Customer();
        Mockito.when(customerRepository.existsById(customerId)).thenReturn(true);
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = underTest.getById(customerId);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }
}