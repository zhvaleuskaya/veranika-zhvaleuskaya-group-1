package unic.mentoring.springcore.repository.map;

import java.util.ArrayList;
import java.util.List;

import unic.mentoring.springcore.data.Seller;
import unic.mentoring.springcore.repository.SellerRepository;

public final class SellerMapRepository extends AbstractMapRepository<Seller> implements SellerRepository
{
	@Override
	public void createOrUpdateSeller(Seller seller)
	{
		update(seller);
	}

	@Override
	public List<Seller> getSellers()
	{
		return new ArrayList<Seller>(register.values());
	}

	@Override
	public Seller getSellerById(Long sellerId)
	{
		return get(sellerId);
	}
}