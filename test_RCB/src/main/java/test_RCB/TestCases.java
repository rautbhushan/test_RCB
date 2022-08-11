package test_RCB;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases {

	private JSONArray getPlayers() {
		JSONParser jsonParser = new JSONParser();
		FileReader reader;
		JSONArray playerList = null;
		try {
			reader = new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\test_RCB\\TeamRCB.json");
			Object obj = jsonParser.parse(reader);
			JSONObject teamRCB = (JSONObject) obj;
			playerList = (JSONArray) teamRCB.get("player");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return playerList;
	}

	@Test(description="Write a test that validates that the team has only 4 foreign players")
	public void testTeamHasOnlyFourForeignPlayers() {
		JSONArray playerList = getPlayers();
		int foreignPlayersCount = 0;
		for (int i = 0; i < playerList.size(); i++) {
			JSONObject player = (JSONObject) playerList.get(i);
			String country = (String) player.get("country");
			if (country != "india") {
				foreignPlayersCount++;
			}
		}
		Assert.assertTrue(foreignPlayersCount <= 4,
				"Team has more than four foreign players, Foreign players count: " + foreignPlayersCount);
	}

	@Test(description="Write a test that validates that the there is at least one wicket keeper")
	public void testTeamHasAtleastOneWicket_keeper() {
		JSONArray playerList = getPlayers();
		int wicket_keeperCount = 0;
		for (int i = 0; i < playerList.size(); i++) {
			JSONObject player = (JSONObject) playerList.get(i);
			String role = (String) player.get("role");
			if (role != "Wicket-keeper") {
				wicket_keeperCount++;
			}
		}
		Assert.assertTrue(wicket_keeperCount >= 1, "Team does not have at least one Wicket_keeper");
	}

}
