package webServiceLearning.ch01.team;

public class Player {

	private String name;
	private String nickname;
	
	public Player() {}
	public Player(String name,String nickname) {
		this.setName(name);
		this.setNickname(nickname);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
