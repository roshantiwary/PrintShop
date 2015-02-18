package com.print.mvc;

//import com.mongodb.DB;
//import com.mongodb.MongoClient;
//import com.mongodb.WriteConcern;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.print.domain.CustomerType;
import com.print.domain.ExtraItemType;
import com.print.domain.ExtraType;
import com.print.domain.Member;
import com.print.domain.ParentExtraType;
import com.print.domain.Pricing;
import com.print.domain.SizeUp;
import com.print.domain.Variety;
import com.print.domain.VarietyType;
import com.print.repo.PrintShopDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class MemberController
{
    @Autowired
    private PrintShopDao printShopDao;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
        List<CustomerType> customers =  printShopDao.getAllCustomerTypes();
        model.addAttribute("customers", customers);
        
        List<Variety> varietys = printShopDao.getAllVarietys();
        model.addAttribute("varietys", varietys);
        
        Iterator<CustomerType> iterator = customers.iterator();
        Map<String, Object> customerExtraMap = new HashMap<String, Object>();
        while(iterator.hasNext()) {
        	CustomerType customerType = iterator.next();
        	Map<String, List<ExtraType>> extrasMap = printShopDao.getExtrasForCustomer(customerType.getName());
        	customerExtraMap.put(customerType.getName(), extrasMap);
        }
        
        model.addAttribute("customerExtraMap", customerExtraMap);
        
//        Map<String, List<VarietyType>> varietyTypeMap = printShopDao.getVarietyTypes();
//        model.addAttribute("varietyTypeMap", varietyTypeMap);
        
        return "index";
    }
   
    @RequestMapping(value="addcustomertype", method=RequestMethod.GET)
    public String displayCustomerPage(Model model)
    {   
    	List<CustomerType> customers = printShopDao.getAllCustomerTypes();
    	model.addAttribute("customers", customers);
        return "add_customertype";
    }
    
    @RequestMapping(value="addvariety", method=RequestMethod.GET)
    public String displayAddVarietyPage(Model model)
    {   
    	List<Variety> varietyList = printShopDao.getAllVarietys();
    	model.addAttribute("varietyList", varietyList);
        return "add_variety";
    }
    
    @RequestMapping(value="addvarietytype", method=RequestMethod.GET)
    public String displayAddVarietytypePage(Model model)
    {   
    	List<VarietyType> varietyTypeList = printShopDao.getAllVarietyTypes();
    	model.addAttribute("varietyTypeList", varietyTypeList);
    	
    	List<SizeUp> sizeList = printShopDao.getSizeUps();
    	model.addAttribute("sizeUpList", sizeList);
    	
    	List<Variety> varietyList = printShopDao.getAllVarietys();
    	model.addAttribute("varietyList", varietyList);
    	
        return "add_variety_type";
    }
    
    @RequestMapping(value="addsize", method=RequestMethod.GET)
    public String displayAddSizePage(Model model)
    {   
    	List<SizeUp> sizeUpList = printShopDao.getSizeUps();
    	model.addAttribute("sizeUpList", sizeUpList);
        return "add_size";
    }
    
    @RequestMapping(value="addextras", method=RequestMethod.GET)
    public String displayAddExtraPage(Model model)
    {   
    	List<CustomerType> customers =  printShopDao.getAllCustomerTypes();
        model.addAttribute("customers", customers);
    	
    	List<ParentExtraType> parentExtraList = printShopDao.getAllParentExtraTypes();
    	model.addAttribute("parentExtraList", parentExtraList);
    	
    	List<ExtraItemType> extraItemList = printShopDao.getAllExtraItemTypes();
    	model.addAttribute("extraItemList", extraItemList);
    	
        return "add_extras";
    }
    
    @RequestMapping(value="showextras", method=RequestMethod.GET)
    public String showExtraPage(Model model)
    {   
    	
    	List<ExtraType> extraList = printShopDao.getAllExtraTypes();
    	model.addAttribute("extraList", extraList);
    	
        return "show_extras";
    }
    
    @RequestMapping(value="admin", method=RequestMethod.GET)
    public String displayAdminPage(Model model)
    {   
        return "admin";
    }
    
    @RequestMapping(value="addpricing", method=RequestMethod.GET)
    public String displayPricingPage(Model model)
    {   
    	List<Pricing> priceList = printShopDao.getPriceList();
    	model.addAttribute("priceList", priceList);
        return "add_pricing_for_variety_type";
    }
    
//    @RequestMapping(value="customertype",method=RequestMethod.GET)
//    public String getCustomerType(Model model)
//    {
//        model.addAttribute("newMember", new Member());
////        model.addAttribute("members", memberDao.findAllOrderedByName());
//        model.addAttribute("members", null);
//
//        return "index";
//    }

//    @RequestMapping(method=RequestMethod.POST)
//    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model)
//    {
//        if (!result.hasErrors()) {
////            memberDao.register(newMember);
//            return "redirect:/";
//        }
//        else {
////            model.addAttribute("members", memberDao.findAllOrderedByName());
//            model.addAttribute("members", null);
//            return "index";
//        }
//    }
}
