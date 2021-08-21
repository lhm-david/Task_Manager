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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
	
	
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		@Email (message = "Email must be valid")
	    private String email;
		@NotBlank
		@Size(max=30)
		private String firstName;
		@NotBlank
		@Size(max=30)
		private String lastName;
		private String location;
		private String state;
		
		@OneToMany(mappedBy="host", fetch = FetchType.LAZY)
		private List<Event> events;
		
		@Size(min=5, message="Password must be greater than 5 characters")
	    private String password;
	    @Transient
	    private String passwordConfirmation;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	    		name = "joinevents",
	    		joinColumns = @JoinColumn(name = "user_id"),
	    		inverseJoinColumns = @JoinColumn(name = "event_id")
	    		)
	    private List<Event> userEvents;
	    
	    @OneToMany(mappedBy="userMessage", fetch = FetchType.LAZY)
	    private List<Message> userMessage;
	    
	    
		public User() {
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}
		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
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
		public List<Event> getUserEvents() {
			return userEvents;
		}
		public void setUserEvents(List<Event> userEvents) {
			this.userEvents = userEvents;
		}
		public List<Message> getUserMessage() {
			return userMessage;
		}
		public void setUserMessage(List<Message> userMessage) {
			this.userMessage = userMessage;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public List<Event> getEvents() {
			return events;
		}
		public void setEvents(List<Event> events) {
			this.events = events;
		}
}
