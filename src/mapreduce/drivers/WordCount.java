package mapreduce.drivers;

import mapreduce.mappers.WordMapper;
import mapreduce.reducers.SumReducer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Driver class for MapReduce job
 * @author Irina Likhtina iralight@gmail.com
 * Do not distribute without permission.
 *
 */
public class WordCount
{
	public static void main(String[] args) throws Exception
	{
		if(args.length != 2)
		{
			System.out.println(
					"Usage: WordCount <input dir> <output dir>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(WordCount.class);
		job.setJobName("Word Count");
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(WordMapper.class);
		job.setReducerClass(SumReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
