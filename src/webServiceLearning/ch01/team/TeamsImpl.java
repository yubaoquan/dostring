package webServiceLearning.ch01.team;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "ch01.team.Teams")
public class TeamsImpl implements Teams{

	private TeamsUtility utils;
	public TeamsImpl() {
		utils = new TeamsUtility();
		utils.make_test_tesms();
	}
	
	@Override
	public Team getTeam(String name) {
		return utils.getTeam(name);
	}

	@Override
	public List<Team> getTeams() {
		return utils.getTeams();
	}

}
