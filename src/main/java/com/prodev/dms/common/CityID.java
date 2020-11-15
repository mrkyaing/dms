package com.prodev.dms.common;

public enum CityID {
	NAYPYITAW (1),
	YANGON (2),
	MANDALAY (3)
	;
	
	private final int cityID;
	
	CityID(int cityID) {
		this.cityID = cityID;
	}
	
	public int getcityID() {
        return this.cityID;
    }
}
