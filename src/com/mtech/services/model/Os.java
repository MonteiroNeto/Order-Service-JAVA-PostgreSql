package com.mtech.services.model;

import com.mtech.services.dao.DaoOs;

public class Os {
	private int id;
	private String data;
	private String equipment;
	private String defect;
	private String service;
	private String technician;
	private double price;
	private int idCli;
	private int status;
	private int serviceType;

	// Inner Join
	private String nameCli;
	private String phoneCli;
	private String statusDescription;

	public Os() {
		super();
	}

	public Os(int id, String data, String equipment, String defect, String service, String technician, Double price,
			int idCli, int status, int serviceType) {
		super();
		this.id = id;
		this.data = data;
		this.equipment = equipment;
		this.defect = defect;
		this.service = service;
		this.technician = technician;
		this.price = price;
		this.idCli = idCli;
		this.status = status;
		this.serviceType = serviceType;
	}

	public Os(String equipment, String defect, String service, String technician, Double price, int idCli, int status,
			int serviceType) {
		super();
		this.equipment = equipment;
		this.defect = defect;
		this.service = service;
		this.technician = technician;
		this.price = price;
		this.idCli = idCli;
		this.status = status;
		this.serviceType = serviceType;
	}

	// INNER JOIN
	public Os(int id, String data, String equipment, double price, String statusDescription, String nameCli,
			String phoneCli) {
		super();
		this.id = id;
		this.data = data;
		this.equipment = equipment;
		this.price = price;
		this.statusDescription = statusDescription;
		this.nameCli = nameCli;
		this.phoneCli = phoneCli;
	}

	public String getNameCli() {
		return nameCli;
	}

	public String getPhoneCli() {
		return phoneCli;
	}

	public String getStatuDescription() {
		return statusDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getDefect() {
		return defect;
	}

	public void setDefect(String defect) {
		this.defect = defect;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdCli() {
		return idCli;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((defect == null) ? 0 : defect.hashCode());
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + id;
		result = prime * result + idCli;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + serviceType;
		result = prime * result + status;
		result = prime * result + ((technician == null) ? 0 : technician.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Os other = (Os) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (defect == null) {
			if (other.defect != null)
				return false;
		} else if (!defect.equals(other.defect))
			return false;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (id != other.id)
			return false;
		if (idCli != other.idCli)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (serviceType != other.serviceType)
			return false;
		if (status != other.status)
			return false;
		if (technician == null) {
			if (other.technician != null)
				return false;
		} else if (!technician.equals(other.technician))
			return false;
		return true;
	}

}
