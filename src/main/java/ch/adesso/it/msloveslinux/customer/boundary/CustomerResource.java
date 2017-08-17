package ch.adesso.it.msloveslinux.customer.boundary;

import ch.adesso.it.msloveslinux.customer.entity.Customer;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @PersistenceContext
    EntityManager entityManager;

    @POST
    public Response createCustomer(JsonObject customer, @Context UriInfo info) {
        JsonObject customerSaved = entityManager
                .merge(new Customer(customer))
                .toJson();
        URI uri = info.getAbsolutePathBuilder().path("/" + customerSaved.getString("name")).build();
        return Response
                .created(uri)
                .entity(customerSaved)
                .build();
    }

    @GET
    public JsonArray getCustomers() {
        JsonArrayBuilder customers = Json.createArrayBuilder();
        entityManager.createNamedQuery("findAll", Customer.class)
                .getResultList()
                .parallelStream()
                .forEach(c -> customers.add(c.toJson()));
        return customers.build();
    }

    @GET
    @Path("{name}")
    public JsonObject getCustomer(@PathParam("name") String name) {
        return entityManager.find(Customer.class, name).toJson();
    }

}
