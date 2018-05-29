package ru.sfedu.logisticsrest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author max
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ru.sfedu.logisticsrest.CustomerService.class);
        resources.add(ru.sfedu.logisticsrest.DriverService.class);
    }

}
