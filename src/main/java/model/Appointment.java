package model;

import java.time.LocalDate;

public class Appointment {
	private int id;
	private LocalDate date;
	private int slot;
	private String status;
	private Pet pet;
	private Trainer trainer;
	private Profile client;
	
	//Getters and Setters method
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	public Profile getClient() {
		return client;
	}
	public void setClient(Profile client) {
		this.client = client;
	}
	
}
