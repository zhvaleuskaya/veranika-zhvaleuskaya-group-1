package unic.mentoring.springcore.repository;

import java.util.List;

import unic.mentoring.springcore.data.Proposal;

public interface ProposalRepository
{
	Proposal getProposal(Long id);
	Long createProposal(Proposal proposal);
	void updateProposal(Proposal proposal);
	List<Proposal> getProposalsByProductId(Long productId);
	List<Proposal> getProposalsBySellerId(Long sellerId);
}