package com.axsos.logreg.models;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="companies")
public class Company {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="name is required!")
    @Size(min=2, max=30, message="name must be between 3 and 30 characters")
    private String title;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
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
        name = "users_companies", 
        inverseJoinColumns = @JoinColumn(name = "user_id"),
        joinColumns = @JoinColumn(name = "company_id") 
    )
    private List<User> employees;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User contractor;
    
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<User> getEmployees() {
		return employees;
	}
	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}
	public User getContractor() {
		return contractor;
	}
	public void setContractor(User contractor) {
		this.contractor = contractor;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}

	@OneToMany(mappedBy="company", fetch = FetchType.LAZY)
    private List<Service> services;
}
