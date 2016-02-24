package api.dto.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mauricio on 3/23/15.
 */
@XmlRootElement
public class ItemDTO {
    private Long id;


    private Integer quantity;


    private Long productId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productid) {
        this.productId = productid;
    }
}
