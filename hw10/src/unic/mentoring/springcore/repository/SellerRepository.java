package unic.mentoring.springcore.repository;

import java.util.List;

import unic.mentoring.springcore.data.Seller;

public interface SellerRepository
{
	void createOrUpdateSeller(Seller seller);
	List<Seller> getSellers();
	Seller getSellerById(Long sellerId);
}