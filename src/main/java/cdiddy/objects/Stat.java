/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DMDD
 */

@Entity
@Table(name = "Stat")
public class Stat 
{
    private int table_stat_id;
    String stat_id;
    private Double value;

    
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "table_stat_id", nullable=false)
    public int getTable_stat_id() {
        return table_stat_id;
    }

    public void setTable_stat_id(int table_stat_id) {
        this.table_stat_id = table_stat_id;
    }

 
    @Column(name = "stat_id", length=4, nullable=false)
    public String getStat_id() {
        return stat_id;
    }

    public void setStat_id(String stat_id) {
        this.stat_id = stat_id;
    }

    @Column(name = "value", nullable=false)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
