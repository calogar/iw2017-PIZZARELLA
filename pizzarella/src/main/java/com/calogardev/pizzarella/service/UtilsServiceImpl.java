package com.calogardev.pizzarella.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UtilsService implementation that encapsulates the dozer mapping.
 * 
 * @author calogar
 *
 */
@Service
public class UtilsServiceImpl implements UtilsService {

    @Autowired
    private DozerBeanMapper dozer;

    @Override
    public <T, S> T transform(S source, Class<T> destinationClass) {
	return dozer.map(source, destinationClass);
    }

    @Override
    public <T, S> List<T> transform(List<S> sources, Class<T> destinationClass) {
	List<T> results = new ArrayList<>();
	for (S source : sources) {
	    results.add(dozer.map(source, destinationClass));
	}
	return results;
    }

}
