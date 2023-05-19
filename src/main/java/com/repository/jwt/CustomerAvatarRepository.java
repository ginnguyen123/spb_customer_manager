package com.repository.jwt;

import com.model.Customer;
import com.model.CustomerAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAvatarRepository extends JpaRepository<CustomerAvatar, String> {


}
