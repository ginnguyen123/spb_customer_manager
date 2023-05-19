package com.model;

import com.model.dto.customer.CustomerAvatarDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_avatar")
@Accessors(chain = true)
public class CustomerAvatar {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_folder")
    private String fileFolder;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "cloud_id")
    private String cloudId;


    public CustomerAvatarDTO toCustomerAvatarDTO(){
        return new CustomerAvatarDTO()
                .setId(id)
                .setFileName(fileName)
                .setFileUrl(fileUrl)
                .setFileType(fileType)
                .setCloudId(cloudId)
                .setFileFolder(fileFolder);
    }

    @Override
    public String toString() {
        return "CustomerAvatar{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileFolder='" + fileFolder + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                ", cloudId='" + cloudId + '\'' +
                '}';
    }
}
