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
