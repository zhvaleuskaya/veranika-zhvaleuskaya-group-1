package unic.mentoring.springcore.init;

import org.springframework.beans.factory.annotation.Autowired;

import unic.mentoring.springcore.api.ProductService;
import unic.mentoring.springcore.api.ProposalService;
import unic.mentoring.springcore.api.SellerService;
import unic.mentoring.springcore.common.Products;
import unic.mentoring.springcore.data.Product;
import unic.mentoring.springcore.data.Seller;
/**
 * The Proposal Initializer util class.
 */
public class ProposalInitializer {
    
    /** The product service. */
    @Autowired
    private ProductService productService;
    
    /** The proposal service. */
    @Autowired
    private ProposalService proposalService;
    
    /** The seller service. */
    @Autowired
    private SellerService sellerService;

    /**
     * Inits the proposals.
     */
    public void initProposals() {
        Seller amazon = sellerService.getSellerById((long) 1);
        Seller samsung = sellerService.getSellerById((long) 2);
        
        Product galaxyTab = productService.getProductsByName(Products.SAMSUNG_GALAXY_TAB).get(0);
        Product kindleFire = productService.getProductsByName(Products.KINDLE_FIRE).get(0);
        Product kindleTouch = productService.getProductsByName(Products.KINDLE_TOUCH).get(0);
        Product galaxyAce = productService.getProductsByName(Products.SAMSUNG_GALAXY_ACE).get(0);
        
        //Samsung
        proposalService.createProposal(samsung.getId(), galaxyAce.getId(), 250.0);
        proposalService.createProposal(samsung.getId(), galaxyTab.getId(), 500.0);
        
        //Amazon
        proposalService.createProposal(amazon.getId(), kindleFire.getId(), 199.0);
        proposalService.createProposal(amazon.getId(), kindleTouch.getId(), 99.0);
    }
}
