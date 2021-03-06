package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.test.JavaEESampleTestDeployment;
import java.util.Calendar;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test with Arquillian for the Ship entity facade
 * 
 * Test de integración con Arquillian para la fachada de la entidad Ship
 * 
 * @author Paco Saucedo
 */
@RunWith(Arquillian.class)
public class ShipFacadeTest {
    
    private static final Long SHIP_ID = -2L;
    private static final String SHIP_NAME = "ALBAYCIN";
    private static final String FILTER = "%M%";
    private static final String FIRST_SHIP_IN_FILTERED_LIST = "Caroline Maersk";
    private static final Integer SHIP_LIST_SIZE = 5;
    private static final Integer RANGE_SHIP_LIST_SIZE = 3;
    private static final Integer FILTERED_SHIP_LIST_SIZE = 3;
    
    @Inject
    private ShipFacade shipFacade;
    
    @Inject
    private ShipTypeFacade shipTypeFacade;
    
    @Inject
    private CountryFacade countryFacade;
    
    @Deployment
    public static WebArchive deployment() {
        return JavaEESampleTestDeployment.deployment()                
                .addPackage(com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship.class.getPackage())
                .addPackage(com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade.class.getPackage());
    }

    @Test
    @InSequence(2)
    public void testCreate() throws Exception {
        ShipType containerShip = shipTypeFacade.find("CON");
        Country spain = countryFacade.find("ES");        
        shipFacade.create(new Ship(9703291, 
                "MSC Oscar",
                192237,
                Calendar.getInstance().getTime(),
                spain,
                containerShip
        ));
    }

    @Test
    @InSequence(3)
    public void testUpdate() throws Exception {
        Ship ship = shipFacade.find(SHIP_ID);
        ship.setName(SHIP_NAME);
        shipFacade.update(ship);
    }

    @Test
    @InSequence(4)
    public void testFind() throws Exception {
        Assert.assertEquals("Wrong ship found",
                SHIP_NAME,
                shipFacade.find(SHIP_ID).getName());
    }

    @Test
    @InSequence(5)
    public void testRemove() throws Exception {
        Ship ship = shipFacade.find(SHIP_ID);
        shipFacade.remove(ship);
        Assert.assertEquals("No ship expected after removing the item",
                null,
                shipFacade.find(SHIP_ID));
    }

    @Test
    @InSequence(1)
    public void testFindAll() throws Exception {
        Assert.assertEquals("Wrong number of ships found",
                SHIP_LIST_SIZE,
                (Integer) shipFacade.findAll().size());
    }

    @Test
    @InSequence(1)
    public void testFindRange() throws Exception {
        Assert.assertEquals("Wrong number of ships found in range query",
                RANGE_SHIP_LIST_SIZE,
                (Integer) shipFacade.findRange(new int[] {0, 2}).size());
    }

    @Test
    @InSequence(1)
    public void testFindByFilter() throws Exception {
        Assert.assertEquals("Wrong number of ships found in no filter query",
                SHIP_LIST_SIZE,
                (Integer) shipFacade.findByFilter("%").size());
        Assert.assertEquals("Wrong number of ships found in filtered query",
                FILTERED_SHIP_LIST_SIZE,
                (Integer) shipFacade.findByFilter(FILTER).size()); 
        Assert.assertEquals("Wrong order of ships found in filtered query",
                FIRST_SHIP_IN_FILTERED_LIST,
                shipFacade.findByFilter(FILTER).get(0).getName());        
    }

    @Test
    @InSequence(1)
    public void testCount() throws Exception {
        Assert.assertEquals("Wrong count of ships",
                SHIP_LIST_SIZE,
                (Integer) shipFacade.count());
    }
    
}
