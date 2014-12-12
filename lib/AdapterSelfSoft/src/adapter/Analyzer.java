/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author Evilasio
 */
public interface Analyzer {
    public void update(Monitor m);
    public void addDesigner(Designer a);
    public void removeDesigner(Designer a);
}
