package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.CustomerDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    @Mock
    private CustomerGroupService customerGroupService;
    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository,customerGroupService);
    }

    @Test
    void createIsSuccessful() throws IOException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("huy");
        customerDTO.setDob("1970-01-01 08:00:00");
        customerDTO.setIdentity("071097723");
        customerDTO.setPhoneNumber("0889967541");

        // Assuming underTest is an instance of the class containing the create method
        ResponseEntity<Map<String, String>> actual = underTest.create(customerDTO);

        // Extracting the expected customer ID from the actual response
        String expectedCustomerId = actual.getBody().get("customerId");

        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Tạo thông tin khách hàng thành công ");
        expectedResponse.put("customerId", expectedCustomerId);

        ResponseEntity<Map<String, String>> expected = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testCreateCustomerException() throws IOException {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("New Name");

        Mockito.when(customerRepository.findTopByOrderByCustomerIdDesc()).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<Map<String, String>> response = underTest.create(customerDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Tạo thông tin khách hàng thất bại", response.getBody().get("message"));
    }
    @Test
    public void testUpdateCustomerSuccess() throws IOException {
        // Arrange
        String customerId = "123";
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("New Name");
        customerDTO.setIdentity("071097724");
        customerDTO.setPhoneNumber("0889967542");
        customerDTO.setDob("1970-01-01 08:00:00");

        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setCustomerName("Old Name");
        existingCustomer.setIdentity("071097724");
        existingCustomer.setPhoneNumber("0889967542");
        customerDTO.setDob("1970-01-02 08:00:00");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        ResponseEntity<Map<String, String>> response = underTest.update(customerId, customerDTO);

        // Assert
        Mockito.verify(customerRepository).findById(customerId);
//        Mockito.verify(customerRepository).save(Mockito.any(Customer.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cập nhật thông tin khách hàng thành công ", response.getBody().get("message"));
    }

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

    @Test
    public void testUpdateCustomerException() throws IOException {
        // Arrange
        String customerId = "123";
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("New Name");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Map<String, String>> response = underTest.update(customerId, customerDTO);

        // Assert
        Mockito.verify(customerRepository).findById(customerId);
//        Mockito.verify(customerRepository, Mockito.never()).save(Mockito.any(Customer.class));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Cập nhật thông tin khách hàng  thất bại", response.getBody().get("message"));
    }
    @Test
    void delete() {
        String id = "000001"; // Assuming an existing customer with this ID

        // Mocking the existence check
        Mockito.when(customerRepository.existsById(id)).thenReturn(true);

        // Mocking the customer retrieval
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerId(id);
        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));

        // Calling the delete method
        ResponseEntity<String> response = underTest.delete(id);

        // Verifying the interactions
        Mockito.verify(customerRepository).existsById(id);
        Mockito.verify(customerRepository).findById(id);

        // Update: Check for the save method instead of deleteById
        Mockito.verify(customerRepository).save(existingCustomer);

        // Update: Remove the unnecessary deleteById verification
        // Mockito.verify(customerRepository).deleteById(id);

        // Assertions
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