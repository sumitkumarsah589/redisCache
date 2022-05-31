package com.redis.sample.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.sample.entities.Invoice;
import com.redis.sample.exceptions.InvoiceNotFoundException;
import com.redis.sample.interfaces.InvoiceService;
import com.redis.sample.repo.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

	public Invoice saveInvoice(Invoice inv) {
		 return invoiceRepo.save(inv);
	}

	
	@CachePut(value="Invoice", key="#invId")
	public Invoice updateInvoice(Invoice inv, Integer invId) {
		Invoice invoice = invoiceRepo.findById(invId).orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
	       
		
	       return invoiceRepo.save(invoice);
	}

	
	 @CacheEvict(value="Invoice", key="#invId")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple entires to delete
	public void deleteInvoice(Integer invId) {
		  Invoice invoice = invoiceRepo.findById(invId)
		           .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
		       invoiceRepo.delete(invoice);
	}

	
	@Cacheable(value="Invoice", key="#invId")
	public Invoice getOneInvoice(Integer invId) {
		Invoice invoice = invoiceRepo.findById(invId)
		         .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
		       return invoice;
	}

	
	@Cacheable(value="Invoice")
	public List<Invoice> getAllInvoices() {
		return invoiceRepo.findAll();
	}

   
}
