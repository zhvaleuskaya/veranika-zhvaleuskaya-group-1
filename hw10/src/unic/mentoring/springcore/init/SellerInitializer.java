package unic.mentoring.springcore.init;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import unic.mentoring.springcore.api.SellerService;
import unic.mentoring.springcore.data.Seller;

/**
 * The Seller Initializer util class.
 */
public class SellerInitializer {

    /** The seller service. */
    private SellerService sellerService;
    
    /** The seller names. */
    private Map<Long, String> sellerNames = Collections.emptyMap();

    public SellerInitializer(SellerService sellerService, Map<Long, String> sellerNames) {
        super();
        this.sellerService = sellerService;
        this.sellerNames = sellerNames;
    }

    /**
     * Inits the sellers.
     */
    public void initSellers() {
        List<Seller> sellers = new LinkedList<Seller>();
        
        for (Map.Entry<Long, String> entry : sellerNames.entrySet()) {
            Seller seller = new Seller();
            seller.setId(entry.getKey());
            seller.setName(entry.getValue());
            
            sellers.add(seller);
        }
        
        sellerService.importSellers(sellers);
    }
}
