package com.model;

import com.model.dto.customer.LocationRegionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location_region")
@Accessors(chain = true)
public class LocationRegion extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "province_id")
    private String provinceId;
    @Column(name = "province_name")
    private String provinceName;
    @Column(name = "district_id")
    private String districtId;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "ward_id")
    private String wardId;
    @Column(name = "ward_name")
    private String wardName;

    private String address;

    public LocationRegionDTO toLocationRegionDTO(){
        return new LocationRegionDTO()
                .setId(id)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address);
    }

    @Override
    public String toString() {
        return "LocationRegion{" +
                "id=" + id +
                ", provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", districtId='" + districtId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", wardId='" + wardId + '\'' +
                ", wardName='" + wardName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
