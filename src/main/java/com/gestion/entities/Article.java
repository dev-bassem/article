package com.gestion.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

import javax.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Label is mandatory")
	@Column(name = "label")
	private String label;
	@Column(name = "price")
	private float price;
	@Column(name = "picture")
	private String picture;

	@Column(name = "pictureprofile")
	private String pictureprofile;
	
	

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "provider_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Provider provider;

	public Article() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	
	public String getPictureprofile() {
		return pictureprofile;
	}

	public void setPictureprofile(String pictureprofile) {
		this.pictureprofile = pictureprofile;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", label=" + label + ", price=" + price + ", picture=" + picture + ", provider="
				+ provider + "]";
	}
	
	

}
