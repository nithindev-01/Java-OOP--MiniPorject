package BusBooking.code;

import java.util.HashMap;

public class bus {
    private int availSeats;
    private int seats;
    private String from;
    private String to;
    private String travelsName;
    private String busId;
    private driver D;
    static HashMap<String,bus> busBook = new HashMap<>();

    public driver getD() {
        return D;
    }

    public void setD(driver d) {
        D = d;
    }

    public int getavailSeats() {
        return availSeats;
    }

    public void setavailSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTravelsName() {
        return travelsName;
    }

    public void setTravelsName(String travelsName) {
        this.travelsName = travelsName;
    }


    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public bus(String from, String to, String travelsName) {
        this.from = from;
        this.to = to;
        this.travelsName = travelsName;
        String idf = getFrom().substring(0,2);
        String idt = getTo().substring(0,2);
        String idn = getTravelsName().substring(0,1).concat(idf);
        String id = idn.concat(idt);
        this.busId = id;
    }

    public bus(String line) {
        String values[] = line.split(",");
        this.from = values[0];
        this.to = values[1];
        this.travelsName = values[2];
        this.seats = Integer.valueOf(values[3]);
        this.availSeats = Integer.valueOf(values[4]);
        String idf = getFrom().substring(0,2);
        String idt = getTo().substring(0,2);
        String idn = getTravelsName().substring(0,1).concat(idf);
        String id = idn.concat(idt);
        this.busId = id;
        busBook.put(id,this);
    }

    public static bus busFind(String id){
        if(busBook.containsKey(id)){
            return busBook.get(id);
        } else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "From: " + from + "  To: " + to + "  Travels Name: " + travelsName;
    }

}
