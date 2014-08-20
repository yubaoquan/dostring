package netLearning.webServiceLearning.ch01.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamsUtility {

	private Map<String,Team> team_map;
	
	public TeamsUtility() {
		team_map = new HashMap<String,Team>();
	}
	
	public Team getTeam(String name) {
		return team_map.get(name);
	}
	
	public List<Team> getTeams() {
		List<Team> list = new ArrayList<Team>();
		Set<String> keys = team_map.keySet();
		for(String key : keys) {
			list.add(team_map.get(key));
		}
		return list;
	}
	
	public void make_test_tesms() {
		List<Team> teams = new ArrayList<Team>();
		Player chico = new Player("Leonard Marx","Chico");
		Player groucho = new Player("Julius Marx","Groucho");
		Player harpo = new Player("Adolph Marx","Harpo");
		List<Player> mb = new ArrayList<Player>();
		mb.add(chico);
		mb.add(groucho);
		mb.add(harpo);
		
		Team marx_brothers = new Team("Marx Brothers",mb);
		teams.add(marx_brothers);
		
		
		Player bud = new Player("Bud","nick Bud");
		Player lou = new Player("Lou","nick Lou");
		List<Player> mc = new ArrayList<Player>();
		mc.add(bud);
		mc.add(lou);
		Team abbott_and_costello = new Team("Abbott_and_Costello",mc);
		teams.add(abbott_and_costello);
		
		store_teams(teams);
	}
	
	private void store_teams(List<Team> teams) {
		for (Team team : teams) {
			team_map.put(team.getName(), team);
		}
	}
}
