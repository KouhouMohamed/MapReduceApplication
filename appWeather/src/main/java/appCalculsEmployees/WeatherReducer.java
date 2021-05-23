package appCalculsEmployees;


import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WeatherReducer extends Reducer<Text, WeatherWritable,Text, WeatherWritable>{

	@Override
	protected void reduce(Text key, Iterable<WeatherWritable> values,
			Reducer<Text, WeatherWritable, Text, WeatherWritable>.Context context) throws IOException, InterruptedException {
		Double spWindMin=null;
		Double spWindMax=null;
		Double tmpMin=null;
		Double tmpMax=null;
		Double som=0d;// calculer des salaires et par suite la moyenne
		Long compteur=0l;// calculer le nbre d'employ�s
		for(WeatherWritable val:values) {
			
			if(spWindMin==null || spWindMin>val.getSpWindMin())spWindMin=val.getSpWindMin();
			if(spWindMax==null || spWindMax<val.getSpWindMax())spWindMax=val.getSpWindMax();
			
			if(tmpMin==null || tmpMin>val.getTmpMin())tmpMin=val.getTmpMin();
			if(tmpMax==null || tmpMax<val.getTmpMax())tmpMax=val.getTmpMax();
			som+=val.getSpWindMoy();
			compteur+=val.getNbrMesure();
		}
		System.out.println("Reducer");
		WeatherWritable result=new WeatherWritable();
		//System.out.println(som/compteur);
		result.setNbrMesure(compteur);
		result.setSpWindMin(spWindMin);
		result.setSpWindMax(spWindMax);
		result.setSpWindMoy(som/compteur);
		result.setTmpMin(tmpMin);
		result.setTmpMax(tmpMax);
		context.write(key, result);

	}
}
