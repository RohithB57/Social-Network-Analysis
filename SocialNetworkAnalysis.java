import java.util.*;

class Graph {
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addEdge(String user1, String user2) {
        adjList.putIfAbsent(user1, new ArrayList<>());
        adjList.putIfAbsent(user2, new ArrayList<>());
        adjList.get(user1).add(user2);
        adjList.get(user2).add(user1);
    }

    public void bfsTraversal(String startUser) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startUser);
        visited.add(startUser);

        System.out.println("BFS Traversal starting from: " + startUser);
        while (!queue.isEmpty()) {
            String user = queue.poll();
            System.out.print(user + " -> ");
            for (String neighbor : adjList.get(user)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("End");
    }

    public void dfsTraversal(String startUser) {
        Set<String> visited = new HashSet<>();
        System.out.println("DFS Traversal starting from: " + startUser);
        dfsHelper(startUser, visited);
        System.out.println("End");
    }

    private void dfsHelper(String user, Set<String> visited) {
        visited.add(user);
        System.out.print(user + " -> ");
        for (String neighbor : adjList.get(user)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void findConnectedComponents() {
        Set<String> visited = new HashSet<>();
        int count = 0;
        for (String user : adjList.keySet()) {
            if (!visited.contains(user)) {
                count++;
                System.out.print("Community " + count + ": ");
                dfsHelper(user, visited);
                System.out.println();
            }
        }
    }

    public void calculateCentrality() {
        Map<String, Integer> centrality = new HashMap<>();
        for (String user : adjList.keySet()) {
            centrality.put(user, adjList.get(user).size());
        }
        System.out.println("User Centrality (Higher = More Influential): " + centrality);
    }
}

public class SocialNetworkAnalysis {
    public static void main(String[] args) {
        Graph network = new Graph();
        network.addEdge("Alice", "Bob");
        network.addEdge("Bob", "Charlie");
        network.addEdge("Alice", "David");
        network.addEdge("David", "Eve");
        network.addEdge("Eve", "Frank");
        network.addEdge("Charlie", "Frank");
        network.addEdge("George", "Hannah");

        network.bfsTraversal("Alice");
        network.dfsTraversal("Alice");
        network.findConnectedComponents();
        network.calculateCentrality();
    }
}