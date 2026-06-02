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
