package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Non CDI life cycle managed bean example. Java EE 7 has a CDI ViewScoped annotation.
 * 
 * Ejemplo de ciclo de vida estándar JSF, no CDI. Java EE 7 tiene este ciclo de vida como CDI.
 * 
 * @author Paco Saucedo
 */
@ManagedBean
@ViewScoped
public class Countries extends AbstractController<Country> implements Serializable {

    @Inject
    private Logger logger;
    
    @Inject
    private CountryFacade facade;

    @Override
    protected void refreshData() {
        setElements(getFacade().findAll());
        setFilteredElements(null);
    }

    @Override
    protected Country getNewEntity() {
        return new Country();
    }

    @Override
    protected CrudFacade getFacade() {
        return facade;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
