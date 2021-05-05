package models;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "companies")
@NamedQueries({
    @NamedQuery(
        name = "getAllCompanies",
        query = "SELECT c FROM Company AS c ORDER BY c.id DESC"
        ),
    @NamedQuery(
            name = "getCompaniesCount",
            query ="SELECT COUNT(c) FROM Company AS c"
            ),
    @NamedQuery(
            name = "checkRegisteredCompanies",
            query = "SELECT COUNT(c) FROM Company AS c WHERE c.name = :name  AND c.client = :client AND c.tell = :tell AND c.address = :address"
        ),

})

@Entity
public class Company{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@Column(name = "name",nullable = false )
	private String name;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Column(name = "client" , nullable = false )
	private String client;

	@Column(name = "tell" , nullable = false ,unique = true)
	private String tell;

	@Column(name = "address",nullable = false,unique = true)
	private String address;

	@Column(name = "created_at", nullable = false)
	private Timestamp created_at;

	@Column(name = "updated_at" ,nullable = false)
	private Timestamp updated_at;

	@Column(name = "delete_flag", nullable = false)
	private Integer delete_flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}





}
