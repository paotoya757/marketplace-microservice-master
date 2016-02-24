package persistence.converter.common;


import api.dto.common.ItemDTO;
import persistence.entity.common.ItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio on 3/23/15.
 */
public class ItemConverter {
    public static ItemDTO entity2PersistenceDTO(ItemEntity entity){
        if (entity != null) {
            ItemDTO dto = new ItemDTO();
            dto.setId(entity.getId());
            dto.setQuantity(entity.getQuantity());
            dto.setProductId(entity.getProductId());
            return dto;
        }else{
            return null;
        }
    }

    public static ItemEntity persistenceDTO2Entity(ItemDTO dto){
        if(dto!=null){
            ItemEntity entity=new ItemEntity();
            entity.setId(dto.getId());

            entity.setQuantity(dto.getQuantity());

            entity.setProductId(dto.getProductId());

            return entity;
        }else {
            return null;
        }
    }

    public static List<ItemDTO> entity2PersistenceDTOList(List<ItemEntity> entities){
        List<ItemDTO> dtos=new ArrayList<ItemDTO>();
        for(ItemEntity entity:entities){
            dtos.add(entity2PersistenceDTO(entity));
        }
        return dtos;
    }

    public static List<ItemEntity> persistenceDTO2EntityList(List<ItemDTO> dtos){
        List<ItemEntity> entities=new ArrayList<ItemEntity>();
        for(ItemDTO dto:dtos){
            entities.add(persistenceDTO2Entity(dto));
        }
        return entities;
    }
}
