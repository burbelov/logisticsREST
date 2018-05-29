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
import ru.sfedu.logistics.entities.Customer;
import ru.sfedu.logistics.entities.Orders;

/**
 * REST Web Service
 *
 * @author max
 */
@Path("customer")
public class CustomerService {

    Business business = new Business();
    
    /**
     * Creates a new instance of CustomerService
     */
    public CustomerService() {
    }

    @POST
    @Path("create-or-update-customer")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createCustomer(Customer customer) {
        Result result = business.saveOrUpdateCustomer(customer);
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
    @Path("delete-customer/{customerId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCustomer(@PathParam("customerId") long customerId) {
        Result result = business.deleteCustomer(customerId);
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
    @Path("get-customer-by-id/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("customerId") long customerId) {
        Result result = business.getCustomerById(customerId);
        switch(result.getStatus()) {
            case SUCCESS:
                return (Customer) result.getData().get(0);
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("get-orders-by-customer-id/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getOrdersByCustomerId(@PathParam("customerId") long customerId) {
        Result result = business.getCustomerById(customerId);
        switch(result.getStatus()) {
            case SUCCESS:
                return ((Customer) result.getData().get(0)).getOrders();
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("get-customer-by-login/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerByLogin(@PathParam("login") String login) {
        Result result = business.getCustomerByLogin(login);
        switch(result.getStatus()) {
            case SUCCESS:
                return (Customer) result.getData().get(0);
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @POST
    @Path("create-or-update-order")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOrder(Orders order) {
        Result result = business.saveOrUpdateOrder(order);
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
    @Path("delete-order/{orderId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOrder(@PathParam("orderId") long orderId) {
        Result result = business.deleteOrder(orderId);
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
    @Path("get-order-by-id/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Orders getOrderById(@PathParam("orderId") long orderId) {
        Result result = business.getOrderById(orderId);
        switch(result.getStatus()) {
            case SUCCESS:
                return (Orders) result.getData().get(0);
            case NOT_FOUND:
                return null;
            case ERROR:
                break;
        }
        return null;
    }
    
    @GET
    @Path("complete-order/{orderId}/{customerId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response completeOrder(@PathParam("orderId") long orderId, @PathParam("customerId") long customerId) {
        Result result = business.completeOrder(orderId, customerId);
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
