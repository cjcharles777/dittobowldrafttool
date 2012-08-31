/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DMDD
 */
@Entity
@Table(name = "Players")
public class Player implements Serializable
{

    private int id;
    private String firstName;
    private String lastName;
    private String team;
    private int uniformNumber;
    private char displayPosition;
    private String headshotHtml;

    public Player (ArrayList<LinkedHashMap> lhm)
    {
        this.id = (Integer) lhm.get(0).get("player_id");
    }
    
     @Column(name = "FIRSTNAME", nullable=false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LASTNAME", nullable=false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    @Column(name = "DISPLAYPOSITION", nullable=false)
    public char getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(char displayPosition) {
        this.displayPosition = displayPosition;
    }
    
    @Column(name = "HEADSHOTHTML", nullable=false)
    public String getHeadshotHtml() {
        return headshotHtml;
    }

    public void setHeadshotHtml(String headshotHtml) {
        this.headshotHtml = headshotHtml;
    }

    @Id
    @GeneratedValue(generator = "assigned")
    @Column(name = "ID", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "TEAM", nullable=false)
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    @Column(name = "UNIFORMNUMBER", nullable=false)
    public int getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(int uniformNumber) {
        this.uniformNumber = uniformNumber;
    }
    
    
    
    
}
