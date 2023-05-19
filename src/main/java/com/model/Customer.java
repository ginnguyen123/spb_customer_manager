package com.model;

import com.model.dto.customer.CustomerAvatarDTO;
import com.model.dto.customer.CustomerResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@Accessors(chain = true)
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private Date dateOfBirth;

    private String phone;

    @Column(precision = 10, scale = 0, updatable = false, nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "customer")
    private List<Deposit> deposits;

    @OneToMany(targetEntity = Transfer.class)
    private List<Transfer> senders;

    @OneToMany(targetEntity = Transfer.class)
    private List<Transfer> recipients;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)//cascade = CascadeType.ALL
    // orphanRemoval = true đánh dấu rằng các phần tử con sẽ bị xóa khi bạn xóa nó khỏi collection của phần tử cha
    private LocationRegion locationRegion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_avatar_id", referencedColumnName = "id")//cascade = CascadeType.ALL
    // orphanRemoval = true đánh dấu rằng các phần tử con sẽ bị xóa khi bạn xóa nó khỏi collection của phần tử cha
    private CustomerAvatar customerAvatar;

    public CustomerResponseDTO toCustomerResponseDTO(){
        String strDateOfBirth;
        if (this.dateOfBirth != (null)){
            strDateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format(this.dateOfBirth);
        }else {
            strDateOfBirth = "0000-00-00";
        }
        return new CustomerResponseDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setDateOfBirth(strDateOfBirth)
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setCustomerAvatar(customerAvatar.toCustomerAvatarDTO())
                ;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                ", deposits=" + deposits +
                ", senders=" + senders +
                ", recipients=" + recipients +
                ", locationRegion=" + locationRegion +
                '}';
    }
}