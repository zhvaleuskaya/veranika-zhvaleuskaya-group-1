package unic.mentoring.springcore.data;

import java.io.Serializable;

/**
 * The Base Entity.
 */
public interface Entity extends Serializable {
    
    /**
     * Gets the entity id.
     *
     * @return the id
     */
    Long getId();

    /**
     * Sets the entity id.
     *
     * @param id the new id
     */
    void setId(Long id);
}
