package com.booking.train;

import java.util.Scanner;

public class BookTicket {

	public static void bookTicket(Passenger pass) {
		String name = pass.name; 
		int age=pass.age;
		String berthPreferance = pass.berthPreference;
		TicketBooker booker = new TicketBooker();
		if (berthPreferance.equals("L") && TicketBooker.availableLowerBerths != 0
				|| berthPreferance.equals("M") && TicketBooker.availableMiddleBerths != 0
				|| berthPreferance.equals("U") && TicketBooker.availableUpperBerths != 0) {
			System.out.println("Preferred Berth Available");

			if (berthPreferance.equals("L")) {
				Passenger p = pass;
				booker.bookLowerBerth(p, TicketBooker.lowerBerthsPositions.get(0));
			} else if (berthPreferance.equals("M")) {
				Passenger p = pass;
				booker.bookMiddleBerth(p, TicketBooker.middleBerthsPositions.get(0));
			} else if (berthPreferance.equals("U")) {
				Passenger p = pass;
				booker.bookUpperBerth(p, TicketBooker.upperBerthsPositions.get(0));
			}

		} else {
			if (TicketBooker.availableLowerBerths > 0) {
				Passenger p = pass;
				booker.bookLowerBerth(p, TicketBooker.lowerBerthsPositions.get(0));
			} else if (TicketBooker.availableMiddleBerths > 0) {
				Passenger p = pass;
				booker.bookMiddleBerth(p, TicketBooker.middleBerthsPositions.get(0));
			} else if (TicketBooker.availableUpperBerths > 0) {
				Passenger p = pass;
				booker.bookUpperBerth(p, TicketBooker.upperBerthsPositions.get(0));
			} else if (TicketBooker.availableRacTickets > 0) {
				Passenger p = pass;
				booker.bookRACtickets(p, TicketBooker.racPositions.get(0));
			} else if (TicketBooker.availableWaitingList > 0) {
				Passenger p = pass;
				booker.bookWaitingTickets(p, TicketBooker.waitingListPositions.get(0));
			}else {
				System.out.println("----------------No tickets available----------------");
			}

		}
	}
	
	public static void cancelTicket(int passengerID) {
		TicketBooker.cancelTicket(passengerID);
	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean bool = true;
		while (bool) {
			System.out.println(
					"Choose the options below: \n1 - Booking\n2 - Cancel Ticket\n3 - View Available Tickets\n4 - View Booked Tickets\n5-Exit");
			int option = scan.nextInt();
			switch (option) {
			case 1: {
				System.out.println("Enter Name, age and berth Preferance(L, M, U):");
				String name = scan.next();
				int age = scan.nextInt();
				String berthPreferance = scan.next();
                Passenger p = new Passenger(name,age,berthPreferance);
				bookTicket(p);
				break;
			}
			case 2: {
				System.out.println("Enter Passenger ID: ");
				int passengerID = scan.nextInt();
				cancelTicket(passengerID);
				
				break;
			}
			case 3: {
				TicketBooker booker = new TicketBooker();
				booker.viewAvailableTickets();
				break;
			}
			case 4: {
				TicketBooker booker = new TicketBooker();
				booker.viewBookedTickets();
				break;
			}
			case 5: {
				break;
			}
			}
		}
	}


}
