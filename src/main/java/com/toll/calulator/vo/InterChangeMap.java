package com.toll.calulator.vo;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class InterChangeMap implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
    public double lat;
    public double lng;
    private String devcomment; 
    private String routeId;
    public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getDevcomment() {
		return devcomment;
	}
	public void setDevcomment(String devcomment) {
		this.devcomment = devcomment;
	}
	public ArrayList<Route> routes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(ArrayList<Route> routes) {
		this.routes = routes;
	}
}
