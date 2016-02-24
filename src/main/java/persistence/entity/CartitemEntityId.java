package persistence.entity;

import java.io.Serializable;

/**
 * Created by Mauricio on 3/23/15.
 */
public class CartitemEntityId implements Serializable{

    private Long cartId;
    private Long itemId;

    @Override
    public int hashCode() {
        return (int) (cartId + itemId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CartitemEntityId) {
            CartitemEntityId otherId = (CartitemEntityId) object;
            return (otherId.cartId.equals(this.cartId)) && (otherId.itemId.equals(this.itemId));
        }
        return false;
    }

}
