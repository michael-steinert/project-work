package com.bike_factory.customermanagement.service;

import com.bike_factory.customermanagement.dao.CustomerDao;
import com.bike_factory.customermanagement.model.Customer;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerDao);
    }

    @Test
    void shouldGetAllCustomers() {
        UUID customerUid = UUID.randomUUID();
        Customer customer = new Customer(customerUid, "Michael", "Steinert", Customer.Gender.MALE, "steinert-michael@example.org", "Fuchskaute", "4", 58730, "Fröndenberg/ Ruhr");
        ImmutableList<Customer> orderImmutableList = new ImmutableList.Builder<Customer>().add(customer).build();

        given(customerDao.selectAllCustomers()).willReturn(orderImmutableList);

        Optional<List<Customer>> allOptionalCustomers = customerService.getAllCustomers();
        List<Customer> allCustomers = allOptionalCustomers.get();
        Customer customerFromService = allCustomers.get(0);

        assertThat(allOptionalCustomers.isPresent()).isTrue();
        assertThat(allCustomers).hasSize(1);
        assertThat(customerFromService.getCustomerUid()).isNotNull();
        assertThat(customerFromService.getCustomerUid()).isEqualTo(customerUid);
        assertThat(customerFromService.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(customerFromService.getFirstName()).isEqualTo("Michael");
        assertThat(customerFromService.getLastName()).isEqualTo("Steinert");
        assertThat(customerFromService.getGender()).isEqualTo(Customer.Gender.MALE);
        assertThat(customerFromService.getEmail()).isEqualTo("steinert-michael@example.org");
        assertThat(customerFromService.getStreet()).isEqualTo("Fuchskaute");
        assertThat(customerFromService.getHouseNumber()).isEqualTo("4");
        assertThat(customerFromService.getZipCode()).isEqualTo(58730);
        assertThat(customerFromService.getCity()).isEqualTo("Fröndenberg/ Ruhr");
    }

    @Test
    void shouldGetCustomer() {
        UUID customerUid = UUID.randomUUID();
        Customer customer = new Customer(customerUid, "Michael", "Steinert", Customer.Gender.MALE, "steinert-michael@example.org", "Fuchskaute", "4", 58730, "Fröndenberg/ Ruhr");

        given(customerDao.selectCustomerByCustomerUid(customerUid)).willReturn(customer);

        Optional<Customer> optionalCustomerFromService = customerService.getCustomer(customerUid);
        Customer customerFromService = optionalCustomerFromService.get();

        assertThat(optionalCustomerFromService.isPresent()).isTrue();
        assertThat(customerFromService.getCustomerUid()).isNotNull();
        assertThat(customerFromService.getCustomerUid()).isEqualTo(customerUid);
        assertThat(customerFromService.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(customerFromService.getFirstName()).isEqualTo("Michael");
        assertThat(customerFromService.getLastName()).isEqualTo("Steinert");
        assertThat(customerFromService.getGender()).isEqualTo(Customer.Gender.MALE);
        assertThat(customerFromService.getEmail()).isEqualTo("steinert-michael@example.org");
        assertThat(customerFromService.getStreet()).isEqualTo("Fuchskaute");
        assertThat(customerFromService.getHouseNumber()).isEqualTo("4");
        assertThat(customerFromService.getZipCode()).isEqualTo(58730);
        assertThat(customerFromService.getCity()).isEqualTo("Fröndenberg/ Ruhr");
    }

    @Test
    void shouldInsertCustomer() {
        UUID customerUid = UUID.randomUUID();
        Customer customer = new Customer(customerUid, "Michael", "Steinert", Customer.Gender.MALE, "steinert-michael@example.org", "Fuchskaute", "4", 58730, "Fröndenberg/ Ruhr");
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);

        given(customerDao.insertCustomer(any(Customer.class))).willReturn(1);

        int insertResult = customerService.insertCustomer(customer);
        verify(customerDao).insertCustomer(captor.capture());
        Customer orderFromCaptor = captor.getValue();

        assertThat(orderFromCaptor.getCustomerUid()).isNotNull();
        assertThat(orderFromCaptor.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(orderFromCaptor.getFirstName()).isEqualTo("Michael");
        assertThat(orderFromCaptor.getLastName()).isEqualTo("Steinert");
        assertThat(orderFromCaptor.getGender()).isEqualTo(Customer.Gender.MALE);
        assertThat(orderFromCaptor.getEmail()).isEqualTo("steinert-michael@example.org");
        assertThat(orderFromCaptor.getStreet()).isEqualTo("Fuchskaute");
        assertThat(orderFromCaptor.getHouseNumber()).isEqualTo("4");
        assertThat(orderFromCaptor.getZipCode()).isEqualTo(58730);
        assertThat(orderFromCaptor.getCity()).isEqualTo("Fröndenberg/ Ruhr");
    }
}
