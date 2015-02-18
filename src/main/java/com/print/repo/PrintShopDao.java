package com.print.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.print.domain.CalculatorResult;
import com.print.domain.CustomerType;
import com.print.domain.ExtraItemType;
import com.print.domain.ExtraType;
import com.print.domain.ParentExtraType;
import com.print.domain.PriceCalcVO;
import com.print.domain.PriceJSONVO;
import com.print.domain.Pricing;
import com.print.domain.SizeUp;
import com.print.domain.Variety;
import com.print.domain.VarietyType;

public interface PrintShopDao {

	/**
	 * createCustomerType method is used for initial set up of customer types.
	 * 
	 */
	public void createCustomerType(CustomerType customerType);
	
	/**
	 * deleteCustomerType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void deleteCustomerType(CustomerType customerType);
	
	/**
	 * updateCustomerType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void updateCustomerType(CustomerType customerType);
	
	/**
	 * createCustomerType method is used for initial set up of customer types.
	 * 
	 * @return Map<String, String>
	 */
	public List<CustomerType> getAllCustomerTypes();
	
	/**
	 * createVarietyType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void createVariety(Variety variety);
	
	/**
	 * deleteVariety method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void deleteVariety(Variety objVariety);
	
	/**
	 * updateVariety method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void updateVariety(Variety objVariety);
	
	/**
	 * getVarietyType method is used to get variety	 types.
	 * 
	 * @param db DB
	 * @return Map<String, String>
	 */
	public List<Variety> getAllVarietys();	

	
	/**
	 * createVarietyType method is used to create each variety type.
	 * 
	 * @param VarietyType
	 */
	public String createVarietyType(VarietyType varietyType);
	
	/**
	 * getAllVarietyTypes method is used to get all the variety types.
	 * 
	 * @return List<VarietyType>
	 */
	public List<VarietyType> getAllVarietyTypes();	
	
	/**
	 * createSizeUps method is used to create size with height-up and width-up.
	 * 
	 * @param SizeUp
	 */
	public void createSizeUps(SizeUp sizeUp);
	
	/**
	 * getNumberOfUPS method is used to get the number of Up's for a give size with height-up and width up.
	 * 
	 * @param width
	 * @param height
	 * @param SizeUp
	 */
	public int getNumberOfUPS(SizeUp sizeUp, double width, double height);
	
	/**
	 * getSizeUps method is used to get size name with height-up and width-up.
	 * 
	 * @return List<SizeUp>
	 */
	public List<SizeUp> getSizeUps();
	
	/**
	 * deleteVariety method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void deleteVarietyType(VarietyType objVarietyType);
	
	/**
	 * updateVariety method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void updateVarietyType(VarietyType objVarietyType);
	
	/**
	 * deleteSizeUp method is used for initial set up of size.
	 * 
	 * @param db DB
	 */
	public void deleteSizeUp(SizeUp size);
	
	/**
	 * updateSizeUp method is used for initial set up of size.
	 * 
	 * @param db DB
	 */
	public void updateSizeUp(SizeUp size);
	
	/**
	 * getVarietyTypes method is used to get the variety types for a variety id.
	 * 
	 * @return Map<String, VarietyType>
	 */
	public List<VarietyType> getVarietyTypes(String varietyId);
	
	/**
	 * getSizeUp method is used to get size ups for a variety type id.
	 * 
	 * @return Set<SizeUp>
	 */
	public Set<SizeUp> getSizeUpsForVarietyType(String varTypeId);
	
	/**
	 * createPrizes method is used to initialize price for a given
	 * customer, variety type and size.
	 * 
	 * @param customerId
	 * @param varietyTypeId
	 * @param sizeId
	 */
	public void createPrizes(String customerId, String varietyTypeId, String sizeId);
	
	/**
	 * getPriceList method is used to get all the prices from pricelist.
	 * 
	 * @return List<Pricing>
	 */
	public List<Pricing> getPriceList();
	
	/**
	 * updatePricing method is used to update pricing information.
	 * 
	 * @param priceVO
	 */
	public void updatePricing(PriceJSONVO priceVO);
	
	/**
	 * calculatePrice method is used to calculate total price.
	 * 
	 * @param priceVO
	 */
	public CalculatorResult calculatePrice(PriceCalcVO priceVO);
	
	/**
	 * getAllParentExtraTypes method is used to get all parent extras.
	 * 
	 * @return List<ParentExtraType>
	 */
	public List<ParentExtraType> getAllParentExtraTypes();
	
	/**
	 * getAllExtraItemTypes method is used to get all extra item type.
	 * 
	 * @return List<ExtraItemType>
	 */
	public List<ExtraItemType> getAllExtraItemTypes();
	
	/**
	 * createExtraType method is used to create Extra Type.
	 * 
	 */
	public void createExtraType(ExtraType extraType);
	
	/**
	 * getAllExtraTypes method is used to get all extras.
	 * 
	 * @return List<ExtraType>
	 */
	public List<ExtraType> getAllExtraTypes();
	
	/**
	 * deleteExtraType method is used to delte extra type.
	 * 
	 * @param extraType ExtraType
	 */
	public void deleteExtraType(ExtraType extraType);
	
	/**
	 * getExtrasForCustomer method is used to get all extra type for a customer.
	 * 
	 * @param customerId
	 */
	public Map<String, List<ExtraType>> getExtrasForCustomer(String customerType);
	
}	
