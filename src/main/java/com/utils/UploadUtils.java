package com.utils;

import com.cloudinary.utils.ObjectUtils;
import com.exception.DataInputException;
import com.model.Customer;
import com.model.CustomerAvatar;
import com.model.ProductAvatar;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtils {
    public final String CUSTOMER_IMAGE_UPLOAD_FOLDER = "c12_customer_images";
    public final String PRODUCT_IMAGE_UPLOAD_FOLDER = "c12_product_images";
    public final String PRODUCT_VIDEO_UPLOAD_FOLDER = "c12_product_videos";
    public final String CUSTOMER_VIDEO_UPLOAD_FOLDER = "c12_customer_videos";

    public Map buildCustomerImageUploadParams(CustomerAvatar customerAvatar) {
        if (customerAvatar == null || customerAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của khách hàng chưa được lưu");

        String publicId = String.format("%s/%s", CUSTOMER_IMAGE_UPLOAD_FOLDER, customerAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildProductImageUploadParams(ProductAvatar productAvatar) {
        if (productAvatar == null || productAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", PRODUCT_IMAGE_UPLOAD_FOLDER, productAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Customer customer, String publicId) {
        if (customer == null || customer.getId() == null)
            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildVideoUploadParams(CustomerAvatar customerAvatar) {
        if (customerAvatar == null || customerAvatar.getId() == null)
            throw new DataInputException("Không thể upload video của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", CUSTOMER_VIDEO_UPLOAD_FOLDER, customerAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "video"
        );
    }

    public Map buildVideoDestroyParams(Customer customer, String publicId) {
        if (customer == null || customer.getId() == null)
            throw new DataInputException("Không thể destroy video của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "video"
        );
    }
}
