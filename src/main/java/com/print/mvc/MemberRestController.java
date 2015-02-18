package com.print.mvc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.print.domain.BasePrice;
import com.print.domain.BasePriceVO;
import com.print.domain.CalculatorResult;
import com.print.domain.CustomerType;
import com.print.domain.ExtraItemType;
import com.print.domain.ExtraType;
import com.print.domain.ExtraTypePrice;
import com.print.domain.Member;
import com.print.domain.MultiPrice;
import com.print.domain.MultiPriceVO;
import com.print.domain.NumberOfUpJsonVO;
import com.print.domain.PriceCalcVO;
import com.print.domain.PriceJSONVO;
import com.print.domain.PricePerSet;
import com.print.domain.Pricing;
import com.print.domain.QuantitativePrice;
import com.print.domain.QuantitativePriceVO;
import com.print.domain.SetPriceVO;
import com.print.domain.VarietyTypeJsonVO;
import com.print.domain.SizeUp;
import com.print.domain.Variety;
import com.print.domain.VarietyType;
import com.print.repo.PrintShopDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/print")
public class MemberRestController
{
    @Autowired
    private PrintShopDao printShopDao;
    
    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<CustomerType> listAllMembers()
    {
    	List<CustomerType> customers =  printShopDao.getAllCustomerTypes();
    	return customers;
    }

