package com.print.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.print.domain.BasePrice;
import com.print.domain.CalculatorResult;
import com.print.domain.CustomerType;
import com.print.domain.ExtraItemType;
import com.print.domain.ExtraType;
import com.print.domain.ExtraTypePrice;
import com.print.domain.MultiPrice;
import com.print.domain.NumberOfUpJsonVO;
import com.print.domain.ParentExtraType;
import com.print.domain.PriceCalcVO;
import com.print.domain.PriceJSONVO;
import com.print.domain.PricePerSet;
import com.print.domain.Pricing;
import com.print.domain.QuantitativePrice;
import com.print.domain.SelectedExtra;
import com.print.domain.SizeUp;
import com.print.domain.Variety;
import com.print.domain.VarietyType;

@Repository
public class PrintShopDaoImpl implements PrintShopDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * createCustomerType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void createCustomerType(CustomerType customerType) {
		mongoTemplate.insert(customerType);
	}
	
	/**
	 * deleteCustomerType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void deleteCustomerType(CustomerType customerType) {
		mongoTemplate.remove(customerType);
	}
	
	/**
	 * updateCustomerType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void updateCustomerType(CustomerType customerType) {
		Query query = new Query(Criteria.where("id").is(customerType.getId()));
		mongoTemplate.updateFirst(query,Update.update("name", customerType.getName()),CustomerType.class);
	}
	
	/**
	 * createCustomerType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 * @return Map<String, String>
	 */
	public List<CustomerType> getAllCustomerTypes() {
		List<CustomerType> customers = mongoTemplate.findAll(CustomerType.class); 
		return Collections.unmodifiableList(customers);		
	}
	
	/**
	 * createVarietyType method is used for initial set up of customer types.
	 * 
	 * @param db DB
	 */
	public void createVariety(Variety variety) {
		mongoTemplate.insert(variety);
	}
	
	/**
	 * getVarietyType method is used to get variety	 types.
	 * 
	 * @param db DB
	 * @return Map<String, String>
	 */
	public List<Variety> getAllVarietys() {
		List<Variety> varietys = mongoTemplate.findAll(Variety.class); 
		return Collections.unmodifiableList(varietys);	
	}
	
	/**
	 * createVarietyType method is used to create each variety type.
	 * 
	 * @param VarietyType
	 */
	public String createVarietyType(VarietyType varietyType) {
		mongoTemplate.insert(varietyType);
		return varietyType.getId();
	}
	
	/**
	 * getAllVarietyTypes method is used to get all the variety types.
	 * 
	 * @return List<VarietyType>
	 */
	public List<VarietyType> getAllVarietyTypes() {
		List<VarietyType> varietyTypeList = mongoTemplate.findAll(VarietyType.class); 
		return Collections.unmodifiableList(varietyTypeList);
	}
	
	/**
	 * createSizeUps method is used to create size with height-up and width-up.
	 * 
	 * @param SizeUp
	 */
	public void createSizeUps(SizeUp sizeUp) {
		mongoTemplate.insert(sizeUp);
	}
	
	/**
	 * getSizeUps method is used to get size name with height-up and width-up.
	 * 
	 * @param SizeUp
	 */
	public List<SizeUp> getSizeUps() {
		List<SizeUp> sizeUpList = mongoTemplate.findAll(SizeUp.class);
		return Collections.unmodifiableList(sizeUpList);
	}
	
	/**
	 * getNumberOfUPS method is used to get the number of Up's for a give size with height-up and width up.
	 * 
	 * @param width
	 * @param height
	 * @param SizeUp
	 */
	public int getNumberOfUPS(SizeUp sizeUp, double width, double height) {
		int noOfUps = 0;
		double sheetWidth = 0;
		double sheetHeight = 0;
				
		sheetWidth = sizeUp.getWidth_up();
		sheetHeight = sizeUp.getHeight_up();
		
		double widthUp = (sheetWidth - 0.5)/width;
		double heightUp = (sheetHeight - 0.5)/height;
		
		int widthUpFloored = (int) Math.floor(widthUp);
		int heightUpFloored = (int) Math.floor(heightUp);
		
		noOfUps = widthUpFloored * heightUpFloored;
		
		return noOfUps;
	}

	/**
	 * deleteVariety method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void deleteVariety(Variety objVariety) {
		mongoTemplate.remove(objVariety);
	}

	/**
	 * updateVariety method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void updateVariety(Variety objVariety) {
		Query query = new Query(Criteria.where("id").is(objVariety.getId()));
		mongoTemplate.updateFirst(query,Update.update("name", objVariety.getName()),Variety.class);
	}

	/**
	 * deleteVarietyType method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void deleteVarietyType(VarietyType objVarietyType) {
		
		Query query = new Query(Criteria.where("id").is(objVarietyType.getId()));
		VarietyType varType = mongoTemplate.findOne(query, VarietyType.class);
		
		mongoTemplate.remove(objVarietyType);
		deletePricingForVarietyType(varType);
	}
	
	/**
	 * updateVarietyType method is used for initial set up of variety.
	 * 
	 * @param db DB
	 */
	public void updateVarietyType(VarietyType objVarietyType) {
		Query query = new Query(Criteria.where("id").is(objVarietyType.getId()));
		mongoTemplate.updateFirst(query,Update.update("name", objVarietyType.getName()),VarietyType.class);
	}
	
	/**
	 * deleteSizeUp method is used for initial set up of size.
	 * 
	 * @param db DB
	 */
	public void deleteSizeUp(SizeUp size) {
		mongoTemplate.remove(size);
	}
	
	/**
	 * updateSizeUp method is used for initial set up of size.
	 * 
	 * @param db DB
	 */
	public void updateSizeUp(SizeUp size) {
		Query query = new Query(Criteria.where("id").is(size.getId()));
		
		Update sizeUpdate = new Update();
		sizeUpdate.set("name", size.getName());
		sizeUpdate.set("height_up", size.getHeight_up());
		sizeUpdate.set("width_up", size.getWidth_up());
	    
		mongoTemplate.updateFirst(query, sizeUpdate, SizeUp.class);
	}
	
	/**
	 * getVarietyTypes method is used to get the variety types for a variety id.
	 * 
	 * @return Map<String, List<VarietyType>>
	 */
	public List<VarietyType> getVarietyTypes(String varietyId) {
		
		Query varQuery = new Query(Criteria.where("id").is(varietyId));
		List<Variety> variety = mongoTemplate.find(varQuery, Variety.class);
		
		Query query = new Query(Criteria.where("variety").is(variety.get(0)));
		List<VarietyType> varietyTypeList = mongoTemplate.find(query, VarietyType.class);
		
		return varietyTypeList;
	}
	
	/**
	 * getSizeUp method is used to get size ups for a variety type id.
	 * 
	 * @return Set<SizeUp>
	 */
	public Set<SizeUp> getSizeUpsForVarietyType(String varTypeId) {
		Query varQuery = new Query(Criteria.where("id").is(varTypeId));
		List<VarietyType> varietyTypeList = mongoTemplate.find(varQuery, VarietyType.class);
		
		Set<SizeUp> sizeUpSet = new HashSet<SizeUp>();
		
		Iterator<VarietyType> iterator = varietyTypeList.iterator();
		
		while(iterator.hasNext()) {
			VarietyType varType = iterator.next();
			sizeUpSet.addAll(varType.getSizeUp());
		}
						
		return sizeUpSet;
	}
	
	/**
	 * createPrizes method is used to initialize price for a given
	 * customer, variety type and size.
	 * 
	 * @param customerId
	 * @param varietyTypeId
	 * @param sizeId
	 */
	public void createPrizes(String customerId, String varietyTypeId, String sizeId) {
		//Get Customer Type
		Query queryCustomer = new Query(Criteria.where("id").is(customerId));
		List<CustomerType> customerTypeList = mongoTemplate.find(queryCustomer, CustomerType.class);
		
		//Get Variety Type
		Query queryVariety = new Query(Criteria.where("id").is(varietyTypeId));
		List<VarietyType> varietyTypeList = mongoTemplate.find(queryVariety, VarietyType.class);
		
		//Get Size Up
		Query querySize = new Query(Criteria.where("id").is(sizeId));
		List<SizeUp> sizeUpList = mongoTemplate.find(querySize, SizeUp.class);
		
		Pricing price = new Pricing();
		price.setCustomerType(customerTypeList.get(0));
		price.setSizeUp(sizeUpList.get(0));
		price.setVarietyType(varietyTypeList.get(0));
		price.setEachPrice(0);
		price.setPriceAbove10(0);
		price.setPriceAbove100(0);
		price.setPriceAbove200(0);
		price.setPriceAbove50(0);
		price.setPriceAbove500(0);
		
		mongoTemplate.insert(price);
		
	}
	
	/**
	 * getPriceList method is used to get all the prices from pricelist.
	 * 
	 * @return List<Pricing>
	 */
	public List<Pricing> getPriceList() {
		List<Pricing> customers = mongoTemplate.findAll(Pricing.class); 
		return Collections.unmodifiableList(customers);		
	}
	
	/**
	 * updatePricing method is used pricing information.
	 * 
	 * @param priceVO
	 */
	public void updatePricing(PriceJSONVO priceVO) {
		Query query = new Query(Criteria.where("id").is(priceVO.getPricingId()));
		
		Update pricingUpdate = new Update();
		pricingUpdate.set("eachPrice", priceVO.getEachPrice());
		pricingUpdate.set("priceAbove10", priceVO.getPriceAbove10());
		pricingUpdate.set("priceAbove50", priceVO.getPriceAbove50());
		pricingUpdate.set("priceAbove100", priceVO.getPriceAbove100());
		pricingUpdate.set("priceAbove200", priceVO.getPriceAbove200());
		pricingUpdate.set("priceAbove500", priceVO.getPriceAbove500());
	    
		mongoTemplate.updateFirst(query, pricingUpdate, Pricing.class);
	}
	
	public void deletePricingForVarietyType(VarietyType varietyType) {
		
		Query pricingQuery = new Query();
		
		pricingQuery.addCriteria(
                Criteria.where("varietyType.name").is(varietyType.getName()));
		
		List<Pricing> pricingList = mongoTemplate.find(pricingQuery, Pricing.class);
		
		Iterator<Pricing> priceIterator = pricingList.iterator();
		
		while(priceIterator.hasNext()) {
			Pricing pricing = priceIterator.next();
			Query priceQuery = new Query(Criteria.where("id").is(pricing.getId()));
			mongoTemplate.remove(priceQuery, Pricing.class);
		}
		
	}
	
	/**
	 * calculatePrice method is used to calculate total price.
	 * 
	 * @param priceVO
	 */
	public CalculatorResult calculatePrice(PriceCalcVO priceVO) {
		double totalPrice = 0.0;
		
		NumberOfUpJsonVO noOfUpJson = priceVO.getNoOfUp();
		CalculatorResult calcResult = new CalculatorResult();
		
		SizeUp size = new SizeUp();
    	size.setId(noOfUpJson.getSizeId());
    	size.setHeight_up(noOfUpJson.getHeight_up());
    	size.setWidth_up(noOfUpJson.getWidth_up());
    	int noOfUps = getNumberOfUPS(size, noOfUpJson.getWidth(), noOfUpJson.getHeight());
    	
    	priceVO.setNumberOfUp(noOfUps);
		
		CustomerType customerType = new CustomerType();
		customerType.setId(priceVO.getCustTypeId());
		
		Query query = new Query(Criteria.where("id").is(priceVO.getCustTypeId()));
		CustomerType custType =  mongoTemplate.findOne(query, CustomerType.class);
		
		Query sizeQuery = new Query(Criteria.where("id").is(priceVO.getSizeUpId()));
		SizeUp sizeup =  mongoTemplate.findOne(sizeQuery, SizeUp.class);
		
		Query varietyQuery = new Query(Criteria.where("id").is(priceVO.getVarTypeId()));
		VarietyType varType =  mongoTemplate.findOne(varietyQuery, VarietyType.class);
		
		Query pricingQuery = new Query();
		
		pricingQuery.addCriteria(
                Criteria.where("customerType.name").is(custType.getName()).andOperator(Criteria.where("varietyType.name").is(varType.getName()),Criteria.where("sizeUp.name").is(sizeup.getName())));
		
		Pricing pricing = mongoTemplate.findOne(pricingQuery, Pricing.class);
		
		/*
		 * number of sheet: (quantity/no_of_ups (Round it to highest whole number))
		 * 
		 * price = number of sheet * price
		 * 
		 * extras = number of sheet * BAS
		 */
		int numberOfUp = priceVO.getNumberOfUp();	
		
		int quantity = priceVO.getQty();
		
		float numbUps = (float) quantity/numberOfUp;
			
		int numberOfSheet = (int) Math.ceil(numbUps);
				
		if(quantity >= 500) {
			totalPrice = numberOfSheet * pricing.getPriceAbove500();
		} else if(quantity >= 200) {
			totalPrice = numberOfSheet * pricing.getPriceAbove200();
		} else if(quantity >= 100) {
			totalPrice = numberOfSheet * pricing.getPriceAbove100();
		} else if(quantity >= 50) {
			totalPrice = numberOfSheet * pricing.getPriceAbove50();
		} else if(quantity >= 10) {
			totalPrice = numberOfSheet * pricing.getPriceAbove10();
		} else {
			totalPrice = numberOfSheet * pricing.getEachPrice();
		}
		
		calcResult.setPrintingPrice(totalPrice);
		List<SelectedExtra> selectedExtrasList = priceVO.getSelectedExtraArray();
		
		float extrasPrice = calculateExtras(selectedExtrasList);
		totalPrice = totalPrice + extrasPrice;
		
		calcResult.setExtraPrice(extrasPrice);
		calcResult.setNoOfSheets(numberOfSheet);
		calcResult.setTotalPrice(totalPrice);
		
		return calcResult;
	}
	
	public float calculateExtras(List<SelectedExtra> selectedExtrasList) {
		float extrasPrice = 0;
		
		Iterator<SelectedExtra> selExtraIter = selectedExtrasList.iterator();
		while(selExtraIter.hasNext()) {
			SelectedExtra selExtra = selExtraIter.next();
			
			//Get Extra
			Query queryCustomer = new Query(Criteria.where("id").is(selExtra.getId()));
			ExtraType extraType = mongoTemplate.findOne(queryCustomer, ExtraType.class);
			
			ExtraTypePrice pricing = extraType.getExtraTypePrice();
			
			if(pricing instanceof BasePrice) {
				System.out.println("Base Price");
				BasePrice basePrice = (BasePrice) pricing;
				long qty = selExtra.getQty();
				float newPrice = (float) (qty * basePrice.getPrice());
				extrasPrice = extrasPrice + newPrice;
			}
			if(pricing instanceof MultiPrice) {
				System.out.println("Multi Price");
				MultiPrice multiPrice = (MultiPrice) pricing;
				long thresholdQty = multiPrice.getThresholdQty();
				long qty = selExtra.getQty();
				if(qty >= thresholdQty) {
					float newPrice = (float) (qty * multiPrice.getHighThresholdPrice());
					extrasPrice = extrasPrice + newPrice;
				} else {
					float newPrice = (float) (qty * multiPrice.getLowThresholdPrice());
					extrasPrice = extrasPrice + newPrice;
				}
			}
			if(pricing instanceof QuantitativePrice) {
				System.out.println("Quantitative Price");
				QuantitativePrice qtyPrice = (QuantitativePrice) pricing;
				long qty = selExtra.getQty();
				long minQty = qtyPrice.getMinimumQty();
				if(qty <= minQty) {
					float newPrice = (float) qtyPrice.getMinimumPrice();
					extrasPrice = extrasPrice + newPrice;
				} else {
					long qty1 = qty - minQty;
					float newPrice = (float) (qty1 * qtyPrice.getPricePerItem());
					newPrice = (float) (newPrice + qtyPrice.getMinimumPrice());
					extrasPrice = extrasPrice + newPrice;
				}
			}
			if(pricing instanceof PricePerSet) {
				System.out.println("Pricer Per Set");
				PricePerSet pricePerSet = (PricePerSet) pricing;
				long qty = selExtra.getQty();
				long itemsPerSet = pricePerSet.getItemPerSet();
				int eachSet = (int) Math.ceil(qty / itemsPerSet);
				extrasPrice = (float) (extrasPrice + (eachSet * pricePerSet.getPricePerSet()));
				
			}

		}
		
		return extrasPrice;
	}
	
	/**
	 * getAllParentExtraTypes method is used to get all parent extras.
	 * 
	 * @return List<ParentExtraType>
	 */
	public List<ParentExtraType> getAllParentExtraTypes() {
		List<ParentExtraType> parentExtraList = mongoTemplate.findAll(ParentExtraType.class); 
		
		if(parentExtraList.size() <= 0) {
			// Add Parent Extas type
			ParentExtraType parentLamination = new ParentExtraType();
			parentLamination.setName("Laminations");
			mongoTemplate.insert(parentLamination);

			ParentExtraType parentBinding = new ParentExtraType();
			parentBinding.setName("Binding");
			mongoTemplate.insert(parentBinding);
			
			ParentExtraType parentPunching = new ParentExtraType();
			parentPunching.setName("Punching");
			mongoTemplate.insert(parentPunching);
			
			ParentExtraType parentCreasing = new ParentExtraType();
			parentCreasing.setName("Creasing");
			mongoTemplate.insert(parentCreasing);
			
			ParentExtraType parentEmboss = new ParentExtraType();
			parentEmboss.setName("Emboss");
			mongoTemplate.insert(parentEmboss);
			
			ParentExtraType parentFoilStamping = new ParentExtraType();
			parentFoilStamping.setName("FoilStamping");
			mongoTemplate.insert(parentFoilStamping);
			
			parentExtraList = mongoTemplate.findAll(ParentExtraType.class);
		}
		
		return Collections.unmodifiableList(parentExtraList);	
	}
	
	/**
	 * getAllExtraItemTypes method is used to get all extra item type.
	 * 
	 * @return List<ExtraItemType>
	 */
	public List<ExtraItemType> getAllExtraItemTypes() {
		List<ExtraItemType> extraItemTypeList = mongoTemplate.findAll(ExtraItemType.class); 
		
		if(extraItemTypeList.size() <= 0) {
			//Add Extra Item Type
			ExtraItemType itemBookType = new ExtraItemType();
			itemBookType.setName("Book");
			mongoTemplate.insert(itemBookType);
			
			ExtraItemType itemSheetType = new ExtraItemType();
			itemSheetType.setName("Sheet");
			mongoTemplate.insert(itemSheetType);
			
			extraItemTypeList = mongoTemplate.findAll(ExtraItemType.class);
		}
		
		return Collections.unmodifiableList(extraItemTypeList);
	}
	
	/**
	 * createExtraType method is used to create Extra Type.
	 * 
	 */
	public void createExtraType(ExtraType extraType) {
		mongoTemplate.insert(extraType);
	}
	
	/**
	 * getAllExtraTypes method is used to get all extras.
	 * 
	 * @return List<ExtraType>
	 */
	public List<ExtraType> getAllExtraTypes() {
		List<ExtraType> extraList = mongoTemplate.findAll(ExtraType.class); 
		return Collections.unmodifiableList(extraList);
	}
	
	/**
	 * deleteExtraType method is used to delte extra type.
	 * 
	 * @param extraType ExtraType
	 */
	public void deleteExtraType(ExtraType extraType) {
		mongoTemplate.remove(extraType);
	}
	
	/**
	 * getExtrasForCustomer method is used to get all extra type for a customer.
	 * 
	 * @param customerId
	 */
	public Map<String, List<ExtraType>> getExtrasForCustomer(String customerType) {
		Map<String, List<ExtraType>> extraMap = new HashMap<String, List<ExtraType>>();
		
		List<ParentExtraType> parentExtras = getAllParentExtraTypes();
		
		Iterator<ParentExtraType> parExtraIterator = parentExtras.iterator();
		while(parExtraIterator.hasNext()) {
			ParentExtraType parExtType = parExtraIterator.next();
			Query customerExtraQuery = new Query();
			customerExtraQuery.addCriteria(
	                Criteria.where("customerTypeName").is(customerType).andOperator(Criteria.where("parentExtraName").is(parExtType.getName())));
			
			List<ExtraType> extraTypeList = mongoTemplate.find(customerExtraQuery, ExtraType.class);
			
			if(extraTypeList.size() > 0) {
				extraMap.put(parExtType.getName(), extraTypeList);
			}
		}
		
		return extraMap;
	}
}
