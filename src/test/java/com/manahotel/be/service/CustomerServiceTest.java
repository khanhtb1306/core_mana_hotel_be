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

    @Test
    void createHaveNotNullCustomerNameIsSucessfull() throws IOException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("huy");
      customerDTO.setDob("1970-01-01 08:00:00");
        ResponseEntity<String> actual = underTest.create(customerDTO);
        ResponseEntity<String> expect = new ResponseEntity<>("Tạo thông tin khách hàng thành công ", HttpStatus.OK);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    void createNullCustomerName() throws IOException {
        CustomerDTO customerDTO = new CustomerDTO();

        ResponseEntity<String> actual = underTest.create(customerDTO);
        ResponseEntity<String> expect = new ResponseEntity<>("Tên khách hàng bị trống", HttpStatus.INTERNAL_SERVER_ERROR);
        ;
        Assertions.assertEquals(expect, actual);
    }

    @Test
    void updateHaveNotNullCustomerNameIsSucessfull() throws IOException {

        String id = "000001"; // Assuming an existing floor with this ID

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("huy");

        Customer exists = new Customer();
        exists.setCustomerId((String) id);

        Mockito.when(customerRepository.findById((String) id)).thenReturn(Optional.of(exists));
        underTest.update(id, customerDTO);

//        verify(customerRepository).save(customerArgumentCaptor.capture());
//        Customer c = customerArgumentCaptor.getValue();
//        exists.setCustomerName("huy");
//        assertThat(c).isEqualTo(exists);

    }

    @Test
    void updateNullCustomerName() throws IOException {

        String id = "000001"; // Assuming an existing floor with this ID

        CustomerDTO customerDTO = new CustomerDTO();

        Customer exists = new Customer();
        exists.setCustomerId((String) id);
        exists.setCustomerName("huy");

        ResponseEntity<String> actual = underTest.update(id, customerDTO);
        ResponseEntity<String> expect = new ResponseEntity<>("Tên khách hàng bị trống", HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(expect, actual);

    }

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