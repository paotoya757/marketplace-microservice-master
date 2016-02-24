package persistence.api;

import api.dto.CartMasterDTO;
import api.dto.common.ItemDTO;
import persistence.entity.CartitemEntity;

import java.util.List;

/**
 * Created by Mauricio on 3/23/15.
 */
public interface ICartMasterPersistence {

    public CartMasterDTO getCart(Long cartId);


    public CartitemEntity createCartitemEntity(CartitemEntity entity);
    public void deleteCartitemEntity(Long cartId, Long itemId);
    public List<ItemDTO> getCartitemEntityList(Long cartId);

}
