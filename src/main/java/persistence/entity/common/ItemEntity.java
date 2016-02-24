package persistence.entity.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Mauricio on 3/23/15.
 */
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(generator = "Item")
    private Long id;
    private Integer quantity;
    private Long productId;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    public Long getProductId(){
        return productId;
    }

    public void setProductId(Long productId){
        this.productId = productId;
    }
}
