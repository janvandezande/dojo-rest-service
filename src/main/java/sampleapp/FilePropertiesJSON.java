/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author BKJVZ
 */
class FilePropertiesJSON {
    private String name;
    private String type;
    private String location;
    private BigDecimal size;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date created;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date modified;
        @JsonSerialize(using = JsonDojoCheckBoxValueSerializer.class)
    private CheckBoxJSON readOnly;
        @JsonSerialize(using = JsonDojoCheckBoxValueSerializer.class)
    private CheckBoxJSON hidden;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String comment;
    private String label;
    private String user;
    private BigDecimal value;

    public FilePropertiesJSON(String name, String type, String location, BigDecimal size, Date created, Date modified, boolean readOnly, boolean hidden, Date date, String comment, String label, String user, BigDecimal value) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.size = size;
        this.created = created;
        this.modified = modified;
        this.readOnly = new CheckBoxJSON("readOnly", readOnly);
        this.hidden = new CheckBoxJSON("hidden", hidden);
        this.date = date;
        this.comment = comment;
        this.label = label;
        this.user = user;
        this.value = value;
    }

    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the size
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

//    /**
//     * @return the readOnly
//     */
//    public boolean isReadOnly() {
//        return readOnly.getValue("readOnly");
//    }
//
//    /**
//     * @param readOnly the readOnly to set
//     */
//    public void setReadOnly(boolean readOnly) {
//        this.readOnly = new CheckBoxJSON("readOnly", readOnly);
//    }
//
//    /**
//     * @return the hidden
//     */
//    public boolean isHidden() {
//        return hidden.getValue("hidden");
//    }
//
//    /**
//     * @param hidden the hidden to set
//     */
//    public void setHidden(boolean hidden) {
//        this.hidden = new CheckBoxJSON("hidden", hidden);
//    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
}
