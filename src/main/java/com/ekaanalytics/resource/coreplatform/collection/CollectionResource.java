package com.ekaanalytics.resource.coreplatform.collection;

import static com.ekaanalytics.resource.UriConstants.COLLECTIONS_ID_COLUMN_NAME_MAP_URI;
import static com.ekaanalytics.resource.UriConstants.COLLECTIONS_QUICK_EDIT_INFO_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.resource.BaseResource;

@RestController
@RequestMapping("/collections")
public class CollectionResource extends BaseResource{

	@RequestMapping(value="/{collectionId}/quick-edit-info", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCollectionQuickEditInfo(@PathVariable String collectionId,
											HttpServletRequest request){
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, 
															 String.format(COLLECTIONS_QUICK_EDIT_INFO_URI, collectionId)));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	@RequestMapping(value="/{collectionId}/column-map", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCollectionIdColumnNameMap(@PathVariable String collectionId,
											HttpServletRequest request){
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
												.fromHttpUrl(getURIForCACWebRequest(request, 
															 String.format(COLLECTIONS_ID_COLUMN_NAME_MAP_URI, collectionId)));
		
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.GET,
											null,
											headers,
											String.class);
	}
	
	
}
