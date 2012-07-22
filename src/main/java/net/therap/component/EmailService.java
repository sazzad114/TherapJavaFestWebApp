package net.therap.component;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.log.Log;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 11:46 AM
 */
@Name("emailService")
@AutoCreate
public class EmailService {
      @Logger
      private Log log;

      @In(create = true)
      private Renderer renderer;

       public void sendMessage() {
       log.info("inside send message");
        try{
              renderer.render("/email.xhtml");
      }catch (Exception e) {
          log.error("Error Email Send #0"+e.getMessage());
           }
      }
}
