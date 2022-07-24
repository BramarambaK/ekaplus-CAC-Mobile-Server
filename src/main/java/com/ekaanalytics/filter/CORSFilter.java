package com.ekaanalytics.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class is to validate the Cross Origin Resource Sharing. If any request comes from unknown origin
 * this filter not allow to access APIs.
 * 
 * @author abhimanyu
 *
 */

public class CORSFilter implements Filter {
	
	private static final Log LOGGER = LogFactory.getLog(CORSFilter.class);

	private static final String ORIGIN_HEADER = "Origin";

	private String[] excludedURIS = null;
	
	private String[] excludedOrigins = null;

	public static final String EXCLUDED_URIS_KEY = "EXCLUDED_URIS";
	
	public static final String EXCLUDED_ORIGINS_KEY = "EXCLUDED_ORIGINS";
	
	/**
	 * The Access-Control-Allow-Origin header indicates whether a resource can be
	 * shared based by returning the value of the Origin request header in the
	 * response.
	 */
	public static final String RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin"; 

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("CORSFilter: Filter Initialized!!!");
		String nonOAuthURIS = filterConfig.getInitParameter(EXCLUDED_URIS_KEY);
		if (StringUtils.isNotBlank(nonOAuthURIS)) {
			excludedURIS = nonOAuthURIS.split(",");
		}
		String excludedOriginsString = filterConfig.getInitParameter(EXCLUDED_ORIGINS_KEY);
		if (StringUtils.isNotBlank(excludedOriginsString)) {
			excludedOrigins = excludedOriginsString.split(",");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		String origin = httpReq.getHeader(ORIGIN_HEADER);
		String requestURI = httpReq.getRequestURI();
		String allowedOrigin = getDefaultAllowedOrigin(httpReq);
		
		if (!isExclude(requestURI) && StringUtils.isNotEmpty(origin) && (!isExcludeOrigin(origin))&& !origin.contains(allowedOrigin)) {
			printLog(httpReq, origin, allowedOrigin);
			LOGGER.warn("CORSFilter: Origin and Allowed Origin request"
					+ " headers dont contain the same domain so we block the request !");
			httpResp.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Request (error code: CORS002)");
			return;
		} 
		httpResp.addHeader(RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, getDefaultAllowedOrigin(httpReq)); 
		chain.doFilter(request, response);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
		LOGGER.info("CORSFilter: Filter shutdown");
	}

	private boolean isExclude(String uri) {
		boolean isExcluded = Boolean.FALSE;
		if (ArrayUtils.isNotEmpty(excludedURIS)) {
			for (String excludeURI : excludedURIS) {
				if (StringUtils.equals(StringUtils.trim(excludeURI), uri)) {
					isExcluded = Boolean.TRUE;
					break;
				}
			}
		}
		return isExcluded;
	}
	
	private boolean isExcludeOrigin(String origin) {
		boolean isExcluded = Boolean.FALSE;
		if (ArrayUtils.isNotEmpty(excludedOrigins)) {
			for (String excludeOrigin : excludedOrigins) {
				if (StringUtils.equals(StringUtils.trim(excludeOrigin), origin)) {
					isExcluded = Boolean.TRUE;
					break;
				}
			}
		}
		return isExcluded;
	}

	private String getDefaultAllowedOrigin(HttpServletRequest request) {
		return UriComponentsBuilder.newInstance().scheme(request.getScheme()).host(request.getServerName()).build()
				.toString();
	}

	private void printLog(HttpServletRequest request, String origin, String allowedOrigin) {
		LOGGER.warn("Origin :" + origin);
		LOGGER.warn("Allowed Origin :" + allowedOrigin);
		LOGGER.warn("Complete URI :" + getCompleteURI(request));
	}

	private String getCompleteURI(HttpServletRequest request) {
		return UriComponentsBuilder.newInstance().scheme(request.getScheme()).host(request.getServerName())
				.port(request.getServerPort()).path(request.getRequestURI()).build().toString();
	}

}
