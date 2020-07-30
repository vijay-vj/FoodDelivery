package com.ee.demo.workerslayer;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.ee.demo.models.Order;

@Component
public interface OrderRepository extends CrudRepository<Order, String> {
 
	Order findOneByorderId(String username);

}