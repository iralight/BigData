package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * A mapper class for the job
 * 
 * @author Irina Likhtina iralight@gmail.com Do not distribute without
 *         permission.
 *
 */
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException
	{
		String line = value.toString();
		for (String word : line.split("\\W+"))
		{
			if (word.length() > 0)
			{
				context.write(new Text(word), new IntWritable(1));

			}
		}
	}
}
