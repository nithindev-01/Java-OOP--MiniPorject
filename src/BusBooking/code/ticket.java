package BusBooking.code;

import java.util.HashMap;

public class ticket {
    passenger p;
    bus b;
    private int price;
    private String ticketID;
    public static HashMap<String, ticket> ticketbook = new HashMap<>();

    public passenger getP() {
        return p;
    }

    public void setP(passenger p) {
        this.p = p;
    }

    public bus getB() {
        return b;
    }

    public void setB(bus b) {
        this.b = b;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public ticket(passenger p, bus b, int price) {
        this.p = p;
        this.b = b;
        this.price = price;
        if (b.getavailSeats() > 0) {
            int seatNo = 1;
            int a = 0;
            while (a == 0) {
                if (ticketbook.containsKey(b.getBusId() + seatNo)) {
                    seatNo++;
                } else {
                    this.ticketID = b.getBusId() + seatNo;
                    a = 1;
                }
            }
        }
        ticketbook.put(ticketID, this);
    }

    public static void updateSeats(String id) {
        int temp = ticketbook.get(id).getB().getavailSeats();
        ticketbook.get(id).getB().setavailSeats(temp++);
    }

    public static ticket ticketfinder(String id) {
        return ticketbook.get(id);
    }

    @Override
    public String toString() {
        return String.format(
                "\n==============================\npassenger details:\n%s\nbus details:\n%s\nseat No: %s\nprice: %d\nticket ID: %s\n==============================\n",
                this.p, this.b, this.ticketID.substring(5), this.price, this.ticketID);
    }
}
