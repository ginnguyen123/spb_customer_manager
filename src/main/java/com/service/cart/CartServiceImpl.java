package com.service.cart;

import com.model.Cart;
import com.model.CartItem;
import com.repository.jwt.CartItemRepository;
import com.repository.jwt.CartRepository;
import com.service.cartItem.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return cartRepository.existsById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
