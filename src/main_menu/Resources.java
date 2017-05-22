package main_menu;

import java.net.URL;

/**
 * 
 */
public class Resources {
    
    /**
     * Do not construct an instance of this class.
     */
    private Resources() {
        
    }

    /**
     * Return the URL of the filename under the resources directory
     */
    public static URL getResource(String filename) {    
         URL url = Resources.class.getResource(filename); 
         return url;
    }
    
    
}
