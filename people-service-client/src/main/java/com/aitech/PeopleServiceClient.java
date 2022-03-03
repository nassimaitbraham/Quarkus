package com.aitech;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/peopelService")
public class PeopleServiceClient {

    @RestClient
    RemoteService service;

    /**
     * If the call to the method fails, the call is retried three times before failing.
     * 
     * @return String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(
       maxRetries = 3
    )
    @Path("/helloCallWithRetry")
    public String helloCallWithRetry() {
        return service.getRemoteHello();
    }
    /**
     * Circuitbreaker functionality illustration
     * For four requests if 80% of requests fail, the circuit will open and wait 5 seconds to close.
     * 
     * @return String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.80, delay = 5000 )
    @Path("/helloCallWithCircuitBreaker")
    public String helloCallWithCircuitBreaker(){
        return service.getRemoteHello();
    }

    /**
     * illustration of the fallback functionality when a call to a method fails
     * 
     * @return String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Fallback(
        fallbackMethod = "fallback"
    )
    @Path("/helloCallWithFallBackMethod")
    public String helloCallWithFallBackMethod() {
        return service.getRemoteHello();
    }
   
    /**
     * Method called when the fallback is triggered
     * 
     * @return String
     */
    private String fallback(){
      return "Hello from fallback methode";
    }
}