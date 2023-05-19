package com.service.billDetail;

import com.model.BillDetail;
import com.repository.jwt.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BillDetailServiceImpl implements IBillDetailService{

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return null;
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return billDetailRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public BillDetail save(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void delete(BillDetail billDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
