package logic;

import api.api.ICartMasterLogicService;
import api.dto.CartMasterDTO;
import api.dto.common.CartDTO;
import api.dto.common.ItemDTO;
import persistence.CartMasterPersistence;
import persistence.api.ICartMasterPersistence;
import persistence.entity.CartitemEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.ServiceDirectory;

/**
 * Created by Mauricio on 3/23/15.
 */
//@Default
@Stateless
@LocalBean
public class CartMasterLogicService implements ICartMasterLogicService{
    
  
//    @Inject
//    protected ICartPersistence cartPersistance;
    @Inject
    protected CartMasterPersistence cartMasterPersistance;
//    @Inject
//    protected IItemPersistence itemPersistance;

//    @Requires
    public CartMasterDTO createMasterCart(CartMasterDTO cart) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target( ServiceDirectory.CART_HOST ).path("Cart/webresources/Cart/");
        CartDTO persistedCartDTO=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(cart.getCartEntity(), MediaType.APPLICATION_JSON), CartDTO.class);


//        @Require(host="http://localhost:8080/", name="Cart/webresources/Cart/")
//        @Require_Method('POST')
//        @Require_Parameter(cart.getCartEntity())
//        @Require_return(CartDTO)
//        CartDTO persistedCartDTO = cartPersistance.createCart(cart.getCartEntity());
        if (cart.getCreateitem() != null) {
            for (ItemDTO itemDTO : cart.getCreateitem()) {
                target = client.target( ServiceDirectory.ITEM_HOST ).path("Item/webresources/Item/");
                ItemDTO createdItemDTO=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(itemDTO,MediaType.APPLICATION_JSON),ItemDTO.class);
//                ItemDTO createdItemDTO= itemPersistance.createItem(itemDTO);
                CartitemEntity cartItemEntity = new CartitemEntity(persistedCartDTO.getId(), createdItemDTO.getId());
                cartMasterPersistance.createCartitemEntity(cartItemEntity);
            }
        }
        // update item
        if (cart.getUpdateitem() != null) {
            for (ItemDTO itemDTO : cart.getUpdateitem()) {
                target = client.target( ServiceDirectory.ITEM_HOST ).path("Item/webresources/Item/");

                target.request(MediaType.APPLICATION_JSON).put(Entity.entity(itemDTO,MediaType.APPLICATION_JSON));
//                itemPersistance.updateItem(itemDTO);
                CartitemEntity cartItemEntity = new CartitemEntity(persistedCartDTO.getId(), itemDTO.getId());
                cartMasterPersistance.createCartitemEntity(cartItemEntity);
            }
        }
        return cart;
    }

    public CartMasterDTO getMasterCart(Long id) {
        System.out.print("SE JODIO");
        return cartMasterPersistance.getCart(id);
    }

    public void deleteMasterCart(Long id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target( ServiceDirectory.CART_HOST ).path("Cart/webresources/Cart/" + id);
        target.request(MediaType.APPLICATION_JSON).delete();
//        cartPersistance.deleteCart(id);
    }

    public void updateMasterCart(CartMasterDTO cart) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target( ServiceDirectory.CART_HOST ).path("Cart/webresources/Cart/");
        target.request(MediaType.APPLICATION_JSON).put(Entity.entity(cart.getCartEntity(), MediaType.APPLICATION_JSON), CartDTO.class);

//        cartPersistance.updateCart(cart.getCartEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (cart.getCreateitem() != null) {
            for (ItemDTO itemDTO : cart.getCreateitem()) {
                target = client.target( ServiceDirectory.ITEM_HOST ).path("Item/webresources/Item/");

                ItemDTO createdItemDTO = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(itemDTO, MediaType.APPLICATION_JSON), ItemDTO.class);
//                ItemDTO createdItemDTO = itemPersistance.createItem(itemDTO);
                CartitemEntity cartItemEntity = new CartitemEntity(cart.getCartEntity().getId(), createdItemDTO.getId());
                cartMasterPersistance.createCartitemEntity(cartItemEntity);
            }
        }
        // update item
        if (cart.getUpdateitem() != null) {
            for (ItemDTO itemDTO : cart.getUpdateitem()) {
                target = client.target( ServiceDirectory.ITEM_HOST ).path("Item/webresources/Item/");
                target.request(MediaType.APPLICATION_JSON).put(Entity.entity(itemDTO,MediaType.APPLICATION_JSON));
//                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (cart.getDeleteitem() != null) {
            for (ItemDTO itemDTO : cart.getDeleteitem()) {
                cartMasterPersistance.deleteCartitemEntity(cart.getCartEntity().getId(), itemDTO.getId());

                target = client.target( ServiceDirectory.ITEM_HOST ).path("Item/webresources/Item/"+itemDTO.getId());
                target.request(MediaType.APPLICATION_JSON).delete();
//                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
    }
}
