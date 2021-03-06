/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

/**
 *
 * @author BKJVZ
 */
public class ChildrenJSON {
    private String id;
    private boolean directory;
    private String name;
    private FileJSON[] children; 

    public ChildrenJSON(String id, boolean directory, String name, FileJSON[] children) {
        this.id = id;
        this.directory = directory;
        this.name = name;
        this.children = children;
    }

    public ChildrenJSON(String id, boolean directory, String name) {
        this.id = id;
        this.directory = directory;
        this.name = name;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the directory
     */
    public boolean isDirectory() {
        return directory;
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
     * @return the children
     */
    public FileJSON[] getChildren() {
        return children;
    }
            
    
}
