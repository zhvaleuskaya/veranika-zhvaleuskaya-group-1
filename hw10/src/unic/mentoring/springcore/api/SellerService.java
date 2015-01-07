package unic.mentoring.springcore.api;

import java.util.List;

import unic.mentoring.springcore.data.Seller;

/**
 * Provides API for manipulating selles.
 */
public interface SellerService {
    
    /**
     * Import sellers.
     *
     * @param sellers the sellers
     */
    void importSellers(List<Seller> sellers);
    
    /**
     * Gets the sellers.
     *
     * @return the sellers
     */
    List<Seller> getSellers();
    
    /**
     * Gets the seller by id.
     *
     * @param sellerId the seller id
     * @return the seller by id
     */
    Seller getSellerById(Long sellerId);
}
