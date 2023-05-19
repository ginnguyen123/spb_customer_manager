package com.repository.jwt;

import com.model.Customer;
import com.model.dto.customer.CustomerResponseDTO;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> getAllByDeletedIsFalse();

    List<Customer> getAllByDeletedIsTrue();

    Boolean existsByEmailEquals(String email);

    Optional<Customer> findCustomerByIdAndDeletedIsTrue(Long id);

    Optional<Customer> findCustomerByIdAndDeletedIsFalse(Long id);

    @Query("SELECT NEW com.model.dto.customer.CustomerResponseDTO ( " +
            "cust.id, " +
            "cust.fullName, " +
            "cust.phone, cust.email, " +
            "cust.dateOfBirth, " +
            "cust.locationRegion, " +
            "cust.customerAvatar ) " +
            "FROM Customer AS cust " +
            "WHERE ( cust.fullName LIKE :keyWord " +
            "OR cust.email LIKE :keyWord " +
            "OR cust.phone LIKE :keyWord " +
            "OR cust.locationRegion.address LIKE :keyWord " +
            "OR cust.locationRegion.provinceName LIKE :keyWord " +
            "OR cust.locationRegion.districtName LIKE :keyWord " +
            "OR cust.locationRegion.wardName LIKE :keyWord ) " +
            "AND cust.deleted = false "
    )
    List<CustomerResponseDTO> searchCustomer(@Param("keyWord")  String keyWord);



}