    @RequestMapping(value="/customertype/add/{customer}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createCustomerType(@PathVariable("customer") String customer)
    {
    	CustomerType customerType = new CustomerType();
    	customerType.setName(customer);
    	printShopDao.createCustomerType(customerType);
    	System.out.println("Customer entered is " + customer);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/customertype/remove/{customerId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity deleteCustomerType(@PathVariable("customerId") String customerId)
    {
    	CustomerType customerType = new CustomerType();
    	customerType.setId(customerId);
    	printShopDao.deleteCustomerType(customerType);
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/customertype/edit/{customerId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity editCustomerType(@PathVariable("customerId") String customerId, @RequestParam("name") String name)
    {
    	CustomerType customerType = new CustomerType();
    	customerType.setId(customerId);
    	customerType.setName(name);
    	
    	System.out.println("Customer id: " + customerType.getId());
    	System.out.println("Customer Name: " + customerType.getName());
    	
    	printShopDao.updateCustomerType(customerType);
    	
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/variety/add/{variety}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createVariety(@PathVariable("variety") String variety)
    {
    	Variety objVariety = new Variety();
    	objVariety.setName(variety);
    	printShopDao.createVariety(objVariety);
    	System.out.println("Customer entered is " + objVariety.getName());
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/variety/remove/{varietyId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity deletevariety(@PathVariable("varietyId") String varietyId)
    {
    	Variety objVariety = new Variety();
    	objVariety.setId(varietyId);
    	printShopDao.deleteVariety(objVariety);
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/variety/edit/{varietyId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity editVariety(@PathVariable("varietyId") String varietyId, @RequestParam("name") String name)
    {
    	Variety objVariety = new Variety();
    	objVariety.setId(varietyId);
    	objVariety.setName(name);
    	
    	System.out.println("Customer id: " + objVariety.getId());
    	System.out.println("Customer Name: " + objVariety.getName());
    	
    	printShopDao.updateVariety(objVariety);
    	
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    @RequestMapping(value="/varietytype/add/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createVarietyType(@RequestBody VarietyTypeJsonVO sizeVO)
    {
    	// Create variety type
    	VarietyType objVarietyType = new VarietyType();
    	objVarietyType.setName(sizeVO.getVarTypeName());
    	
    	Variety variety = new Variety();
    	variety.setId(sizeVO.getVarietyId());
    	variety.setName(sizeVO.getVarietyName());
    	
    	objVarietyType.setSizeUp(sizeVO.getSizeUp());
    	objVarietyType.setVariety(variety);
    	
    	String varietyTypeId = printShopDao.createVarietyType(objVarietyType);
    	
    	// Create Pricing for each size, customerType and variety type combination
    	
    	createPricing(sizeVO, varietyTypeId);
    	
    	
    	System.out.println("Customer entered is " + objVarietyType.getName());
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

	private void createPricing(VarietyTypeJsonVO sizeVO, String varietyTypeId) {
		Set<SizeUp> sizeUpSet = sizeVO.getSizeUp();
    	
    	List<CustomerType> customerList = printShopDao.getAllCustomerTypes();
    	
    	Iterator<CustomerType> custList = customerList.iterator();
    	while(custList.hasNext()) {
    		CustomerType custType = custList.next();
    		Iterator<SizeUp> sizeUp = sizeUpSet.iterator();
        	while(sizeUp.hasNext()) {
        		SizeUp size = sizeUp.next();
        		printShopDao.createPrizes(custType.getId(), varietyTypeId, size.getId());
        	}	
    	}
	}
    
    @RequestMapping(value="/varietytype/remove/{varietyId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity deletevarietyType(@PathVariable("varietyId") String varietyId)
    {
    	VarietyType objVarietyType = new VarietyType();
    	objVarietyType.setId(varietyId);
    	printShopDao.deleteVarietyType(objVarietyType);
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/varietytype/edit/{varietyId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity editVarietyType(@PathVariable("varietyTypeId") String varietyTypeId, @RequestParam("name") String name,
    		@RequestParam("varietyId") String varId, @RequestParam("sizeId") String sizeId)
    {
    	VarietyType objVarietyType = new VarietyType();
    	objVarietyType.setName(name);
    	objVarietyType.setId(varietyTypeId);
    	
    	Variety variety = new Variety();
    	variety.setId(varId);
    	
    	SizeUp size = new SizeUp();
    	size.setId(sizeId);
    	Set<SizeUp> sizeSet = new HashSet<SizeUp>();
    	sizeSet.add(size);
    	
    	objVarietyType.setSizeUp(sizeSet);
    	objVarietyType.setVariety(variety);
    	
    	printShopDao.updateVarietyType(objVarietyType);
    	
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/varietytype/get/{varietyId}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<VarietyType> getVarietyType(@PathVariable("varietyId") String varietyId)
    {	
    	List<VarietyType> varTypeList = printShopDao.getVarietyTypes(varietyId);
    	
    	return varTypeList;
    }
    
    @RequestMapping(value="/sizeup/add", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createSizeUp(@RequestParam("name") String name,
    		@RequestParam("height") double height, @RequestParam("width") double width)
    {
    	SizeUp size = new SizeUp();
    	size.setName(name);
    	size.setHeight_up(height);
    	size.setWidth_up(width);
    	
    	printShopDao.createSizeUps(size);
    	
    	System.out.println("Customer entered is " + size.getName());
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/sizeup/remove/{sizeId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity deleteSizeUp(@PathVariable("sizeId") String sizeId)
    {
    	SizeUp size = new SizeUp();
    	size.setId(sizeId);
    	printShopDao.deleteSizeUp(size);
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/sizeup/edit/{sizeId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity editSizeUp(@PathVariable("sizeId") String sizeId, @RequestParam("name") String name,
    		@RequestParam("height") double height, @RequestParam("width") double width)
    {
    	SizeUp size = new SizeUp();
    	size.setId(sizeId);
    	size.setName(name);
    	size.setHeight_up(height);
    	size.setWidth_up(width);
    	
    	System.out.println("Customer id: " + size.getId());
    	System.out.println("Customer Name: " + size.getName());
    	
    	printShopDao.updateSizeUp(size);
    	
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/varietytype/sizeup/{varietyTypeId}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Set<SizeUp> getSizeUpsForVarietyType(@PathVariable("varietyTypeId") String varietyTypeId)
    {	
    	Set<SizeUp> sizeUpSet = printShopDao.getSizeUpsForVarietyType(varietyTypeId);
    	
    	return sizeUpSet;
    }
    
    @RequestMapping(value="/numberofups/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody int getNumberOfUps(@RequestBody NumberOfUpJsonVO jsonVO)
    {	
    	SizeUp size = new SizeUp();
    	size.setId(jsonVO.getSizeId());
    	size.setHeight_up(jsonVO.getHeight_up());
    	size.setWidth_up(jsonVO.getWidth_up());
    	int noOfUps = printShopDao.getNumberOfUPS(size, jsonVO.getWidth(), jsonVO.getHeight());
    	
    	return noOfUps;
    }
    
    @RequestMapping(value="/pricing/update/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> updatePricing(@RequestBody PriceJSONVO priceVO)
    {
    	printShopDao.updatePricing(priceVO);
    	
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/pricing/calculateprice/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody CalculatorResult calculatePricing(@RequestBody PriceCalcVO priceVO)
    {
    	CalculatorResult calcPrice = printShopDao.calculatePrice(priceVO);
    	
        return calcPrice;
    }
    
    @RequestMapping(value="/extras/baseprice/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createBasePricing(@RequestBody BasePriceVO basePriceVO)
    {
    	ExtraType extraType = new ExtraType();
    	
    	BasePrice price = new BasePrice();
    	price.setPrice(basePriceVO.getBasePrice());
    	price.setExtraItemType(basePriceVO.getExtraItemType());
    	
    	extraType.setCustomerTypeName(basePriceVO.getCustomerType());
    	extraType.setName(basePriceVO.getExtraItem());
    	extraType.setParentExtraName(basePriceVO.getExtraParentType());
    	extraType.setExtraTypePrice(price);
    	
    	printShopDao.createExtraType(extraType);
    	
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/extras/multiprice/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createMultiPricing(@RequestBody MultiPriceVO multiPriceVO)
    {
    	ExtraType extraType = new ExtraType();
    	
    	MultiPrice price = new MultiPrice();
    	price.setExtraItemType(multiPriceVO.getExtraItemType());
    	price.setHighThresholdPrice(multiPriceVO.getHighPrice());
    	price.setLowThresholdPrice(multiPriceVO.getLowPrice());
    	price.setThresholdQty(multiPriceVO.getThresholdQty());
    	
    	extraType.setCustomerTypeName(multiPriceVO.getCustomerType());
    	extraType.setName(multiPriceVO.getExtraItem());
    	extraType.setParentExtraName(multiPriceVO.getExtraParentType());
    	extraType.setExtraTypePrice(price);
    	
    	printShopDao.createExtraType(extraType);
    	
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/extras/qtyprice/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createQuantitativePricing(@RequestBody QuantitativePriceVO qtyPriceVO)
    {
    	ExtraType extraType = new ExtraType();
    	
    	QuantitativePrice price = new QuantitativePrice();
    	price.setExtraItemType(qtyPriceVO.getExtraItemType());
    	price.setMinimumPrice(qtyPriceVO.getMinPrice());
    	price.setMinimumQty(qtyPriceVO.getMinQty());
    	price.setPricePerItem(qtyPriceVO.getPricePerItem());
    	
    	extraType.setCustomerTypeName(qtyPriceVO.getCustomerType());
    	extraType.setName(qtyPriceVO.getExtraItem());
    	extraType.setParentExtraName(qtyPriceVO.getExtraParentType());
    	extraType.setExtraTypePrice(price);
    	
    	printShopDao.createExtraType(extraType);
    	
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/extras/persetprice/", method=RequestMethod.POST, headers={"Content-type=application/json"}, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> createPricePerSet(@RequestBody SetPriceVO setPriceVO)
    {
    	ExtraType extraType = new ExtraType();
    	
    	PricePerSet price = new PricePerSet();
    	price.setExtraItemType(setPriceVO.getExtraItemType());
    	price.setItemPerSet(setPriceVO.getItemPerSet());
    	price.setPricePerSet(setPriceVO.getPricePerSet());
    	
    	extraType.setCustomerTypeName(setPriceVO.getCustomerType());
    	extraType.setName(setPriceVO.getExtraItem());
    	extraType.setParentExtraName(setPriceVO.getExtraParentType());
    	extraType.setExtraTypePrice(price);
    	
    	printShopDao.createExtraType(extraType);
    	
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    @RequestMapping(value="/extras/remove/{extraId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody ResponseEntity<Boolean> deleteExtra(@PathVariable("extraId") String extraId)
    {
    	ExtraType extraType = new ExtraType();
    	extraType.setId(extraId);
    	printShopDao.deleteExtraType(extraType);
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
//    @RequestMapping(value="/extras/get/{customerType}", method=RequestMethod.POST, produces="application/json")
//    public @ResponseBody ResponseEntity<Boolean> getExtrasForCustomer(@PathVariable("customerType") String customerType)
//    {
//    	printShopDao.getExtrasForCustomer(customerType);
//    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
//    }
}
