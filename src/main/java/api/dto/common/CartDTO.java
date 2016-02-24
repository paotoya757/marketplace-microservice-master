package api.dto.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mauricio on 3/23/15.
 */
@XmlRootElement
public class CartDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
