package com.service.customer;

import com.exception.DataInputException;
import com.model.Customer;
import com.model.CustomerAvatar;
import com.model.dto.customer.CustomerAvatarDTO;
import com.model.dto.customer.CustomerResponseDTO;
import com.model.enums.FileType;
import com.repository.jwt.CustomerAvatarRepository;
import com.repository.jwt.CustomerRepository;
import com.repository.jwt.LocationRegionRepository;
import com.service.customerAvatar.CustomerAvatarService;
import com.service.uploadMedia.UploadService;
import com.utils.UploadUtils;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Autowired
    private CustomerAvatarService customerAvatarService;

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public List<CustomerResponseDTO> getAllExistCustomer() {
        List<Customer> customers = customerRepository.getAllByDeletedIsFalse();

        List<CustomerResponseDTO> dtoList = customers.stream()
                                                .map(item -> item.toCustomerResponseDTO())
                                                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public List<CustomerResponseDTO> getAllDeletedCustomer() {
        List<Customer> customers = customerRepository.getAllByDeletedIsTrue();

        List<CustomerResponseDTO> dtoList = customers.stream()
                .map(item -> item.toCustomerResponseDTO())
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomerByIdAndDeletedIsTrue(Long id) {
        return customerRepository.findCustomerByIdAndDeletedIsTrue(id);
    }

    @Override
    public List<CustomerResponseDTO> searchCustomer(String keyWord) {
        String kw = "%" + keyWord + "%";
        return customerRepository.searchCustomer(kw);
    }

    @Override
    public Customer saveWithAvatar(Customer customer, MultipartFile file) {
        CustomerAvatar oldCustomerAvatar = customer.getCustomerAvatar();
        try{
            uploadService.destroyImage(oldCustomerAvatar.getCloudId(),uploadUtils.buildImageDestroyParams(customer,oldCustomerAvatar.getCloudId()));
            customerAvatarService.delete(oldCustomerAvatar);
            String fileType = file.getContentType();
            assert fileType != null;
            fileType = fileType.substring(0, 5);
            CustomerAvatar customerAvatar = new CustomerAvatar();
            customerAvatar.setFileType(fileType);
            customerAvatar = customerAvatarService.save(customerAvatar);

            if (fileType.equals(FileType.IMAGE.getValue())) {
               CustomerAvatarDTO customerAvatarDTO   = customerAvatarService.uploadAndSaveCustomerAvatar(file, customerAvatar);
            }
            customer.setCustomerAvatar(customerAvatar);
            customer = customerRepository.save(customer);
            return customer;
        } catch (IOException e) {
            throw new DataInputException("Xóa hình ảnh thất bại.");
        }
    }

    @Override
    public Optional<Customer> findCustomerByIdAndDeletedIsFalse(Long id) {
        return customerRepository.findCustomerByIdAndDeletedIsFalse(id);
    }

    @Override
    public Boolean existById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public Boolean existsByEmailEquals(String email) {
        return customerRepository.existsByEmailEquals(email);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customer.setDeleted(true);
        save(customer);
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findById(id).get();
        customer.setDeleted(true);
        save(customer);
    }
}
