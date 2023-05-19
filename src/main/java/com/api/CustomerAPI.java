package com.api;

import com.exception.DataInputException;
import com.exception.EmailExistsException;
import com.model.Customer;
import com.model.CustomerAvatar;
import com.model.LocationRegion;
import com.model.dto.customer.CustomerAvatarDTO;
import com.model.dto.customer.CustomerRequestDTO;
import com.model.dto.customer.CustomerResponseDTO;
import com.model.dto.customer.LocationRegionDTO;
import com.service.customer.CustomerService;
import com.service.customerAvatar.CustomerAvatarService;
import com.service.locationRegion.LocationRegionService;
import com.service.uploadMedia.UploadService;
import com.utils.AppUtils;
import com.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerAvatarService customerAvatarService;

    @Autowired
    private LocationRegionService locationRegionService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllExist(){

        List<CustomerResponseDTO> customerResponseDTOList = customerService.getAllExistCustomer();

        return new ResponseEntity<>(customerResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/deleded-list")
    public ResponseEntity<?> getAllDeleted(){

        List<CustomerResponseDTO> customerResponseDTOList = customerService.getAllDeletedCustomer();

        return new ResponseEntity<>(customerResponseDTOList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'STAFF')")
    @GetMapping("/{customerId}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long customerId){
        Optional<Customer> customerOptional = customerService.findCustomerByIdAndDeletedIsFalse(customerId);

        if (!customerOptional.isPresent()){
            throw new DataInputException("Customer not exist!");
        }

        CustomerResponseDTO customerResponseDTO = customerOptional.get().toCustomerResponseDTO();

        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/deleted/{customerId}")
    public ResponseEntity<?> findCustomerDeletedById(@PathVariable Long customerId){
        Optional<Customer> customerOptional = customerService.findCustomerByIdAndDeletedIsTrue(customerId);

        if (!customerOptional.isPresent()){
            throw new DataInputException("Customer not exist!");
        }

        CustomerResponseDTO customerResponseDTO = customerOptional.get().toCustomerResponseDTO();

        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'STAFF')")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody String strListDelete){
        List<String> listDelete = new ArrayList<>();
        String [] str = strListDelete.split(",");
        for (String item : str){
            if (customerService.existById(Long.parseLong(item))){
                customerService.deleteById(Long.parseLong(item));
                listDelete.add(item);
            }else
                continue;
        }
        return new ResponseEntity<>(listDelete, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'STAFF')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated CustomerRequestDTO customerRequestDTO, BindingResult bindingResult){
        MultipartFile file = customerRequestDTO.getFile();
        String email = customerRequestDTO.getEmail();
        String dateOfBirth = customerRequestDTO.getDateOfBirth();
        LocationRegionDTO locationRegionDTO = customerRequestDTO.toLocationRegionDTO();
        locationRegionDTO.setId(null);

        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Boolean existEmail = customerService.existsByEmailEquals(email);

        if (existEmail){
            throw new EmailExistsException("The email is exist");
        }

        customerRequestDTO.setId(null);
        Customer customer = customerRequestDTO.toCustomer();

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
            customer.setDateOfBirth(date);
        }catch (ParseException parseException){
            parseException.printStackTrace();
        }

//        if (locationRegionDTO != null){
//            List<LocationRegion> locationRegions = customer.getLocationRegions();
//                if (locationRegions == null){
//                    locationRegions = new ArrayList<>();
//                }
//            locationRegions.add(locationRegionDTO.toLocationRegion());
//            customer.setLocationRegions(locationRegions);
//        }

        LocationRegion locationRegion = locationRegionService.save(locationRegionDTO.toLocationRegion());
        customer.setLocationRegion(locationRegion);
        CustomerAvatar customerAvatar = new CustomerAvatar();
        customerAvatar = customerAvatarService.save(customerAvatar);
        if(file != null){
            customerAvatarService.uploadAndSaveCustomerAvatar(file, customerAvatar);
        }
        customer.setCustomerAvatar(customerAvatar);
        customer = customerService.save(customer);
        CustomerResponseDTO customerResponseDTO = customer.toCustomerResponseDTO();


        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @PatchMapping("/edit/{customerId}")
    public ResponseEntity<?> edit(@PathVariable Long customerId,@Validated CustomerRequestDTO customerRequestDTO,
                                  BindingResult bindingResult){
        LocationRegionDTO locationRegionDTO = customerRequestDTO.toLocationRegionDTO();
        String dateOfBirth = customerRequestDTO.getDateOfBirth();
        MultipartFile file = customerRequestDTO.getFile();
        Optional<Customer> customerOptional = customerService.findCustomerByIdAndDeletedIsFalse(customerId);

        if (!customerOptional.isPresent()){
            throw new DataInputException("Customer not exist!");
        }

        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Customer customer = customerOptional.get();

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
            customer.setDateOfBirth(date);
        }catch (ParseException parseException){
            parseException.printStackTrace();
        }
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setFullName(customerRequestDTO.getFullName());
        customer.setPhone(customerRequestDTO.getPhoneNumber());

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
            customer.setDateOfBirth(date);
        }catch (ParseException parseException){
            parseException.printStackTrace();
        }

        locationRegionDTO.setId(customer.getLocationRegion().getId());
        LocationRegion locationRegion = locationRegionDTO.toLocationRegion();
        locationRegionService.save(locationRegion);
        customer.setLocationRegion(locationRegion);



        if (file != null){
//            uploadService.destroyImage(customer.getCustomerAvatar().getId(), )
            if (customer.getCustomerAvatar().getFileUrl() == null){
                CustomerAvatar customerAvatar = new CustomerAvatar();
                customerAvatarService.save(customerAvatar);
                customer.setCustomerAvatar(customerAvatar);
                customerAvatarService.uploadAndSaveCustomerAvatar(file, customerAvatar);
            }

            CustomerAvatarDTO customerAvatarDTO = customerService.saveWithAvatar(customer, file).getCustomerAvatar().toCustomerAvatarDTO();
            customer.setCustomerAvatar(customerAvatarDTO.toCustomerAvatar());
        }

        customerService.save(customer);

        CustomerResponseDTO customerResponseDTO = customer.toCustomerResponseDTO();
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR', 'STAFF')")
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody String keyWord){
        System.out.println(keyWord);
        if (keyWord.trim().equals(null) || keyWord.trim().equals("")){
            List<CustomerResponseDTO> customerResponseDTOS = customerService.getAllExistCustomer();
            return new ResponseEntity<>(customerResponseDTOS, HttpStatus.OK);
        }else {
            List<CustomerResponseDTO> customerResponseDTOS = customerService.searchCustomer(keyWord);
            return new ResponseEntity<>(customerResponseDTOS, HttpStatus.OK);
        }
    }
}
