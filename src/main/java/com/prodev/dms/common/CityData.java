package com.prodev.dms.common;

import java.util.ArrayList;
import java.util.List;

import com.prodev.dms.domain.City;
import com.prodev.dms.domain.TownShip;

//to be used when seeding data to the database.
public class CityData {	
	public static List<City> getCityList(){
		List<City> cityList = new ArrayList<City>();
		cityList.add(new City(CityID.NAYPYITAW.getcityID(), "NayPyiTaw","NayPyiTaw"));
		cityList.add(new City(CityID.YANGON.getcityID(),"Yangon","Yangon"));	
		cityList.add(new City(CityID.MANDALAY.getcityID(),"Mandalay","Mandalay"));		
		return cityList;
	}

	private static List<TownShip> getTownShipByCityID(int cityID) {
		if (cityID == CityID.NAYPYITAW.getcityID()) {
			return getNaypyiTawTownship();
		}else if (cityID == CityID.YANGON.getcityID()) {
			return getYangonTownShip();
		}
		return null;
	}
	//to be used when seeding data to the database.
	private static List<TownShip> getNaypyiTawTownship (){
		List<TownShip> townShipList = new ArrayList<TownShip>();
		townShipList.add(new TownShip("Town Ship 1", ""));
		townShipList.add(new TownShip("Town Ship 2", ""));
		townShipList.add(new TownShip("Town Ship 3", ""));
		townShipList.add(new TownShip("Town Ship 4", ""));		
		return townShipList;
	}
	
	//to be used when seeding data to the database.
	private static List<TownShip> getYangonTownShip (){
		List<TownShip> townShipList = new ArrayList<TownShip>();
		townShipList.add(new TownShip("Town Ship 5", ""));
		townShipList.add(new TownShip("Town Ship 6", ""));
		townShipList.add(new TownShip("Town Ship 7", ""));
		townShipList.add(new TownShip("Town Ship 8", ""));
		
		return townShipList;
	}
}
