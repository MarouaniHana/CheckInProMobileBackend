package atelierSpringBoot_Ch;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WorkSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public double getStartLatitude() {
		return startLatitude;
	}
	public void setStartLatitude(double startLatitude) {
		this.startLatitude = startLatitude;
	}
	public double getStartLongitude() {
		return startLongitude;
	}
	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}
	public double getEndLatitude() {
		return endLatitude;
	}
	public void setEndLatitude(double endLatitude) {
		this.endLatitude = endLatitude;
	}
	public double getEndLongitude() {
		return endLongitude;
	}
	public void setEndLongitude(double endLongitude) {
		this.endLongitude = endLongitude;
	}
	@Override
	public String toString() {
		return "WorkSession [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", startLatitude="
				+ startLatitude + ", startLongitude=" + startLongitude + ", endLatitude=" + endLatitude
				+ ", endLongitude=" + endLongitude + "]";
	}

    
}