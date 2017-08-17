package ch.adesso.it.msloveslinux.health.boundary;

import ch.adesso.it.msloveslinux.customer.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("health")
public class HealthResource {

    @PersistenceContext
    EntityManager entityManager;

    @GET
    public Object health() {
        return entityManager.createNamedQuery("findAll", Customer.class);
    }

}
