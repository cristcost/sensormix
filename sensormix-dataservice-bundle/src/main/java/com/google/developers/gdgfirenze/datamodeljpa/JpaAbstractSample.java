package com.google.developers.gdgfirenze.datamodeljpa;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "abstractsample")
@Access(AccessType.FIELD)
public class JpaAbstractSample {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "sensorId", nullable = false)
	private String sensorId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false)
	private Date time;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "sample", nullable = false)
	private byte[] value;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

}
