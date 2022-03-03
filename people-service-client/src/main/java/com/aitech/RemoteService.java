package com.aitech;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri="http://localhost:8081")
public interface RemoteService {
    

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String getRemoteHello();
}
