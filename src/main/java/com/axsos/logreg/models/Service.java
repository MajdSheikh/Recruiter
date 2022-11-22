package com.axsos.logreg.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
	import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="services")
public class Service {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Range(min=1,max=10)
    private Integer ContractorRating=5;
    
    
    @Range(min=1,max=10)
    private Integer OwnerRating=5;
    
    @NotEmpty(message="Specialization is required!")
    @Size(min=2, max=30, message="specialization must be between 3 and 30 characters")
    private String specialization;
    
    public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	@NotEmpty(message="Location is required!")
    @Size(min=2, max=30, message="Location must be between 3 and 30 characters")
    private String location;
	
	
	
    private boolean status=true;
    
    @NotEmpty(message="Description is required!")
    @Size(max=300, message="Description must be less than 300 characters")
    private String description; 
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startingDate;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date finishingDate;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
	
	
	
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
    
    public Service(){
    	
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getContractorRating() {
		return ContractorRating;
	}
	public void setContractorRating(Integer contractorRating) {
		ContractorRating = contractorRating;
	}
	public Integer getOwnerRating() {
		return OwnerRating;
	}
	public void setOwnerRating(Integer ownerRating) {
		OwnerRating = ownerRating;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getFinishingDate() {
		return finishingDate;
	}
	public void setFinishingDate(Date finishingDate) {
		this.finishingDate = finishingDate;
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
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
    
    
}
