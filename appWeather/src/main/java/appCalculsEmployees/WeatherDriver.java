package appCalculsEmployees;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class WeatherDriver {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		String inputPath = "/home/kouhou/Downloads/input/*/*";
		String outputPath = "/home/kouhou/Downloads/output";
		FileUtil.fullyDelete(new File(outputPath));
		
		String inputPathHdfs = "/user/inputs/output/1901/*";
		String outputPathHdfs = "/user/outputs";
		
		URL url = new URL("https://www.ncei.noaa.gov/data/global-hourly/archive/csv/1902.tar.gz");
		Configuration conf=new Configuration();
		
		/*
		 * if we are working on HDFS
		 */
		//conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
		//conf.set("fs.defaultFS","hdfs://localhost:9000/");
		//conf.set("yarn.ressourcemanager.address","172.18.0.2:9000");
		//conf.set("dfs.client.use.datanode.hostname","true");
		/*for (int i=1901; i<1911;i++) {
			String path = "/home/kouhou/Downloads/input1/"+i;
			downloadToLocal(url,path);
		}
		*/
		
		Job job=Job.getInstance(conf, "Weather");
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(WeatherWritable.class);
		job.setMapperClass(WeatherMapper.class);
		job.setCombinerClass(WeatherCombiner.class);
		job.setReducerClass(WeatherReducer.class);
		job.setJarByClass(WeatherDriver.class);
		
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		job.waitForCompletion(true);	
		System.out.println("here");

	}
	
	static void downloadToLocal(URL url,String inputPath) {
		try {
		File fSrc=new File(inputPath);
		FileUtils.copyURLToFile(url,fSrc,100000,100000);
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
