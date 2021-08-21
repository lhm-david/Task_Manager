package com.codingdojo.BeltReviewer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	private String location;
	private String state;
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
	private User host;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "joinevents",
    		joinColumns = @JoinColumn(name = "event_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    		)
    private List<User> eventUsers;

	@OneToMany(mappedBy="eventMessage", fetch = FetchType.LAZY)
	private List<Message> messages;
	
	public Event() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getEventUsers() {
		return eventUsers;
	}
	
	public void setEventUsers(List<User> eventUsers) {
		this.eventUsers = eventUsers;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}
}
