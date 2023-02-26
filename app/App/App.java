package App;
import App.RoadMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class App {
	public static void main(String[] args) throws FileNotFoundException {
		String cwd = System.getProperty("user.dir");
		File file = new File(cwd + "/input.txt");
		Scanner sc = new Scanner(file);
		RoadMap roadMap = new RoadMap();

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.length() == 0) continue;
			roadMap.addMapping(line);
		}
		System.out.println(roadMap.getLinearOrderOfSkills());
	}
}
