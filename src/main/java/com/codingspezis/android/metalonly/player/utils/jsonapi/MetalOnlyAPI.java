package com.codingspezis.android.metalonly.player.utils.jsonapi;

import org.androidannotations.annotations.rest.*;
import org.androidannotations.api.rest.*;
import org.springframework.http.converter.json.*;

/**
 * Interface to generate the REST-Client
 */
@Rest(converters = MappingJackson2HttpMessageConverter.class, rootUrl = "http://metal-only.de/botcon/mob.php?action=")
interface MetalOnlyAPI extends RestClientErrorHandling, RestClientSupport {

    /**
     * Requests the show's stats
     *
     * @return the show's stats
     */
    @Get("stats")
    @Accept(MediaType.APPLICATION_JSON)
    Stats getStats();

    /**
     * Requests this week's sending plan
     *
     * @return this week's sending plan
     */
    @Get("plannew")
    @Accept(MediaType.APPLICATION_JSON)
    Plan getPlan();

    /**
     * Requests this week's sending plan including stats
     *
     * @return this week's sending plan including stats
     */
    @Get("all")
    @Accept(MediaType.APPLICATION_JSON)
    PlanWithStats getPlanWithStats();

}