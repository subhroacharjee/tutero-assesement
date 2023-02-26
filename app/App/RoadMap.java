package App;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import App.Pair;

public class RoadMap {
	private Map<String, List<String>> roadMap;
	private Map<String, Integer> indegreeMap;
	private Map<String, Double> progressMap;

	public RoadMap() {
		roadMap = new HashMap<>();
		progressMap = new HashMap<>();
		indegreeMap = new HashMap<>();
	}

	public void addMapping(String input) {
		
		boolean isARelation = input.indexOf("=") == -1;
		if (isARelation) {
			String[] skills = input.split("->");
			roadMap.putIfAbsent(skills[0], new ArrayList<>());
            roadMap.putIfAbsent(skills[1], new ArrayList<>());
			roadMap.get(skills[0]).add(skills[1]);
			indegreeMap.put(skills[1], indegreeMap.getOrDefault(skills[1], 0) + 1);
		} else {
			String[] skills = input.split("=");
			roadMap.putIfAbsent(skills[0], new ArrayList<>());
			progressMap.put(skills[0], Double.valueOf(skills[1]));
		}

		
	}


	public List<String> getLinearOrderOfSkills() {
		List<String> ans = new ArrayList<>();
		Queue<Pair> maxHeap = new PriorityQueue<>((a,b)-> b.value - a.value >= 0? 1: -1);
		

		for (String key: roadMap.keySet()) {
			if (indegreeMap.getOrDefault(key, 0) == 0) maxHeap.add(new Pair(key, progressMap.getOrDefault(key, 0.0)));
		}

		while (!maxHeap.isEmpty()) {
			int size = maxHeap.size();
			while(size-- > 0) {
				String key = maxHeap.peek().key;
				ans.add(key);
				maxHeap.poll();

				for (String adjNode: roadMap.getOrDefault(key, new ArrayList<>())) {
					indegreeMap.put(adjNode, indegreeMap.getOrDefault(adjNode, 0)  - 1);
					if (indegreeMap.getOrDefault(adjNode, 0) == 0) {
						maxHeap.add(new Pair(adjNode, progressMap.getOrDefault(adjNode, 0.0)));
					}
				}
			}
		}
		return ans;
	}


}
