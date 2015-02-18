package com.print.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.print.domain.CustomerType;


public interface PrintRepository extends PagingAndSortingRepository<CustomerType, Long> {

}
