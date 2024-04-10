package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Appointment {
	private String description;
	private LocalDateTime from,to;
	
	public Appointment(String description, LocalDateTime from, LocalDateTime to) {
		this.from = from;
		this.to = to;
		this.description = description;
	}
	public Appointment(String description, LocalDateTime from, Duration duration) {
		this.description = description;
		this.from = from;
		this.to = from.plus(duration);
	}
	
	public boolean equals(Appointment app) {
		return (from.isEqual(app.from) && to.isEqual(app.to) && description.equals(app.description));
	}
	public String getDescription() {
		return description;
	}
	public Duration getDuration(){
		return Duration.between(from,to);
	}
	public LocalDateTime getFrom() {
		return from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDuration(Duration duration) {
		this.to = this.from.plus(duration);
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	@Override
	public String toString() {
	    return "Appuntamento = {" +
	            " '" + description + '\'' +
	            ", Dalle =" + from +
	            ", Alle =" + to +
	            '}';
	}
}