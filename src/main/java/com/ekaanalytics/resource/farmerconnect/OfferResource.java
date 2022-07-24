package com.ekaanalytics.resource.farmerconnect;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.resource.BaseResource;
import com.ekaanalytics.resource.UriConstants;
import com.ekaanalytics.webclient.BaseHttpClient;

@RestController
@RequestMapping("/offers")
public class OfferResource extends BaseResource{
	
	@Autowired
	private BaseHttpClient httpClient;
	
	/**
	 * Fetches allowed values(configured via the Collection) for the specified field.
	 * 
	 * @param fieldId - Supported Values: location,quantityUnit,quality,product,incoTerm,priceUnit
	 * @return
	 */
	@GetMapping(value="/field/{fieldId}/values", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getOfferFieldValues(@PathVariable String fieldId,
												 	  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFERS_URI) + "/field/" + fieldId + "/values");
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											Object.class);
	}
	
	/**
	 * Fetches allowed values(configured via the Collection(s)) for the specified field(s).
	 * 
	 * @param fieldIds - Sublist of: location,quantityUnit,quality,product,incoTerm,priceUnit
	 * @return
	 */
	@GetMapping(value="/fields/{fieldIds}/values", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getMultipleOfferFieldValues(@PathVariable String fieldIds,
												 			  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFERS_URI) + "/fields/" + fieldIds + "/values");
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											Object.class);
	}

	/**
	 * Publishes an offer on behalf of the current user. 
	 * Important: Invoking this API successfully would
	 * make the FarmerConnect app paradigm user-based.  
	 * 
	 * @param inputBid Example:
 * 			{
		    "product": "Wheat",
		    "quality": "Top Quality",
		    "cropYear": "2018/2019",
		    "location": "BLR",
		    "publishedPrice": 300,
		    "expiryDateISOString": "2019-08-31T05:47:36.785Z",
		    "priceUnit": "USD/MT",
		    "incoTerm": "ABC",
		    "quantity": 10,
		    "quantityUnit": "MT",
		     --------- optional fields ------
		    "shipmentDateISOString": "2019-12-31T05:47:36.785Z",
		    "rolesToPublish": "",
		    "offerType": "Purchase"
			}
	 * @return HttpStatus - 201 With {"bidId:"systemGeneratedBidId"}
	 */
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createOffer(@RequestBody String inputBid,
											  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFERS_URI));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											inputBid,
											headers,
											Object.class);
	}
	
	/**
	 * Update a published offer.
	 * @param bidId
	 * @param updatedFields one or more of the below fields:
	 * 		{
		    "product": "Wheat",
		    "quality": "Top Quality",
		    "cropYear": "2018/2019",
		    "location": "BLR",
		    "publishedPrice": 300,
		    "expiryDateISOString": "2019-08-31T05:47:36.785Z",
		    "priceUnit": "USD/MT",
		    "incoTerm": "ABC",
		    "quantity": "10",
		    "quantityUnit": "MT",
		    "shipmentDateISOString": "2019-12-31T05:47:36.785Z",
		    "rolesToPublish": ""
			}
			To unset a non-mandatory field, specify value as empty string(for numeric values as well)
	 * 
	 * @return HttpStatus - 200 With Empty Response 
	 */
	@PutMapping(value = "/{bidId}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateOffer(@PathVariable String bidId,
											  @RequestBody String updatedFields,
											  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFERS_URI + "/" + bidId));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.PUT,
											updatedFields,
											headers,
											Object.class);
	}

	/**
	 * Delete a published offer.
	 * 
	 * @param bidId
	 * @param httpRequest
	 * @return
	 */
	@DeleteMapping(value = "/{bidId}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> deleteOffer(@PathVariable String bidId,
											  HttpServletRequest httpRequest) {
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(httpRequest, 
															 UriConstants.OFFERS_URI + "/" + bidId));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.DELETE,
											null,
											headers,
											Object.class);
	}
}