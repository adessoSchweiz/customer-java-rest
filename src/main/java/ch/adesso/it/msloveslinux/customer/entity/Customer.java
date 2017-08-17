package ch.adesso.it.msloveslinux.customer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NoArgsConstructor
@Entity
@Data
@NamedQueries({
        @NamedQuery(name = "findAll", query = "SELECT c FROM Customer c")
})
public class Customer {
    public static final class PROPERTY_KEYS {
        public static final String NAME = "name";
    }

    @Id
    private String name;

    public Customer(JsonObject json) {
        this.name = json.getString(PROPERTY_KEYS.NAME);
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add(PROPERTY_KEYS.NAME, name)
                .build();
    }
}
