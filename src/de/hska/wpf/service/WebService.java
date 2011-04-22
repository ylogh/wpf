package de.hska.wpf.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface WebService {

	/**
	 * 
	 * @param countryCode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    public List<String> getWeatherInfoByCity(String city) throws UnsupportedEncodingException ;
}
