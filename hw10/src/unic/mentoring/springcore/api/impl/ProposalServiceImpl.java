package unic.mentoring.springcore.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unic.mentoring.springcore.api.ProductService;
import unic.mentoring.springcore.api.ProposalService;
import unic.mentoring.springcore.api.SellerService;
import unic.mentoring.springcore.data.Product;
import unic.mentoring.springcore.data.Proposal;
import unic.mentoring.springcore.data.Seller;
import unic.mentoring.springcore.data.State;
import unic.mentoring.springcore.repository.ProposalRepository;

@Service
public class ProposalServiceImpl implements ProposalService {

    private ProposalRepository repository;
    
    @Autowired
    private SellerService sellerService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    public ProposalServiceImpl(ProposalRepository repository, SellerService sellerService, ProductService productService) {
    	super();
    	this.repository = repository;
    	this.sellerService = sellerService;
    	this.productService = productService;
    }
    
    @Autowired
    public ProposalServiceImpl(ProposalRepository repository) {
        super();
        this.repository = repository;
    }
    
    @Override
    public Long createProposal(Long sellerId, Long productId, Double price) {
        Proposal proposal = new Proposal();
        proposal.setPrice(price);
        proposal.setProduct(productService.getProductById(productId));
        proposal.setSeller(sellerService.getSellerById(sellerId));
        proposal.setState(State.NOT_ACTIVE_PROPOSAL);
        
        return repository.createProposal(proposal);
    }

    @Override
    public void deactivateProposal(Long proposalId) {
        Proposal proposal = repository.getProposal(proposalId);
        proposal.setState(State.NOT_ACTIVE_PROPOSAL);
        
        repository.updateProposal(proposal);
    }

    @Override
    public void activateProposal(Long proposalId) {
        Proposal proposal = repository.getProposal(proposalId);
        proposal.setState(State.ACTIVE_PROPOSAL);
        
        repository.updateProposal(proposal);
    }

    @Override
    public final List<Proposal> getProposalsByProduct(Product product) {
        return getProposalsByProductId(product.getId());
    }

    @Override
    public List<Proposal> getProposalsByProductId(Long productId) {
        return repository.getProposalsByProductId(productId);
    }

    @Override
    public final List<Proposal> getProposalsBySeller(Seller seller) {
        return getProposalsBySellerId(seller.getId());
    }

    @Override
    public List<Proposal> getProposalsBySellerId(Long sellerId) {
        return repository.getProposalsBySellerId(sellerId);
    }
}
