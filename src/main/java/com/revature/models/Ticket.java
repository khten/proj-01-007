package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A reimbursement ticket
 * 
 * @author Damion Shirkey, Jen Osborne, Kenneth Burke
 *
 */
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int transactionId;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "description", nullable = false, length = 50)
	private String description;

	@JsonIgnore // add this for tables that are referencing items
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employeeId;

	@Enumerated(EnumType.STRING)

	@Column(name = "status")

	private Status status;

	@Column(name = "requested_by")
	private String requestedBy;

	public Ticket() {
		super();
	}

	public Ticket(int transactionId, double amount, String description, Employee employeeId, Status status,
			String requestedBy) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.description = description;
		this.employeeId = employeeId;
		this.status = status;
		this.requestedBy = requestedBy;
	}

	public Ticket(double amount, String description, Employee employeeId, Status status, String requestedBy) {
		super();
		this.amount = amount;
		this.description = description;
		this.employeeId = employeeId;
		this.status = status;
		this.requestedBy = requestedBy;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, description, employeeId, requestedBy, status, transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(description, other.description) && Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(requestedBy, other.requestedBy) && status == other.status
				&& transactionId == other.transactionId;
	}

	@Override
	public String toString() {
		return "Ticket [transactionId=" + transactionId + ", amount=" + amount + ", description=" + description
				+ ", employeeId=" + employeeId + ", status=" + status + ", requestedBy=" + requestedBy + "]";
	}
}