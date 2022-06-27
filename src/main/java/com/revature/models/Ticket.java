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

/**
 * A reimbursement ticket
 * 
 * @author Damion Shirkey, Jen Osborne, Kenneth Burke
 *
 */
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int transactionId;

	@Column(name = "description", nullable = false, length = 50)
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employeeId;

	@Enumerated(EnumType.STRING)

	@Column(name = "status")

	private Status status;

	public Ticket() {
		super();
	}

	/**
	 * Creates a reimbursement ticket
	 * 
	 * @param transactionId - The unique id number of the transaction
	 * @param description   - A description of the transaction
	 * @param employeeId    - The employee ID that submitted the ticket
	 * @param status        - The satus of the ticked, set by Role enum
	 */

	public Ticket(int transactionId, String description, Employee employeeId, Status status) {
		super();
		this.transactionId = transactionId;
		this.description = description;
		this.employeeId = employeeId;
		this.status = status;
	}

	public Ticket(String description, Employee employeeId, Status status) {
		super();
		this.description = description;
		this.employeeId = employeeId;
		this.status = status;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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

	@Override
	public int hashCode() {
		return Objects.hash(description, employeeId, status, transactionId);
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
		return Objects.equals(description, other.description) && employeeId == other.employeeId
				&& status == other.status && transactionId == other.transactionId;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", description=" + description + ", employeeId="
				+ employeeId + ", status=" + status + "]";
	}
	
	
}