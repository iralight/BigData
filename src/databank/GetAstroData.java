package databank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import streams.StringUtility;


public class GetAstroData
{
	static final String INITIAL_PAGE_PREFIX =
			"http://www.astro.com/astro-databank/Special:AllPages/";
	static final String REGEX = "<a href=\"(?<link>/astro-databank/\\w*,\\w*)\".*</a></li>";
	static final String RESULT_PREFIX = "http://www.astro.com";
	static final String HADOOP_PATH = "/Users/irina/Documents/coding/Hadoop/astro_data/";
	static final String FILE_PREFIX = "astro";

	public static void main(String args[])
	{
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz"
				.toUpperCase()
				.toCharArray();

		for (int i = 0; i < alphabet.length; i++)
		{
			String letterLink = INITIAL_PAGE_PREFIX+alphabet[i];	
			System.out.println(letterLink);
			getPage(REGEX, RESULT_PREFIX, letterLink, alphabet[i]);
	
		}
						
	}

	private static StringBuffer savePage(String letterLink,
			StringBuffer page)
	{
		URL url;
		BufferedReader br;
		String line;
		InputStream is = null;
		try
		{
			url = new URL(letterLink);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			
			while((line = br.readLine()) != null)
			{
				page.append(line);
				page.append("\n");
			}
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (is!=null)
				{
					is.close();					
				}
			}
			catch(IOException ioe)
			{
				
			}
		}
		
		return page;
	}
	
	private static void getPage(final String REGEX,
			final String RESULT_PREFIX, String letterLink, 
			int letter)
	{
		System.out.println("Letter: "+letter);

		StringUtility.createDirectory(HADOOP_PATH, letter);

		URL url;
		BufferedReader br;
		String line;
		InputStream is = null;
		try
		{
			url = new URL(letterLink);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			int count = 0;
			
			while((line = br.readLine()) != null)
			{
				getAllChildPages(REGEX, RESULT_PREFIX, line, 
						letter, count);
				count++;
			}
						
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (is!=null)
				{
					is.close();					
				}
			}
			catch(IOException ioe)
			{
				
			}
		}
	}

	private static void getAllChildPages(final String REGEX,
			final String RESULT_PREFIX, String line,
			int letter, int count)
	{
		List<String> separatedByLi = 
				Pattern.compile("<li>")
					.splitAsStream(line)
					.sorted()
					.collect(Collectors.toList());
		List<String> links = 
				StringUtility
				.extractPatternList(REGEX, separatedByLi, 
						RESULT_PREFIX);
		StringBuffer page = new StringBuffer();
		
		for (String link : links)
		{
			page = savePage(link, page);
		}
		
		if (page.length()==0)
		{
			return;
		}
		
		StringUtility.saveToFile(HADOOP_PATH, "/"
									+letter+"/"+FILE_PREFIX+count+".txt", 
				page.toString());
	}
}
