package com.ee.demo.icontrollers.isentrycontroller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ee.demo.iwebhandlers.ResponseWrapper;
import com.ee.demo.models.Order;
import com.ee.demo.models.Restaurant;
import com.ee.demo.workerslayer.RestaurantRepository;

@RestController
@RequestMapping("/guard")
public class SentryGuardController {
	private Logger logger = LoggerFactory.getLogger(SentryGuardController.class);
	ResponseWrapper jResponseWrapper = null;

	@Autowired
	private Order order;

	@Autowired
	private RestaurantRepository restaurant;

	public HttpStatus isonResHTPPStat = null;

	@RequestMapping(method = RequestMethod.GET, value = "/searchResWithPin/{pincode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper searchResWithPin(HttpServletRequest request, @PathVariable String pincode)
			throws IOException {
		// Pass the Pincode to DB layer and get all restaurants in that area
		logger.debug("Entered into searchResWithPin with Pin: " + pincode);
		Iterable<Restaurant> restaurantResp = restaurant.findBypincode(pincode);

		jResponseWrapper = new ResponseWrapper(restaurantResp, HttpStatus.SERVICE_UNAVAILABLE);

		return jResponseWrapper;
		// return the list of Restaurants
		// return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchResWithLocation/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper searchResWithLocation(HttpServletRequest request, @PathVariable String location)
			throws IOException {
		// Pass the location to DB layer and get all restaurants within a predefined
		// radius -- We can use GeoSpatial Queries here
		logger.debug("Entered into searchResWithLocation with location: " + location);
		Iterable<Restaurant> restaurantResp = restaurant.findBylocation(location, 5); // a 5Km radius
		// return the list of Restaurants

		jResponseWrapper = new ResponseWrapper(restaurantResp, HttpStatus.SERVICE_UNAVAILABLE);
		return jResponseWrapper;
 	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchResWithPinRating/{pincode}/{rating}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper searchResWithPinRating(HttpServletRequest request, @PathVariable String pincode,
			@PathVariable String rating) throws IOException {
		// Pass the Pincode and rating with > to DB layer and get all restaurants in
		// that area
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchResWithLocationRating/{location}/{rating}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper searchResWithLocationRating(HttpServletRequest request, @PathVariable String location,
			@PathVariable String rating) throws IOException {
		// Pass the location and rating with > to DB layer and get all restaurants in
		// that area
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getOrderDetails/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper getOrderDetails(HttpServletRequest request, @PathVariable String orderId)
			throws IOException {

		// Get Order Details with an Order ID
		// System.out.println(orderRepository.findOneByorderId(orderId));
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getMenuItems/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper getMenuItems(HttpServletRequest request, @PathVariable String restaurantId)
			throws IOException {
		// Get Menu Items based on the restaurant Id

		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addToOrderCart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper addToOrderCart(HttpServletRequest request) throws IOException {

		// get food ID and quantity for a given mobileNumber

		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper placeOrder(HttpServletRequest request) throws IOException {

		// get the cart based on mobile number and place the Order

		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cancelOrder", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper cancleOrder(HttpServletRequest request) throws IOException {

		// check the order status based on mobile and orderId

		return null;
	}

}
 