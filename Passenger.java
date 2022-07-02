package com.booking.train;

public class Passenger {
	static int id = 1;
	String name;
	int age;
	String berthPreference;
	int passengerId;
	String allotedBerth;
	String seatNumber;

	public Passenger(String name, int age, String berthPreference) {
		this.name = name;
		this.age = age;
		this.berthPreference = berthPreference;
		this.passengerId = id++;
		allotedBerth = "";
		seatNumber = "";
	}
}
