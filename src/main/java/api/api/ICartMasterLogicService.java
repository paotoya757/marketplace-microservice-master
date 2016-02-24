package api.api;

import api.dto.CartMasterDTO;

/**
 * Created by Mauricio on 3/23/15.
 */
public interface ICartMasterLogicService {
    public CartMasterDTO createMasterCart(CartMasterDTO detail);
    public void updateMasterCart(CartMasterDTO detail);
    public void deleteMasterCart(Long id);
    public CartMasterDTO getMasterCart(Long id);
}
