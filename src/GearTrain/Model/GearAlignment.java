package GearTrain.Model;

public enum GearAlignment {
	AXIAL, PLANAR;
	
	public static GearAlignment getAlignment(String s){
		return ((s.toLowerCase().equals("axial") ? GearAlignment.AXIAL : GearAlignment.PLANAR));
	}
	
	public String toString(){
		return ((this.equals(AXIAL))? "Axial" : "Planar");
	}
}
