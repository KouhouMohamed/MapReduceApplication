package appCalculsEmployees;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WeatherCombiner extends Reducer<Text, WeatherWritable,Text, WeatherWritable> {
	@Override
	protected void reduce(Text key, Iterable<WeatherWritable> values,
			Reducer<Text, WeatherWritable, Text, WeatherWritable>.Context context) throws IOException, InterruptedException {
		Double spWindMin=null;
		Double spWindMax=null;
		Double spWindMoy=null;
		Double tmpMin=null;
		Double tmpMax=null;
		Long compteur=0l;// calculer le nbre d'employ�s
		for(WeatherWritable val:values) {
			
			if(spWindMin==null || spWindMin>val.getSpWindMin())spWindMin=val.getSpWindMin();
			if(spWindMax==null || spWindMax<val.getSpWindMax())spWindMax=val.getSpWindMax();
			
			if(tmpMin==null || tmpMin>val.getTmpMin())tmpMin=val.getTmpMin();
			if(tmpMax==null || tmpMax<val.getTmpMax())tmpMax=val.getTmpMax();
			spWindMoy=val.getSpWindMoy();
			compteur+=val.getNbrMesure();
		}
		WeatherWritable result=new WeatherWritable();
		result.setNbrMesure(compteur);
		result.setSpWindMin(spWindMin);
		result.setSpWindMax(spWindMax);
		result.setSpWindMoy(spWindMoy);
		result.setTmpMin(tmpMin);
		result.setTmpMax(tmpMax);
		context.write(key, result);

	}
}
