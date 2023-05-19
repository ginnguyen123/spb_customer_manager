package com.service.customerAvatar;

import com.cloudinary.Cloudinary;
import com.exception.DataInputException;
import com.model.Customer;
import com.model.CustomerAvatar;
import com.model.dto.customer.CustomerAvatarDTO;
import com.repository.jwt.CustomerAvatarRepository;
import com.service.uploadMedia.UploadService;
import com.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CustomerAvatarServiceImpl implements CustomerAvatarService{

    @Autowired
    private CustomerAvatarRepository customerAvatarRepository;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<CustomerAvatar> findAll() {
        return null;
    }

    @Override
    public Optional<CustomerAvatar> findById(String id) {
        return customerAvatarRepository.findById(id);
    }

    @Override
    public Optional<CustomerAvatar> findByCustomer() {
        return null;
    }

    @Override
    public Boolean existById(String id) {
        return null;
    }

    @Override
    public CustomerAvatar save(CustomerAvatar customerAvatar) {
        return customerAvatarRepository.save(customerAvatar);
    }

    @Override
    public void delete(CustomerAvatar customerAvatar) {
        customerAvatarRepository.delete(customerAvatar);
    }

    @Override
    public void deleteById(String id) {
        customerAvatarRepository.deleteById(id);
    }

    @Override
    public CustomerAvatarDTO uploadAndSaveCustomerAvatar(MultipartFile file, CustomerAvatar customerAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtils.buildCustomerImageUploadParams(customerAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");
            customerAvatar.setFileName(customerAvatar.getId() + "." + fileFormat);
            customerAvatar.setFileUrl(fileUrl);
            customerAvatar.setFileFolder(uploadUtils.CUSTOMER_IMAGE_UPLOAD_FOLDER);
            customerAvatar.setCloudId(customerAvatar.getFileFolder() + "/" + customerAvatar.getId());
            customerAvatarRepository.save(customerAvatar);
            return customerAvatar.toCustomerAvatarDTO();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }
}
