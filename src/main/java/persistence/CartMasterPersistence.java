package persistence;



import service.ServiceDirectory;
import api.dto.CartMasterDTO;
import api.dto.common.CartDTO;
import api.dto.common.ItemDTO;
import persistence.api.ICartMasterPersistence;
import persistence.converter.common.ItemConverter;
import persistence.entity.CartitemEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import org.apache.naming.ServiceRef;

/**
 * Created by Mauricio on 3/23/15.
 */
public class CartMasterPersistence implements ICartMasterPersistence {
    
    @PersistenceContext(unitName="MarketplaceProjectPU")
    protected EntityManager entityManager;

//    @Inject
//    protected ICartPersistence cartPersistence;

    public CartMasterDTO getCart(Long cartId) {
        System.out.println("CART!");
        CartMasterDTO cartMasterDTO = new CartMasterDTO();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target( ServiceDirectory.PRODUCT_HOST ).path("webresources/Product/"+cartId);
//        WebTarget target = client.target("http://localhost:8080").path("Product/webresources/Product/"+cartId);
        CartDTO cart=target.request(MediaType.APPLICATION_JSON).get(CartDTO.class);


//        CartDTO cart = cartPersistence.getCart(cartId);
        cartMasterDTO.setCartEntity(cart);
        cartMasterDTO.setListitem(getCartitemEntityList(cartId));
        return cartMasterDTO;
    }

    public CartitemEntity createCartitemEntity(CartitemEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteCartitemEntity(Long cartId, Long itemId) {
        Query q = entityManager.createNamedQuery("CartitemEntity.deleteCartitemEntity");
        q.setParameter("cartId", cartId);
        q.setParameter("itemId", itemId);
        q.executeUpdate();
    }

    public List<ItemDTO> getCartitemEntityList(Long cartId) {
        ArrayList<ItemDTO> resp = new ArrayList<ItemDTO>();
        Query q = entityManager.createNamedQuery("CartitemEntity.getByMasterId");
        q.setParameter("cartId",cartId);
        List<CartitemEntity> qResult =  q.getResultList();
        for (CartitemEntity entity : qResult) {
            if(entity.getItemIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(ItemConverter.entity2PersistenceDTO(entity.getItemIdEntity()));
        }
        return resp;
    }
}
