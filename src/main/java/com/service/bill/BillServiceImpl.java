package com.service.bill;

import com.model.Bill;
import com.repository.jwt.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BillServiceImpl implements IBillService{

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return billRepository.existsById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
