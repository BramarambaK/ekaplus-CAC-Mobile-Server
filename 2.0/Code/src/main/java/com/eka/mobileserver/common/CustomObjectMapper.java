package com.eka.mobileserver.common;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Component
public class CustomObjectMapper extends ObjectMapper{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
        super();
        this.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
	
	

}
