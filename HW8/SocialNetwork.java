import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents the social network graph.
 */
public class SocialNetwork {
    private Map<String, Person> people = new HashMap<>();
    private Map<Person, List<Person>> friendships = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Adds a person to the social network.
     *
     * @param name    the name of the person
     * @param age     the age of the person
     * @param hobbies the hobbies of the person
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        Person person = new Person(name, age, hobbies);
        people.put(name + dateFormat.format(person.getTimestamp()), person); // unique key with formatted timestamp
        friendships.put(person, new ArrayList<>());
        System.out.println("Person added: " + person.getName() + " (Timestamp: " + person.getFormattedTimestamp() + ")");
    }

    /**
     * Removes a person from the social network.
     *
     * @param name      the name of the person
     * @param timestamp the timestamp of removal
     */
    public void removePerson(String name, Date timestamp) {
        String key = name + dateFormat.format(timestamp);
        Person person = people.remove(key);
        if (person != null) {
            friendships.remove(person);
            for (Iterator<Map.Entry<Person, List<Person>>> it = friendships.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Person, List<Person>> entry = it.next();
                List<Person> friends = entry.getValue();
                for (Iterator<Person> friendIt = friends.iterator(); friendIt.hasNext(); ) {
                    Person friend = friendIt.next();
                    if (friend.equals(person)) {
                        friendIt.remove();
                    }
                }
            }
            System.out.println("Person removed: " + name + " (Timestamp: " + person.getFormattedTimestamp() + ")");
        } else {
            System.out.println("Person not found in the network.");
        }
    }

    /**
     * Adds a friendship between two people.
     *
     * @param name1      the name of the first person
     * @param timestamp1 the timestamp of the first person
     * @param name2      the name of the second person
     * @param timestamp2 the timestamp of the second person
     */
    public void addFriendship(String name1, Date timestamp1, String name2, Date timestamp2) {
        Person person1 = people.get(name1 + dateFormat.format(timestamp1));
        Person person2 = people.get(name2 + dateFormat.format(timestamp2));
        if (person1 != null && person2 != null) {
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.getName() + " and " + person2.getName());
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Removes a friendship between two people.
     *
     * @param name1      the name of the first person
     * @param timestamp1 the timestamp of the first person
     * @param name2      the name of the second person
     * @param timestamp2 the timestamp of the second person
     */
    public void removeFriendship(String name1, Date timestamp1, String name2, Date timestamp2) {
        Person person1 = people.get(name1 + dateFormat.format(timestamp1));
        Person person2 = people.get(name2 + dateFormat.format(timestamp2));
        if (person1 != null && person2 != null) {
            friendships.get(person1).remove(person2);
            friendships.get(person2).remove(person1);
            System.out.println("Friendship removed between " + person1.getName() + " and " + person2.getName());
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Finds the shortest path using BFS.
     *
     * @param startName     the name of the starting person
     * @param startTimestamp the timestamp of the starting person
     * @param endName       the name of the ending person
     * @param endTimestamp  the timestamp of the ending person
     */
    public void findShortestPath(String startName, Date startTimestamp, String endName, Date endTimestamp) {
        Person start = people.get(startName + dateFormat.format(startTimestamp));
        Person end = people.get(endName + dateFormat.format(endTimestamp));
        if (start == null || end == null) {
            System.out.println("One or both persons not found in the network.");
            return;
        }
    
        Map<Person, Person> prev = new HashMap<>();
        if (bfs_short(start, end, prev)) {
            printPath(start, end, prev);
        } else {
            System.out.println("No path found between " + startName + " and " + endName);
        }
    }
    
/**
 * Performs a breadth-first search (BFS) to determine if a path exists between two people in the social network.
 * This method is used to find the shortest path from the start person to the end person.
 * It updates the 'prev' map which is used to reconstruct the path if one exists.
 *
 * @param start The starting person of the path.
 * @param end The destination person of the path.
 * @param prev A map that tracks the preceding person in the path for each visited person.
 * @return boolean Returns true if a path exists between the start and end person, false otherwise.
 */
    private boolean bfs_short(Person start, Person end, Map<Person, Person> prev) {
        Queue<Person> queue = new LinkedList<>();
        Set<Person> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
    
        while (!queue.isEmpty()) {
            Person current = queue.poll();
            if (current.equals(end)) {
                return true;
            }
    
            List<Person> neighbors = friendships.get(current);
            for (Person neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }
        return false;
    }
    
    
    /**
     * Prints the shortest path between two people.
     *
     * @param start the starting person
     * @param end   the ending person
     * @param prev  the map of previous persons in the path
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<Person> path = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
    
        StringBuilder pathStr = new StringBuilder();
        for (Person person : path) {
            pathStr.append(person.getName()).append(" -> ");
        }
        // Remove the last "->"
        if (pathStr.length() > 0) {
            pathStr.setLength(pathStr.length() - 2);
        }
    
        System.out.println("Shortest path: " + pathStr.toString());
    }
    

    /**
     * Suggests friends for a person.
     *
     * @param name           the name of the person
     * @param timestamp      the timestamp of the suggestion
     * @param maxSuggestions the maximum number of friend suggestions
     */
    public void suggestFriends(String name, Date timestamp, int maxSuggestions) {
        Person person = people.get(name + dateFormat.format(timestamp));
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }
    
        Map<Person, FSuggestion> scores = new HashMap<>();
        for (Person p : people.values()) {
            if (!p.equals(person) && !friendships.get(person).contains(p)) {
                double score = 0;
                int mutualFriends = 0;
                int commonHobbies = 0;
    
                for (Person friend : friendships.get(person)) {
                    if (friendships.get(p).contains(friend)) {
                        score += 1;
                        mutualFriends++;
                    }
                }
    
                for (String hobby : person.getHobbies()) {
                    if (p.getHobbies().contains(hobby)) {
                        score += 0.5;
                        commonHobbies++;
                    }
                }
    
                if (score > 0) {
                    scores.put(p, new FSuggestion(score, mutualFriends, commonHobbies));
                }
            }
        }
    
        List<Map.Entry<Person, FSuggestion>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((a, b) -> Double.compare(b.getValue().score, a.getValue().score));
    
        if (sortedScores.isEmpty()) {
            System.out.println("No friends to suggest.");
        } else {
            System.out.println("Suggested friends for " + name + ":");
            for (int i = 0; i < Math.min(maxSuggestions, sortedScores.size()); i++) {
                Map.Entry<Person, FSuggestion> entry = sortedScores.get(i);
                Person suggestedPerson = entry.getKey();
                FSuggestion suggestion = entry.getValue();
                System.out.printf("%s (Score: %.1f, %d mutual friends, %d common hobbies)\n",
                        suggestedPerson.getName(), suggestion.score, suggestion.mutualFriends, suggestion.commonHobbies);
            }
        }
    }
    
    private static class FSuggestion {
        double score;
        int mutualFriends;
        int commonHobbies;
    
        FSuggestion(double score, int mutualFriends, int commonHobbies) {
            this.score = score;
            this.mutualFriends = mutualFriends;
            this.commonHobbies = commonHobbies;
        }
    }
    
    /**
     * Counts clusters using BFS.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;
        List<List<Person>> clusters = new ArrayList<>();

        for (Iterator<Person> it = people.values().iterator(); it.hasNext();) {
            Person person = it.next();
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusters.add(cluster);
                clusterCount++;
            }
        }

        System.out.println("Number of clusters found: " + clusterCount);

        int clusterIndex = 1;
        for (Iterator<List<Person>> it = clusters.iterator(); it.hasNext();) {
            List<Person> cluster = it.next();
            System.out.println("Cluster " + clusterIndex + ":");
            for (Iterator<Person> personIt = cluster.iterator(); personIt.hasNext();) {
                Person p = personIt.next();
                System.out.println(p.getName());
            }
            System.out.println();
            clusterIndex++;
        }
    }

    /**
     * Performs a BFS to find all connected nodes in a cluster.
     *
     * @param start    the starting person
     * @param visited  the set of visited persons
     * @param cluster  the list of persons in the current cluster
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);

            List<Person> neighbors = friendships.get(current);
            for (int i = 0; i < neighbors.size(); i++) {
                Person neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}
