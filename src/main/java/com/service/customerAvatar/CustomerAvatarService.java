package com.service.customerAvatar;

import com.model.Customer;
import com.model.CustomerAvatar;
import com.model.dto.customer.CustomerAvatarDTO;
import com.service.IGeneralWithImageService;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface CustomerAvatarService extends IGeneralWithImageService<CustomerAvatar> {

    CustomerAvatarDTO uploadAndSaveCustomerAvatar (MultipartFile file, CustomerAvatar customerAvatar);


    Optional<CustomerAvatar> findByCustomer();
}
