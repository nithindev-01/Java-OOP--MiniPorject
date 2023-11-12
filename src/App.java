import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import BusBooking.code.*;
import BusBooking.sqlqueries.*;

public class App {
    public static void main(String[] args) {
        String name, gender, mobileno, tid, bid;
        int age;
        ticket t;
        String DirectoryPath = System.getProperty("user.dir");
        try {
            BufferedReader breader = new BufferedReader(new FileReader(
                    DirectoryPath + "//src//csvfiles//busdetails.csv"));
            String line;
            while ((line = breader.readLine()) != null) {
                bus b = new bus(line);
                busQueries.addBus(b);
            }
            BufferedReader dreader = new BufferedReader(new FileReader(
                    DirectoryPath+"//src//csvfiles//driverdetails.csv"));
            while ((line = dreader.readLine()) != null) {
                driver.AddDriver(line);
            }
            breader.close();
            dreader.close();
            Scanner scan = new Scanner(System.in);
            int a = 0;
            System.out.println("-h to help.");
            System.out.println("exit to terminate the program.");
            while (a == 0) {
                String choice = scan.nextLine();
                switch (choice) {
                    case "-b":
                        BufferedReader preader = new BufferedReader(new FileReader(
                                DirectoryPath+"//src//csvfiles//passengerDetails.csv"));
                        while ((line = preader.readLine()) != null) {
                            String values[] = line.split(",");
                            bid = values[0];
                            name = values[1];
                            age = Integer.valueOf(values[2]);
                            gender = values[3];
                            mobileno = values[4];
                            passenger p = new passenger(name, age, gender, mobileno);
                            if (p.duplicateFind(p) == 0) {
                                t = new ticket(p, bus.busFind(bid), 100);
                                ticketQueries.addTicket(t);
                                t.getB().setavailSeats(t.getB().getavailSeats() - 1);
                            } else {
                                ticketQueries.updateTicket(name, age, gender, mobileno);
                            }
                        }
                        preader.close();
                        System.out.print("Tickets booked for passengers in csv file.");
                        break;

                    case "-bt":
                        System.out.println("\nEnter bus details:");
                        System.out.print("Source: ");
                        String from = scan.nextLine();
                        System.out.print("Destination: ");
                        String to = scan.nextLine();
                        if (busQueries.searchBus(from, to) == 0) {
                            System.out.println("Bus not found.");
                        } else {
                            System.out.print("Bus ID: ");
                            bid = scan.nextLine();
                            System.out.println("\nEnter your details: ");
                            System.out.print("name: ");
                            name = scan.nextLine();
                            System.out.printf("Mobile No: ");
                            mobileno = scan.nextLine();
                            System.out.printf("Gender: ");
                            gender = scan.nextLine();
                            System.out.print("age: ");
                            age = scan.nextInt();
                            passenger p = new passenger(name, age, gender, mobileno);
                            t = new ticket(p, bus.busFind(bid), 100);
                            ticketQueries.addTicket(t);
                            System.out.println("ticket booked");
                            t.getB().setavailSeats(t.getB().getavailSeats() - 1);
                            System.out.println(t);
                        }
                        break;

                    case "-us":
                        driverQueries.updating();
                        System.out.println("salary updated.");
                        break;

                    case "-pt":
                        System.out.print("Enter ticket ID: ");
                        tid = scan.nextLine();
                        System.out.println(ticket.ticketfinder(tid));
                        break;

                    case "-smn":
                        System.out.print("Enter mobile no: ");
                        mobileno = scan.nextLine();
                        ticket.ticketfinder(ticketQueries.searchTicket(mobileno));
                        break;

                    case "-cb":
                        System.out.println("Enter your  details:");
                        System.out.printf("Name: ");
                        name = scan.nextLine();
                        System.out.printf("Ticket ID: ");
                        tid = scan.nextLine();
                        Operations op = new Operations();
                        if (op.searchPerson(name, tid) == 1) {
                            System.out.println("Your ticket has been confirmed.");
                        } else {
                            System.out.println("Please book your ticket.");
                        }
                        break;

                    case "-cd":
                        System.out.println("Enter your  details:");
                        System.out.printf("Name: ");
                        name = scan.nextLine();
                        System.out.printf("Ticket ID: ");
                        tid = scan.nextLine();
                        Operations op1 = new Operations();
                        if (op1.searchPerson(name, tid) == 0) {
                            System.out.println("Your ticket has been cancelled.");
                        } else {
                            System.out.println("sorry.");
                        }
                        break;

                    case "-umn":
                        System.out.print("Enter your ticket Id: ");
                        tid = scan.nextLine();
                        System.out.print("Enter name: ");
                        name = scan.nextLine();
                        System.out.print("Enter updated mobile No: ");
                        mobileno = scan.nextLine();
                        ticketQueries.updateTicket(tid, name, mobileno);
                        break;

                    case "-d":
                        System.out.print("Enter your ticket ID: ");
                        tid = scan.nextLine();
                        System.out.print("Enter your bus ID: ");
                        bid = scan.nextLine();
                        ticketQueries.deleteTicket(tid, bid);
                        break;

                    case "-p":
                        System.out.print("Enter bus id to print passenger list: ");
                        bid = scan.nextLine();
                        ticketQueries.passengerList(bid);
                        break;

                    case "-pa":
                        AllPassengerList abs = new AllPassengerList();
                        abs.printPassengerList();
                        break;

                    case "-pd":
                        driver.PrintDriverBook();
                        break;

                    case "-h":
                        System.out.println("-b to book tickets from csv.");
                        System.out.println("-bt to book ticket.");
                        System.out.println("-pt to print ticket.");
                        System.out.println("-un to update name.");
                        System.out.println("-umn to update mobileNo.");
                        System.out.println("-d to cancel ticket.");
                        System.out.println("-p for passenger list.");
                        System.out.println("-cb to confirm ticket booking.");
                        System.out.println("-cd to confirm ticket cancellation.");
                        System.out.println("-pa to print passenger list of all buses.");
                        System.out.println("-smn to find ticket using mobile no.");
                        System.out.println("-pd to print list of drivers.");
                        System.out.println("-ud to update salary of drivers.");
                        System.out.println("exit to terminate the program.");
                        break;

                    case "exit":
                        busQueries.truncateBus();
                        driverQueries.truncateDriver();
                        ticketQueries.truncateTicket();
                        a = 1;
                        break;

                    default:
                        break;
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
