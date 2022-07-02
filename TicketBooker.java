package com.booking.train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TicketBooker {
	static int availableLowerBerths = 1;
	static int availableMiddleBerths = 1;
	static int availableUpperBerths = 1;
	static int availableRacTickets = 1;
	static int availableWaitingList = 1;

	static List<Integer> waiatingListPassengers = new LinkedList<Integer>();
	static List<Integer> racListPassengers = new LinkedList<Integer>();
	static List<Integer> berthBookings = new ArrayList<>();

	static List<String> lowerBerthsPositions = new ArrayList<>(Arrays.asList("1L"));
	static List<String> middleBerthsPositions = new ArrayList<>(Arrays.asList("1M"));
	static List<String> upperBerthsPositions = new ArrayList<>(Arrays.asList("1U"));
	static List<String> racPositions = new ArrayList<>(Arrays.asList("1RAC"));
	static List<String> waitingListPositions = new ArrayList<>(Arrays.asList("1WL"));

	static Map<Integer, Passenger> passengers = new HashMap<>();

	public void bookLowerBerth(Passenger p, String seat) {
		System.out.println("---------------Lower berth confirmed---------------");
		p.allotedBerth = "L";
		p.seatNumber = seat;

		availableLowerBerths--;
		lowerBerthsPositions.remove(0);
		berthBookings.add(p.passengerId);
		passengers.put(p.passengerId, p);

	}

	public void bookMiddleBerth(Passenger p, String seat) {
		System.out.println("---------------Middle berth confirmed---------------");
		p.allotedBerth = "M";
		p.seatNumber = seat;

		availableMiddleBerths--;
		middleBerthsPositions.remove(0);
		berthBookings.add(p.passengerId);
		passengers.put(p.passengerId, p);

	}

	public void bookUpperBerth(Passenger p, String seat) {
		System.out.println("---------------Upper berth confirmed---------------");
		p.allotedBerth = "U";
		p.seatNumber = seat;

		availableUpperBerths--;
		upperBerthsPositions.remove(0);
		berthBookings.add(p.passengerId);
		passengers.put(p.passengerId, p);
	}

	public void bookRACtickets(Passenger p, String seat) {
		System.out.println("---------------RAC confirmed---------------");
		p.allotedBerth = "RAC";
		p.seatNumber = seat;

		availableRacTickets--;
		racPositions.remove(0);
		racListPassengers.add(p.passengerId);
		passengers.put(p.passengerId, p);
	}

	public void bookWaitingTickets(Passenger p, String seat) {
		System.out.println("---------------Waiting list confirmed---------------");
		p.allotedBerth = "WAIT";
		p.seatNumber = seat;

		availableWaitingList--;
		waitingListPositions.remove(0);
		waiatingListPassengers.add(p.passengerId);
		passengers.put(p.passengerId, p);
	}

	public void viewBookedTickets() {
		for (Passenger p : passengers.values()) {
			System.out.println("PASSENGER ID :" + p.passengerId);
			System.out.println(" Name :" + p.name);
			System.out.println(" Age :" + p.age);
			System.out.println(" Status :" + p.seatNumber);
			System.out.println("Alloted Berth :" + p.allotedBerth);
			System.out.println("--------------------------");

		}
	}

	public void viewAvailableTickets() {
		System.out.println("Available Lower Berths " + availableLowerBerths);
		System.out.println("Available Middle Berths " + availableMiddleBerths);
		System.out.println("Available Upper Berths " + availableUpperBerths);
		System.out.println("Availabel RACs " + availableRacTickets);
		System.out.println("Available Waiting List " + availableWaitingList);
		System.out.println("--------------------------");
	}

	public static void cancelTicket(int passengerID) {
		Passenger p = passengers.get(passengerID);
		passengers.remove(passengerID);
		String allotedBerth = p.allotedBerth;
		int index = -1;

		if (allotedBerth.equals("L")) {
			for (Integer passenger : berthBookings) {
				++index;
				if (passenger == passengerID)
					break;
			}
			berthBookings.remove(index);
			availableLowerBerths++;
			lowerBerthsPositions.add(p.seatNumber);

		} else if (allotedBerth.equals("M")) {
			for (Integer passenger : berthBookings) {
				++index;
				if (passenger == passengerID)
					break;
			}
			berthBookings.remove(index);
			availableMiddleBerths++;
			middleBerthsPositions.add(p.seatNumber);
		} else if (allotedBerth.equals("U")) {
			for (Integer passenger : berthBookings) {
				++index;
				if (passenger == passengerID)
					break;
			}
			berthBookings.remove(index);
			availableUpperBerths++;
			upperBerthsPositions.add(p.seatNumber);
		} else if (allotedBerth.equals("RAC")) {
			for (Integer passenger : racListPassengers) {
				++index;
				if (passenger == passengerID)
					break;
			}
			racListPassengers.remove(index);
			availableRacTickets++;
			racPositions.add(p.seatNumber);
		} else if (allotedBerth.equals("WAIT")) {
			for (Integer passenger : waiatingListPassengers) {
				++index;
				if (passenger == passengerID)
					break;
			}
			waiatingListPassengers.remove(index);
			availableWaitingList++;
			waitingListPositions.add(p.seatNumber);
		}

		if (availableLowerBerths > 0 || availableMiddleBerths > 0 || availableUpperBerths > 0) {

			if (racListPassengers.size() > 0) {
				Passenger racPassenger = passengers.get(racListPassengers.get(0));
				passengers.remove(racPassenger.passengerId);
				BookTicket.bookTicket(racPassenger);
				availableRacTickets++;
				racListPassengers.remove(0);
				racPositions.add(racPassenger.seatNumber);
			}
		}
		if (availableRacTickets > 0) {
			if (waiatingListPassengers.size() > 0) {
				Passenger waitingListPassenger = passengers.get(waiatingListPassengers.get(0));
				System.out.println("waitingListPassenger - " + waitingListPassenger);
				passengers.remove(waitingListPassenger.passengerId);
				BookTicket.bookTicket(waitingListPassenger);
				availableWaitingList++;
				waiatingListPassengers.remove(0);
				waitingListPositions.add(waitingListPassenger.seatNumber);

			}
		}

	}

}
