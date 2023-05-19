package com.model.dto.customer;

import com.model.CustomerAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerAvatarDTO {
    private String id;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
    private String fileType;
    private String cloudId;

    public CustomerAvatar toCustomerAvatar(){
        return new CustomerAvatar()
                .setId(id)
                .setFileName(fileName)
                .setFileUrl(fileUrl)
                .setCloudId(cloudId)
                .setFileType(fileType);
    }
}
