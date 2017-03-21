package com.calogardev.pizzarella.service;

import java.util.List;

/**
 * Contains non-bussiness functionality that can be applied to all services.
 * 
 * @author calogar
 *
 */
public interface UtilsService {

    /**
     * Transforms a S object into a T object
     * 
     * @param source
     *            The object to transform
     * 
     * @param destinationClass
     *            The class of the desired object
     * @return The transformed object
     */
    <T, S> T transform(S source, Class<T> destinationClass);

    /**
     * Transforms a list of S objects into a list of T objects
     * 
     * @param sources
     *            The objects to transform
     * @param destinationClass
     *            The class of the desired objects
     * @return A list of transformed objects
     */
    <T, S> List<T> transform(List<S> sources, Class<T> destinationClass);
}
