import java.util.*;
class disaster{
    int id;
    String type;
    String city;
    int severity;
    public disaster(int id,String type,String city,int severity){
        this.id=id;
        this.type=type;
        this.city=city;
        this.severity=severity;
    }
    @Override
    public String toString() {
        return "ID: " + id +
                ", Type: " + type +
                ", City: " + city +
                ", Severity: " + severity;
    }
}
class treenode {
    disaster disaster;
    treenode left, right;
    public treenode(disaster disaster) {
        this.disaster = disaster;
        left = right = null;
    }
}
class Disasterbst {
    treenode root;
    public void insert(disaster disaster) {
        root = insertRec(root, disaster);
    }
    private treenode insertRec(treenode root, disaster disaster) {
        if (root == null) {
            return new treenode(disaster);
        }
        if (disaster.id < root.disaster.id) {
            root.left = insertRec(root.left, disaster);
        } else {
            root.right = insertRec(root.right, disaster);
        }
        return root;
    }
    public disaster search(int id) {
        return searchRec(root, id);
    }
    private disaster searchRec(treenode root, int id) {
        if (root == null) {
            return null;
        }
        if (root.disaster.id == id) {
            return root.disaster;
        }
        if (id < root.disaster.id) {
            return searchRec(root.left, id);
        }
        return searchRec(root.right, id);
    }
}
public class Main {
    static ArrayList<disaster> disasterList = new ArrayList<>();
    static LinkedList<String> victims = new LinkedList<>();


    static Stack<String> actionHistory = new Stack<>();


    static Queue<String> rescueQueue = new LinkedList<>();


    static HashMap<String, ArrayList<disaster>> cityMap = new HashMap<>();

    static Disasterbst disasterTree = new Disasterbst();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SMART DISASTER MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Disaster");
            System.out.println("2. View Disasters");
            System.out.println("3. Add Victim");
            System.out.println("4. Add Rescue Request");
            System.out.println("5. Process Rescue Request");
            System.out.println("6. Search Disaster By ID");
            System.out.println("7. View Action History");
            System.out.println("8. View City Data");
            System.out.println("9. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Disaster ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Disaster Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter City: ");
                    String city = sc.nextLine();
                    System.out.print("Enter Severity (1-10): ");
                    int severity = sc.nextInt();
                    sc.nextLine();
                    disaster d = new disaster(id, type, city, severity);
                    disasterList.add(d);
                    disasterTree.insert(d);
                    cityMap.putIfAbsent(city, new ArrayList<>());
                    cityMap.get(city).add(d);
                    actionHistory.push("Added Disaster: " + type);
                    System.out.println("Disaster Added Successfully!");
                    break;
                    case 2:
                        System.out.println("\n--- Disaster Records ---");
                        for (disaster disaster : disasterList) {
                        System.out.println(disaster);
                    }
                        break;
                        case 3:

                    System.out.print("Enter Victim Name: ");
                    String victim = sc.nextLine();
                    victims.add(victim);
                    actionHistory.push(
                            "Victim Added: " + victim);
                    System.out.println(
                            "Victim Added Successfully!");
                    break;
                    case 4:
                        System.out.print("Enter Rescue Request: ");
                    String request = sc.nextLine();
                    rescueQueue.offer(request);
                    actionHistory.push("Rescue Request Added");
                    System.out.println("Request Added Successfully!");
                    break;
                    case 5:
                    if (!rescueQueue.isEmpty()) {
                        System.out.println("Processed Request: " + rescueQueue.poll());
                    } else {
                        System.out.println(
                                "No Rescue Requests!");
                    }
                    break;
                    case 6:
                    System.out.print("Enter Disaster ID to Search: ");
                    int searchId = sc.nextInt();
                    disaster found = disasterTree.search(searchId);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println(
                                "Disaster Not Found!");
                    }
                    break;
                    case 7:
                        System.out.println(
                            "\n--- Action History ---");
                        for (String action : actionHistory) {
                        System.out.println(action);
                    }
                        break;
                        case 8:
                            System.out.print(
                            "Enter City Name: ");
                    String cityName = sc.nextLine();
                    if (cityMap.containsKey(cityName)) {
                        for (disaster disaster : cityMap.get(cityName)) {

                            System.out.println(disaster);
                        }
                    } else {
                        System.out.println("No Data Found!");
                    }
                    break;
                    case 9:
                    System.out.println(
                            "Exiting System...");
                    System.exit(0);
                    default:
                        System.out.println("Invalid Choice!");
            }
        }
    }
}

