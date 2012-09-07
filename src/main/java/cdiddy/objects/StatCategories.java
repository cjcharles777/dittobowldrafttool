/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cedric
 */
public class StatCategories 
{
    private int stat_id;
    private String name;
    private String display_name;
    private String sort_order;
    private ArrayList<PositionTypes> position_types;

    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public ArrayList<PositionTypes> getPosition_types() {
        return position_types;
    }

    public void setPosition_types(ArrayList<PositionTypes> position_types) {
        this.position_types = position_types;
    }


}
