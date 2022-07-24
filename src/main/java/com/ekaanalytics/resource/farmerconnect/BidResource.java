package com.ekaanalytics.resource.farmerconnect;

import static com.ekaanalytics.resource.UriConstants.REQUEST_PARAMS;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.resource.UriConstants;
import com.ekaanalytics.wrapper.coreplatform.farmerconnect.CustomerBid;
import com.ekaanalytics.wrapper.coreplatform.farmerconnect.PublishedBid;

/**
 * The Class BidResource.
 */
@RestController
@RequestMapping(produces=APPLICATION_JSON_UTF8_VALUE)
public class BidResource extends BaseResource{
	
	
	/**
	 * Gets the published bids.
	 *
	 * @param requestParams the request params
	 * @param httpRequest the http request
	 * @return the published bids
	 */
	@RequestMapping(value="/published-bids", method=RequestMethod.GET)
	public ResponseEntity<PublishedBid[]> getPublishedBids(@RequestParam String requestParams,
												   			HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.PUBLISHED_BIDS_API))
												.queryParam(REQUEST_PARAMS, requestParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											PublishedBid[].class);
	}
	
	/**
	 * Gets the bids for offeror.
	 *
	 * @param requestParams the request params
	 * @param httpRequest the http request
	 * @return the bids for offeror
	 */
	@RequestMapping(value="/bids/offeror", method=RequestMethod.GET)
	public ResponseEntity<CustomerBid[]> getBidsForOfferor(@RequestParam String requestParams,
															HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFEROR_BIDS_API))
												.queryParam(REQUEST_PARAMS, requestParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											CustomerBid[].class);
	}	
	
	/**
	 * Gets the my bids.
	 *
	 * @param requestParams the request params
	 * @param httpRequest the http request
	 * @return the my bids
	 */
	@RequestMapping(value="/bids/farmer", method=RequestMethod.GET)
	public ResponseEntity<CustomerBid[]> getMyBids(@RequestParam String requestParams,
															 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.FARMER_BIDS_API))
												.queryParam(REQUEST_PARAMS, requestParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											CustomerBid[].class);
	}
	
	/**
	 * Gets the farmer bids.
	 *
	 * @param requestParams the request params
	 * @param farmerId the farmer id
	 * @param httpRequest the http request
	 * @return the farmer bids
	 */
	@RequestMapping(value="/bids/farmer/{farmerId}", method=RequestMethod.GET)
	public ResponseEntity<CustomerBid[]> getFarmerBids(@RequestParam String requestParams,
														   @PathVariable String farmerId,
														   HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.FARMER_BIDS_API) + "/" + farmerId)
												.queryParam(REQUEST_PARAMS, requestParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											CustomerBid[].class);
	}	
	
	
	/**
	 * Gets the customer bid.
	 *
	 * @param refId the ref id
	 * @param httpRequest the http request
	 * @return the customer bid
	 */
	@RequestMapping(value="/bids/{refId}", method=RequestMethod.GET)
	public ResponseEntity<CustomerBid> getCustomerBid(@PathVariable String refId,
													  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API) + "/" + refId);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											CustomerBid.class);
	}
	
	/**
	 * Gets the customer bid logs.
	 *
	 * @param refId the ref id
	 * @param httpRequest the http request
	 * @return the customer bid logs
	 */
	@RequestMapping(value="/bids/logs/{refId}", method=RequestMethod.GET)
	public ResponseEntity<String> getCustomerBidLogs(@PathVariable String refId,
												 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API) + "/logs/" + refId);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	/**
	 * Creates the bid.
	 *
	 * @param inputBid the input bid
	 * @param httpRequest the http request
	 * @return the response entity
	 */
	@RequestMapping(value="/bids", method=RequestMethod.POST)
	public ResponseEntity<String> createBid(@RequestBody String inputBid,
											HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											inputBid,
											headers,
											String.class);
	}

	/**
	 * Creates the bid on farmer behalf.
	 *
	 * @param farmerId the farmer id
	 * @param inputBid the input bid
	 * @param httpRequest the http request
	 * @return the response entity
	 */
	@RequestMapping(value="/bids/{farmerId}", method=RequestMethod.POST)
	public ResponseEntity<String> createBidOnFarmerBehalf(@PathVariable Integer farmerId,
														  @RequestBody String inputBid,
														  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API + "/" + farmerId));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											inputBid,
											headers,
											String.class);
	}

	
	/**
	 * Update bid.
	 *
	 * @param refId the ref id
	 * @param updateBidParams the update bid params
	 * @param queryParams the query params
	 * @param httpRequest the http request
	 * @return the response entity
	 */
	@RequestMapping(value="/bids/{refId}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateBid(@PathVariable String refId,
											@RequestBody String updateBidParams,
											@RequestParam(required = false) MultiValueMap<String, String> queryParams,
											HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API) + "/" + refId)
												.queryParams(queryParams);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.PUT,
											updateBidParams,
											headers,
											String.class);
	}
	
	/**
	 * Mobile Endpoint for the Offerors to cancel an accepted bid.
	 * 
	 * @param refIds comma separated refIds
	 * @param cancellationInfo: {"remarks":"Cancellation Remarks"}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bids/cancel/{refIds}", method=RequestMethod.POST)
	public ResponseEntity<Object> cancelBids(@PathVariable String refIds,
											 @RequestBody Map<String, Object> cancellationInfo,
											 HttpServletRequest httpRequest) throws Exception{
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFEROR_BIDS_CANCEL_API) + refIds);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											cancellationInfo,
											headers,
											Object.class);	
	}

	
	/**
	 * Update bid on farmer behalf.
	 *
	 * @param refId the ref id
	 * @param farmerId the farmer id
	 * @param updateBidParams the update bid params
	 * @param httpRequest the http request
	 * @return the response entity
	 */
	@RequestMapping(value="/bids/{refId}/{farmerId}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateBidOnFarmerBehalf(@PathVariable String refId,
														  @PathVariable Integer farmerId,
														  @RequestBody String updateBidParams,
														  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API) + "/" + refId + "/" + farmerId);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.PUT,
											updateBidParams,
											headers,
											String.class);
	}
	
	
	/**
	 * Gets the customer bid unique values.
	 *
	 * @param fieldName the field name
	 * @param httpRequest the http request
	 * @return the customer bid unique values
	 */
	@RequestMapping(value="/bids/values/{fieldName}", method=RequestMethod.GET)
	public ResponseEntity<String> getCustomerBidUniqueValues(@PathVariable String fieldName,
												 			 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API) + "/values/" + fieldName);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}


	/**
	 * Gets the customer bid unique values.
	 *
	 * @param fieldName the field name
	 * @param farmerId the farmer id
	 * @param httpRequest the http request
	 * @return the customer bid unique values
	 */
	@RequestMapping(value="/bids/values/{fieldName}/{farmerId}", method=RequestMethod.GET)
	public ResponseEntity<String> getCustomerBidUniqueValues(@PathVariable String fieldName,
															 @PathVariable Integer farmerId,
												 			 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.BIDS_API) + "/values/" + fieldName + "/" + farmerId);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}

	
	/**
	 * Gets the published bid unique values.
	 *
	 * @param fieldName the field name
	 * @param httpRequest the http request
	 * @return the published bid unique values
	 */
	@RequestMapping(value="/published-bids/values/{fieldName}", method=RequestMethod.GET)
	public ResponseEntity<String> getPublishedBidUniqueValues(@PathVariable String fieldName,
												 			 HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.PUBLISHED_BIDS_API) + "/values/" + fieldName);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	/**
	 * Rate bid.
	 *
	 * @param refId the ref id
	 * @param rating the rating
	 * @param request the request
	 * @param httpRequest the http request
	 * @return the response entity
	 */
	@RequestMapping(value="/bids/ratings/{refId}/{rating}", method=RequestMethod.POST)
	public ResponseEntity<String> rateBid(@PathVariable String refId,
										  @PathVariable Integer rating,
										  @RequestBody String request,
										  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.RATINGS_API) +
															 "/" + refId + 
															 "/" + rating);
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											request,
											headers,
											String.class);
	}
	
	@GetMapping(value="/farmerconnect/general-settings", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAppGeneralSettings(HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.APP_SETTINGS_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											Object.class);
	}
}
