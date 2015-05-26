package util;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtility
{
	public static void main(String[] args)
	{
		String path = "/Users/irina/Documents/coding/Hadoop/astro_data/";
		String msg = "hello";
		String name= "tmp.txt";
//		saveToFile(path, name, msg);
		createDirectory(path, 65);
	}
	
	public static void createDirectory(String path,int i)
	{
		FileSystem fs = FileSystems.getDefault();
		Path out = fs.getPath(path+i);
		try
		{
			Files.createDirectory(out);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void saveToFile(String path, String name, String msg)
	{
        try
		{
        		FileSystem fs = FileSystems.getDefault();
        		Path out = fs.getPath(path+name);
			Files.createFile(out);
			Files.write(Paths.get(path+name), msg.getBytes());

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> extractPatternList(String regex, 
			List<String> initialList, String prefix)
	{		
		Matcher m = null;
		List<String> links = new ArrayList<String>();
		
		for (String initial : initialList)
		{
			m = Pattern.compile(regex).matcher(initial);
			if (m.matches())
			{
				links.add(prefix+m.group("link"));
			}
		}
		
		return links;
	}

}
