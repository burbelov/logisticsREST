package ru.sfedu.logisticsrest;

import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.sfedu.logistics.business.Business;
import ru.sfedu.logistics.data_provider.Result;
import ru.sfedu.logistics.entities.Driver;
import ru.sfedu.logistics.entities.Orders;

/**
 * REST Web Service
 *
 * @author max
 */
@Path("driver")
public class DriverService {

    Business business = new Business();
    
    public DriverService() {
    }

    @POST
    @Path("create-or-update-driver")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createDriver(Driver driver) {
        Result result = business.saveOrUpdateDriver(driver);
        switch(result.getStatus()) {
            case SUCCESS:
                return Response.ok().build();
            case NOT_FOUND:
                break;
            case ERROR:
                return Response.status(400).entity(result.getMessages().get(0)).build();
        }
        return null;
    }
    
    @GET
    @Path("delete-driver/{driverId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDriver(@PathParam("driverId") long driverId) {
        Result result = business.deleteDriver(driverId);
        switch(result.getStatus()) {
            case SUCCESS:
                return Response.ok().build();
            case NOT_FOUND:
                return Response.status(400).build();
            case ERROR:
                return Response.status(400).entity(result.getMessages().get(0)).build();
        }
        return null;
    }
    
    @GET
    @Path("get-driver-by-id/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Driver getDriverById(@PathParam("driverId") long driverId) {
        Result result = business.getDriverById(driverId);
        switch(result.getStatus()) {
            case SUCCESS:
                return (Driver) result.getData().get(0);
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("get-orders-by-driver-id/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getOrdersByDriverId(@PathParam("driverId") long driverId) {
        Result result = business.getDriverById(driverId);
        switch(result.getStatus()) {
            case SUCCESS:
                return ((Driver) result.getData().get(0)).getOrders();
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("get-orders/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getOrders() {
        Result result = business.getOrders();
        switch(result.getStatus()) {
            case SUCCESS:
                return result.getData();
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("get-driver-by-login/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Driver getDriverByLogin(@PathParam("login") String login) {
        Result result = business.getDriverByLogin(login);
        switch(result.getStatus()) {
            case SUCCESS:
                return (Driver) result.getData().get(0);
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("take-order/{orderId}/{driverId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response takeOrder(@PathParam("orderId") long orderId, @PathParam("driverId") long driverId) {
        Result result = business.takeOrder(orderId, driverId);
        switch(result.getStatus()) {
            case SUCCESS:
                return Response.ok().build();
            case NOT_FOUND:
                return Response.status(400).build();
            case ERROR:
                return Response.status(400).entity(result.getMessages().get(0)).build();
        }
        return null;
    }
    
    @GET
    @Path("cancel-order/{orderId}/{driverId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cancelOrder(@PathParam("orderId") long orderId, @PathParam("driverId") long driverId) {
        Result result = business.cancelOrder(orderId, driverId);
        switch(result.getStatus()) {
            case SUCCESS:
                return Response.ok().build();
            case NOT_FOUND:
                return Response.status(400).build();
            case ERROR:
                return Response.status(400).entity(result.getMessages().get(0)).build();
        }
        return null;
    }

}