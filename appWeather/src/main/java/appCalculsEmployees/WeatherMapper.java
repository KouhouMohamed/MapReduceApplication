package appCalculsEmployees;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeatherMapper extends Mapper<LongWritable, Text, Text, WeatherWritable>{

	static int nbrLigne = 0;
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, WeatherWritable>.Context context)
			throws IOException, InterruptedException {
			
			System.out.println("Mapper");
			String ligne=value.toString();
			String[] champs=ligne.split("\",\"",-1);
			boolean isTmp = false;
			boolean isWin = false;
			if(champs!=null && !champs[10].equals("WND")) {
				
				String[] winds = champs[10].split(",",-1);
				String tmps = champs[13].split(",",4)[0];
				
				WeatherWritable outputValue=new WeatherWritable(); 
			
				if(!winds[3].equals("9999"))isWin=true;
				if(!tmps.equals("+9999"))isTmp=true;
				if(isWin && isTmp) {
					outputValue.setNbrMesure(1l);
					Double wind = Double.parseDouble(winds[3]);
					
					Double tmp = Double.parseDouble(tmps);
					outputValue.setSpWindMin(wind);
					outputValue.setSpWindMax(wind);
					outputValue.setSpWindMoy(wind);
					outputValue.setTmpMax(tmp);
					outputValue.setTmpMin(tmp);
					context.write(new Text(champs[1].substring(0, 7)), outputValue);
				
				}
				
				
			
			}
			
		
	}
	
}
