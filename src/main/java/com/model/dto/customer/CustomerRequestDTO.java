package com.model.dto.customer;

import com.model.Customer;
import com.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequestDTO implements Validator {
    private Long id;
    @NotNull(message = "Full name cannot be empty (NotNull)")
    @NotEmpty(message = "Full name cannot be empty (NotEmpty)")
    private String fullName;

    @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", message = "yyyy-MM-dd")
    private String dateOfBirth;

    @Pattern(regexp = "^[0-9]{10}", message = "Phone number need 10 numbers")
    private String phoneNumber;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$", message = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$")
    private String email;

    //LocationRegion
    private String provinceName;
    private String provinceId;
    private String districtName;
    private String districtId;
    private String wardId;
    private String wardName;
    private String address;
    //image
    private MultipartFile file;

    public LocationRegionDTO toLocationRegionDTO(){
        return new LocationRegionDTO()
                .setProvinceName(provinceName)
                .setProvinceId(provinceId)
                .setDistrictName(districtName)
                .setDistrictId(districtId)
                .setWardName(wardName)
                .setWardId(wardId)
                .setAddress(address);
    }

    public Customer toCustomer(){
      return new Customer()
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phoneNumber)
                .setBalance(BigDecimal.ZERO);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return  CustomerRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRequestDTO customerRequestDTO = (CustomerRequestDTO) target;
    }

    @Override
    public String toString() {
        return "CustomerRequestDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", districtId='" + districtId + '\'' +
                ", wardId='" + wardId + '\'' +
                ", wardName='" + wardName + '\'' +
                ", address='" + address + '\'' +
                ", file=" + file +
                '}';
    }
}
