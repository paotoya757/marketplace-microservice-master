package persistence.entity;

import org.eclipse.persistence.annotations.JoinFetch;
import persistence.entity.common.ItemEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mauricio on 3/23/15.
 */
@Entity
@IdClass(CartitemEntityId.class)
@NamedQueries({
        @NamedQuery(name = "CartitemEntity.getByMasterId", query = "SELECT  u FROM CartitemEntity u WHERE u.cartId=:cartId"),
        @NamedQuery(name = "CartitemEntity.deleteCartitemEntity", query = "DELETE FROM CartitemEntity u WHERE u.cartId=:cartId and  u.itemId=:itemId")
})
public class CartitemEntity implements Serializable{
    @Id
    @Column(name = "cartId")
    private Long cartId;
    @Id
    @Column(name = "itemId")
    private Long itemId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "cartId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity cartIdEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity itemIdEntity;

    public CartitemEntity() {
    }

    public CartitemEntity(Long cartId, Long itemId) {
        this.cartId =cartId;
        this.itemId = itemId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemEntity getCartIdEntity() {
        return cartIdEntity;
    }

    public void setCartIdEntity(ItemEntity cartIdEntity) {
        this.cartIdEntity = cartIdEntity;
    }

    public ItemEntity getItemIdEntity() {
        return itemIdEntity;
    }

    public void setItemIdEntity(ItemEntity itemIdEntity) {
        this.itemIdEntity = itemIdEntity;
    }

}
