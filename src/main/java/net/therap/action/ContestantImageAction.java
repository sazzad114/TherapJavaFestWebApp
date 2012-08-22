package net.therap.action;

import net.therap.dao.ContestantDao;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/22/12 11:16 AM
 */
@Name("contestantImageAction")
@Scope(ScopeType.EVENT)
public class ContestantImageAction {

    @In
    private ContestantDao contestantDao;


    void getContestantImage(long contestantId){
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

   /* response.addHeader("Content-Length", SIFDataConverter.getStringValue(bean.getData().length, false));
    response.addHeader("Content-Disposition", "inline; filename=" + bean.getName());
    response.addHeader("Expires", "0");
    response.addHeader("Pragma", "cache");
    response.addHeader("Cache-Control", "private");
    response.setContentType(MIMETypes.ApplicationPDF.toString());

    try
    {
      response.getOutputStream().write(bean.getData());
      response.getOutputStream().flush();
      response.getOutputStream().close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }*/
    facesContext.responseComplete();
    }
}
