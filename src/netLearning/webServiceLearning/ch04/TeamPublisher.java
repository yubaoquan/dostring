package netLearning.webServiceLearning.ch04;

import javax.xml.ws.Endpoint;

public class TeamPublisher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8888;
		String url = "http://localhost" + port + "/teams";
		System.out.println("Publicshing Teams restfully on port" + port);
		Endpoint.publish(url, new RestfulTeams());
	}

}
