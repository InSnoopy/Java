package hotel;

public class HotelVO {

	private String guestRoom; 
	private String gusetName;
	
	
	public HotelVO(String guestRoom, String gusetName) {
		super();
		this.guestRoom = guestRoom;
		this.gusetName = gusetName;
	}
	
	public String getGuestRoom() {
		return guestRoom;
	}
	public void setGuestRoom(String guestRoom) {
		this.guestRoom = guestRoom;
	}
	public String getGusetName() {
		return gusetName;
	}
	public void setGusetName(String gusetName) {
		this.gusetName = gusetName;
	}

	@Override
	public String toString() {
		return "HotelVO [guestRoom=" + guestRoom + ", gusetName=" + gusetName + "]";
	}
	
	
	
	
	
}
