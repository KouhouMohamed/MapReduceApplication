package appCalculsEmployees;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;


public class WeatherWritable implements Writable{
	 
	private Long nbrMesure;
	private Double spWindMin;
	private Double spWindMax;
	private Double spWindMoy;
	private Double tmpMin;
	private Double tmpMax;


	@Override
	public String toString() {
		return "SpeedWind( "+nbrMesure.toString() + ", "+ spWindMin + ", " + spWindMax
				+ ", " + spWindMoy + ") Tmp( "+ tmpMin + "," + tmpMax + ")";
	}

	public void setTmpMin(Double tmpMin) {
		this.tmpMin = tmpMin;
	}

	public Double getTmpMin() {
		return tmpMin;
	}

	public void setTmpMax(Double tmpMax) {
		this.tmpMax = tmpMax;
	}

	public Double getTmpMax() {
		return tmpMax;
	}
	public void setSpWindMin(Double spWindMin) {
		this.spWindMin = spWindMin;
	}

	public Double getSpWindMin() {
		return spWindMin;
	}
	public void setSpWindMax(Double spWindMax) {
		this.spWindMax = spWindMax;
	}

	public Double getSpWindMax() {
		return spWindMax;
	}
	public void setSpWindMoy(Double spWindMoy) {
		this.spWindMoy = spWindMoy;
	}

	public Double getSpWindMoy() {
		return spWindMoy;
	}

	public void readFields(DataInput in) throws IOException {
		nbrMesure = in.readLong();
		spWindMin=in.readDouble();
		spWindMax=in.readDouble();
		spWindMoy=in.readDouble();
		tmpMin=in.readDouble();
		tmpMax=in.readDouble();
		
	}

	public void write(DataOutput out) throws IOException {
		out.writeLong(nbrMesure);
		out.writeDouble(spWindMin);
		out.writeDouble(spWindMax);
		out.writeDouble(spWindMoy);
		out.writeDouble(tmpMin);
		out.writeDouble(tmpMax);
		
	}

	public Long getNbrMesure() {
		return nbrMesure;
	}

	public void setNbrMesure(Long nbrMesure) {
		this.nbrMesure = nbrMesure;
	}

}
