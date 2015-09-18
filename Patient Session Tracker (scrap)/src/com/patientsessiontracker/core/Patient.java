package com.patientsessiontracker.core;

/**
 * 
 * @author Evan Pederson
 *
 */

public class Patient {
	
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private int AQR;
	private int AT;
	private int AWT;
	private int CCT;
	private int CMT;
	private int EMS;
	private int GT;
	private int HKM;
	private int IST;
	private int LASER;
	private int NFB;
	private int NRT;
	private int NT;
	private int PMT;
	private int RIFE;
	private int RRT;
	private int SRT;
	private int TRX;
	private int US;
	private int VIBE;
	
	public Patient(String firstName, String middleName, String lastName, int AQR, int AT, int AWT,
			int CCT, int CMT, int EMS, int GT, int HKM, int IST, int LASER, int NFB, int NRT,
			int NT, int PMT, int RIFE, int RRT, int SRT, int TRX, int US, int VIBE) {
		this(0, firstName, middleName, lastName, AQR, AT, AWT, CCT, CMT, EMS, GT, HKM, IST, LASER,
			 NFB, NRT, NT, PMT, RIFE, RRT, SRT, TRX, US, VIBE);
	}
	
	public Patient(int id, String firstName, String middleName, String lastName, int AQR, int AT, int AWT,
			int CCT, int CMT, int EMS, int GT, int HKM, int IST, int LASER, int NFB, int NRT,
			int NT, int PMT, int RIFE, int RRT, int SRT, int TRX, int US, int VIBE) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.AQR = AQR;
		this.AT = AT;
		this.AWT = AWT;
		this.CCT = CCT;
		this.CMT = CMT;
		this.EMS = EMS;
		this.GT = GT;
		this.HKM = HKM;
		this.IST = IST;
		this.LASER = LASER;
		this.NFB = NFB;
		this.NRT = NRT;
		this.NT = NT;
		this.PMT = PMT;
		this.RIFE = RIFE;
		this.RRT = RRT;
		this.SRT = SRT;
		this.TRX = TRX;
		this.US = US;
		this.VIBE = VIBE;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAQR() {
		return AQR;
	}
	public void setAQR(int AQR) {
		this.AQR = AQR;
	}
	
	public int getAT() {
		return AT;
	}
	public void setAT(int AT) {
		this.AT = AT;
	}
	
	public int getAWT() {
		return AWT;
	}
	public void setAWT(int AWT) {
		this.AWT = AWT;
	}
	
	public int getCCT() {
		return CCT;
	}
	public void setCCT(int CCT) {
		this.CCT = CCT;
	}
	
	public int getCMT() {
		return CMT;
	}
	public void setCMT(int CMT) {
		this.CMT = CMT;
	}
	
	public int getEMS() {
		return EMS;
	}
	public void setEMS(int EMS) {
		this.EMS = EMS;
	}
	
	public int getGT() {
		return GT;
	}
	public void setGT(int GT) {
		this.GT = GT;
	}
	
	public int getHKM() {
		return HKM;
	}
	public void setHKM(int HKM) {
		this.HKM = HKM;
	}
	
	public int getIST() {
		return IST;
	}
	public void setIST(int IST) {
		this.IST = IST;
	}
	
	public int getLASER() {
		return LASER;
	}
	public void setLASER(int LASER) {
		this.LASER = LASER;
	}
	
	public int getNFB() {
		return NFB;
	}
	public void setNFB(int NFB) {
		this.NFB = NFB;
	}
	
	public int getNRT() {
		return NRT;
	}
	public void setNRT(int NRT) {
		this.NRT = NRT;
	}
	
	public int getNT() {
		return NT;
	}
	public void setNT(int NT) {
		this.NT = NT;
	}
	
	public int getPMT() {
		return PMT;
	}
	public void setPMT(int PMT) {
		this.PMT = PMT;
	}
	
	public int getRIFE() {
		return RIFE;
	}
	public void setRIFE(int RIFE) {
		this.RIFE = RIFE;
	}
	
	public int getRRT() {
		return RRT;
	}
	public void setRRT(int RRT) {
		this.RRT = RRT;
	}
	
	public int getSRT() {
		return SRT;
	}
	public void setSRT(int SRT) {
		this.SRT = SRT;
	}
	
	public int getTRX() {
		return TRX;
	}
	public void setTRX(int TRX) {
		this.TRX = TRX;
	}
	
	public int getUS() {
		return US;
	}
	public void setUS(int US) {
		this.US = US;
	}
	
	public int getVIBE() {
		return VIBE;
	}
	public void setVIBE(int VIBE) {
		this.VIBE = VIBE;
	}

	@Override
	public String toString() {
		return String.format("Patient [id=%s, firstName=%s, middleName=%s, lastName=%s, AQR=%s, AT=%s, AWT=%s, CCT=%s, CMT=%s, EMS=%s, GT=%s, HKM=%s, IST=%s, LASER=%s, NFB=%s, NRT=%s, NT=%s, PMT=%s, RIFE=%s, RRT=%s, SRT=%s, TRX=%s, US=%s, VIBE=%s]", 
				id, firstName, middleName, lastName, AQR, AT, AWT, CCT, CMT, EMS, GT, HKM, IST, LASER, NFB, NRT, NT, PMT, RIFE, RRT, SRT, TRX, US, VIBE);
	}

}
