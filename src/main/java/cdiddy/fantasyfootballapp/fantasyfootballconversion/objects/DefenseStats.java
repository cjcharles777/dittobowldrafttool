/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.objects;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author cedric
 */
public class DefenseStats 
{
    
    private String name;
    private String tkl;
    private String ast;
    private String sk;
    private String intr;
    private String ffum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTkl() {
        return tkl;
    }

    public void setTkl(String tkl) {
        this.tkl = tkl;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getIntr() {
        return intr;
    }

    @JsonProperty("int")
    public void setIntr(String intr) {
        this.intr = intr;
    }

    public String getFfum() {
        return ffum;
    }

    public void setFfum(String ffum) {
        this.ffum = ffum;
    }
    
    
    
}
