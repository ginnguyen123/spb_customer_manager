package com.service.customer;

import com.model.Customer;
import com.model.dto.customer.CustomerResponseDTO;
import com.service.IGeneralService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends IGeneralService<Customer> {

    List<CustomerResponseDTO> getAllExistCustomer();

    List<CustomerResponseDTO> getAllDeletedCustomer();

    Boolean existsByEmailEquals(String email);

    Optional<Customer> findCustomerByIdAndDeletedIsTrue(Long id);

    Optional<Customer> findCustomerByIdAndDeletedIsFalse(Long id);

    List<CustomerResponseDTO> searchCustomer(String keyWord);

    Customer saveWithAvatar(Customer customer, MultipartFile file);
}
