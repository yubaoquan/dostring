package netLearning.webServiceLearning.ch01.team;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TeamClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//TeamsService service = new TeamsService();
		URL url = new URL("http://localhost:8888/teams?wsdl");
		QName qname = new QName("http://team.ch01/","TeamsImplService");
		Service service = Service.create(url, qname);
		Teams port = service.getPort(Teams.class);
		List<Team> teams = port.getTeams();
		//System.out.println(teams.size());
		for(Team team : teams) {
			System.out.println("Team name:" + team.getName() + "(rouster count: " 
						+ team.getRosterCount() + ")");
			for(Player player : team.getPlayers()) {
				System.out.println(" Player: " + player.getName());
			}
		}
	}

}
