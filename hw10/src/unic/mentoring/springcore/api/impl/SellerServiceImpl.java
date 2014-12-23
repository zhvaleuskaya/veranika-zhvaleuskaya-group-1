package unic.mentoring.springcore.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unic.mentoring.springcore.api.SellerService;
import unic.mentoring.springcore.data.Seller;
import unic.mentoring.springcore.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {
    
    @Autowired
    private SellerRepository repository;

    @Override
    public List<Seller> getSellers() {
        return repository.getSellers();
    }

    @Override
    public Seller getSellerById(Long sellerId) {
        return repository.getSellerById(sellerId);
    }

    @Override
    public void importSellers(List<Seller> sellers) {
        for (Seller seller : sellers) {
            repository.createOrUpdateSeller(seller);
        }
    }
}
