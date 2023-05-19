package com.model.dto.customer;

import com.model.CustomerAvatar;
import com.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerResponseDTO {
    private Long id;
    private String fullName;
    private String dateOfBirth;
    private String email;
    private String phone;
    private LocationRegionDTO locationRegion;
    private CustomerAvatarDTO customerAvatar;

    public CustomerResponseDTO(Long id, String fullName, String phone, String email, Date dateOfBirth,
                               LocationRegion locationRegion, CustomerAvatar customerAvatar){
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        String strDateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
        this.dateOfBirth = strDateOfBirth;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.customerAvatar = customerAvatar.toCustomerAvatarDTO();
    };

}
