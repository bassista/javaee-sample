package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * ShipType entity REST facade. 
 * 
 * Fachada del recurso REST para la entidad ShipType.
 * 
 * @author Paco
 */
@ApplicationScoped
@Path("shiptype")
public class ShipTypeRestFacade extends CrudRestFacade<ShipType> {
    
    @Inject
    protected ShipTypeFacade shipTypeFacade;

    @Override
    public CrudFacade<ShipType> getCrudFacade() {
        return shipTypeFacade;
    }
        
}
