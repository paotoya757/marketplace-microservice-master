package api.dto;

import api.dto.common.CartDTO;
import api.dto.common.ItemDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Mauricio on 3/23/15.
 */
@XmlRootElement
public class CartMasterDTO {
    protected CartDTO cartEntity;
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartDTO getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartDTO cartEntity) {
        this.cartEntity = cartEntity;
    }

    public List<ItemDTO> createitem;
    public List<ItemDTO> updateitem;
    public List<ItemDTO> deleteitem;
    public List<ItemDTO> listitem;



    public List<ItemDTO> getCreateitem(){ return createitem; };
    public void setCreateitem(List<ItemDTO> createitem){ this.createitem=createitem; };
    public List<ItemDTO> getUpdateitem(){ return updateitem; };
    public void setUpdateitem(List<ItemDTO> updateitem){ this.updateitem=updateitem; };
    public List<ItemDTO> getDeleteitem(){ return deleteitem; };
    public void setDeleteitem(List<ItemDTO> deleteitem){ this.deleteitem=deleteitem; };
    public List<ItemDTO> getListitem(){ return listitem; };
    public void setListitem(List<ItemDTO> listitem){ this.listitem=listitem; };

}
