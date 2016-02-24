package service;

import api.api.ICartMasterLogicService;
import api.dto.CartMasterDTO;
import logic.CartMasterLogicService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Mauricio on 3/23/15.
 */
@Path("/CartMaster")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartMasterService {

    @Inject
    protected CartMasterLogicService cartLogicService;

    @POST
    public CartMasterDTO createCart(CartMasterDTO cart) {
        return cartLogicService.createMasterCart(cart);
    }

    @DELETE
    @Path("{id}")
    public void deleteCart(@PathParam("id") Long id) {
        cartLogicService.deleteMasterCart(id);
    }

    @GET
    @Path("{id}")
    public CartMasterDTO getCart(@PathParam("id") Long id) {
        return cartLogicService.getMasterCart(id);
    }

    @PUT
    @Path("{id}")
    public void updateCart(@PathParam("id") Long id, CartMasterDTO cart) {
        cartLogicService.updateMasterCart(cart);
    }

    @GET
    @Path("e")
    public String getCart() {
        return System.getenv("Product");
    }
}
