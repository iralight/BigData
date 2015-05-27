package mapreduce.mappers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import util.StringUtility;
import util.ZodiacMapping.Zodiacs;

/**
 * A mapper class for ZodiacSignCount
 * 
 * @author Irina Likhtina iralight@gmail.com Do not distribute without
 *         permission.
 *
 */

public class ZodiacSignMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	public static String GROUP_NAME = "zodiac";
	public static String REGEX = "(Sun|Moon|Asc)_\\d*_(?<"+GROUP_NAME+">\\w*)";

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException
	{
		String line = value.toString();
		for (String word : line.split("\\W+"))
		{
			if (word.length() > 0)
			{
				context.write(new Text(getZodiacName(word)), new IntWritable(1));					
			}
		}
	}
	
	public static String getZodiacName(String word)
	{
		String zodiacWord = StringUtility.substituteWordOnPattern(REGEX, 
				word, GROUP_NAME);
		word = (zodiacWord==null)?word:zodiacWord.toUpperCase();
		
		for (Zodiacs zodiac : Zodiacs.values())
		{
			if(word.equals(zodiac.getValue()))
			{
				word = zodiac.name();
			}
		}
		
		return word;
	}
}
