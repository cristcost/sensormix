package com.google.developers.gdgfirenze.datamodeljpa;

import java.util.List;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sensor")
@Access(AccessType.FIELD)
public class JpaSensor {

	@Id
	@Column(name="id")
	private String id;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="description", nullable=false)
	private String description;

	@Column(name="lastSeen")
	public Date lastSeen;

	@Column(name="lat")
	private Double lat;
	
	@Column(name="lng")
	private Double lng;
	
	@OneToMany(targetEntity=JpaAbstractSample.class, mappedBy="sensorId")
	private List<JpaAbstractSample> samples;
	
	public List<JpaAbstractSample> getSamples() {
		return samples;
	}

	public void setSamples(List<JpaAbstractSample> samples) {
		this.samples = samples;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
}
