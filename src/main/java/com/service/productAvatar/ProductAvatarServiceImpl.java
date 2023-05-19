package com.service.productAvatar;

import com.model.ProductAvatar;
import com.repository.jwt.ProductAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductAvatarServiceImpl implements IProductAvatarService{

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Override
    public List<ProductAvatar> findAll() {
        return null;
    }

    @Override
    public Optional<ProductAvatar> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Boolean existById(String id) {
        return null;
    }

    @Override
    public ProductAvatar save(ProductAvatar productAvatar) {
        return null;
    }

    @Override
    public void delete(ProductAvatar productAvatar) {

    }

    @Override
    public void deleteById(String id) {

    }
}
