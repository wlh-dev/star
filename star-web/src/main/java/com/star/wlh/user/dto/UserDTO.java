package com.star.wlh.user.dto;

import java.util.Objects;

public class UserDTO {
	private Integer id ;
	private String name;
	private String gender;

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(gender, userDTO.gender);
	}

	@Override public int hashCode() {
		return Objects.hash(id, name, gender);
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserDTO() {
	}

	public UserDTO(Integer id, String name, String gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
}
