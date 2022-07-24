package com.ekaanalytics.resource.advanceanalytics;

import static com.ekaanalytics.resource.UriConstants.ANALYTICS_URI;
import static com.ekaanalytics.resource.UriConstants.REMAINING_COUNT_URI;
import static com.ekaanalytics.resource.UriConstants.RESPONSES_BY_COUNT_USER;
import static com.ekaanalytics.resource.UriConstants.VALIDATE_IMAGE_NAME_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekaanalytics.common.config.ServerConfiguration;
import com.ekaanalytics.resource.BaseResource;

@RestController
@RequestMapping("/analytics")
public class ImageUploadResource extends BaseResource {

	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<List<String>> uploadImage(@RequestParam("files") List<MultipartFile> files,
													@RequestParam Map<String, String> params,
												    HttpServletRequest httpRequest) throws Exception {/*
		
		
		HttpHeaders headers = getRequestHeaders(httpRequest);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		UriComponentsBuilder uriBuilder = getUriBuilder(httpRequest, IMAGE_UPLOAD_URI, params);
		List<FileSystemResource> fsrList = new ArrayList<>(files.size());
		//for (MultipartFile multipartFile : files) {
			String name = "D:/temp/"+files.get(0).getOriginalFilename();
			FileOutputStream os = new FileOutputStream(name);
			os.write(files.get(0).getBytes());
			os.close();
			
		FileSystemResource file = new FileSystemResource(new File(name));
				fsrList.add(file);
		//}
		MultiValueMap<String, Object> requestParts = new LinkedMultiValueMap<>();
		requestParts.add("files", file);
		
		
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
	      bodyMap.add("files", file);
	      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

	      SSLContext context = SSLContext.getInstance("TLSv1.2");
          context.init(null, null, null);
          CloseableHttpClient httpClient = HttpClientBuilder
                  .create().setSslcontext(context)
                  .build();
	      
	      HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	        httpRequestFactory.setConnectionRequestTimeout(0);
	        httpRequestFactory.setConnectTimeout(0);
	        httpRequestFactory.setReadTimeout(0);
	        //httpRequestFactory.setBufferRequestBody(false);
	        
	        
	      RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
	      ResponseEntity<String> response = restTemplate.exchange(uriBuilder.build().encode().toUri(),
	              HttpMethod.POST, requestEntity, String.class);
		
		
		 httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(),
											HttpMethod.POST,
											requestParts,
											headers,
											(Class<List<String>>) Collections.<String>emptyList().getClass());
	*/
		return null;
		}
	
	@RequestMapping(method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getResponsesByCount(HttpServletRequest request, 
													  @RequestParam Map<String, String> params) {

		// TODO:: When Android App is updated this value should be passed from client
		params.put("count", ServerConfiguration.imagesCountForDIApp);
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, RESPONSES_BY_COUNT_USER, params);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  String.class);
	}
	
	@RequestMapping(value = "/{requestIds}", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getResponsesByRequestIds(HttpServletRequest request, 
														  @PathVariable String requestIds,											   
														  @RequestParam Map<String, String> params) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, 
														String.format(ANALYTICS_URI, requestIds), 
														params);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  String.class);
	}
	
	
	@RequestMapping(value = "/{requestId}", method=RequestMethod.PUT, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateFeedback(HttpServletRequest request,
												@PathVariable String requestId,
												@RequestBody String feedbackJson) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, 
														String.format(ANALYTICS_URI, requestId), 
														null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										 HttpMethod.PUT, 
										 feedbackJson, 
										 headers, 
										 String.class);
	}
	
	@RequestMapping(value = "/{requestId}", method=RequestMethod.DELETE, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(HttpServletRequest request,
										@PathVariable String requestId) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, 
														String.format(ANALYTICS_URI, requestId), 
														null);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
											HttpMethod.DELETE, 
											null, 
											headers, 
											String.class);
	}
	
	@RequestMapping(value = "/count", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getRemainingCount(HttpServletRequest request,
													@RequestParam Map<String, String> params) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, REMAINING_COUNT_URI, params);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  String.class);
	}
	
	@RequestMapping(value = "/validate", method=RequestMethod.GET, produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<String> validateDuplicateImageName(HttpServletRequest request,
															@RequestParam Map<String, String> params) {
		
		HttpHeaders headers = getRequestHeaders(request);
		UriComponentsBuilder uriBuilder = getUriBuilder(request, VALIDATE_IMAGE_NAME_URI, params);
		return httpClient.fireHttpRequest(uriBuilder.build().encode().toUri(), 
										  HttpMethod.GET, 
										  null, 
										  headers, 
										  String.class);
	}
}
