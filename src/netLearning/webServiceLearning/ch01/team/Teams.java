
package netLearning.webServiceLearning.ch01.team;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService

public interface Teams {


    /**
     * 
     * @param arg0
     * @return
     *     returns teamsC.Team
     */
    @WebMethod
    public Team getTeam(
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<teamsC.Team>
     */
    @WebMethod
   
    public List<Team> getTeams();

}