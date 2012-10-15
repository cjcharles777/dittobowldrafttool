/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author cedric
 */

@Entity
@Table(name = "Position")
public class Position implements Serializable
{
    private String position;
    private int id;
    
    
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "positionid", nullable=false)
    public int getId() 
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "position", length=500, nullable=false, unique=true)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}
