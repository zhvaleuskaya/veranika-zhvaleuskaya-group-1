package unic.mentoring.springcore.api;

import java.util.List;

import unic.mentoring.springcore.data.Product;
import unic.mentoring.springcore.data.Proposal;
import unic.mentoring.springcore.data.Seller;

/**
 * Provide API for manipulating proposals.
 */
public interface ProposalService {
    
    /**
     * Creates the proposal.
     *
     * @param sellerId the seller id
     * @param productId the product id
     * @param price the price
     * @return the proposal id
     */
    Long createProposal(Long sellerId, Long productId, Double price);
    
    /**
     * Deactivate proposal.
     *
     * @param proposalId the proposal id
     */
    void deactivateProposal(Long proposalId);
    
    /**
     * Activate proposal.
     *
     * @param proposalId the proposal id
     */
    void activateProposal(Long proposalId);
    
    /**
     * Gets the proposals by product.
     *
     * @param product the product
     * @return the proposals by product
     */
    List<Proposal> getProposalsByProduct(Product product);
    
    /**
     * Gets the proposals by product id.
     *
     * @param productId the product id
     * @return the proposals by product id
     */
    List<Proposal> getProposalsByProductId(Long productId);
    
    /**
     * Gets the proposals by seller.
     *
     * @param seller the seller
     * @return the proposals by seller
     */
    List<Proposal> getProposalsBySeller(Seller seller);
    
    /**
     * Gets the proposals by seller id.
     *
     * @param sellerId the seller id
     * @return the proposals by seller id
     */
    List<Proposal> getProposalsBySellerId(Long sellerId);
}
