package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mapreduce.mappers.ZodiacSignMapper;

import org.junit.Test;

import util.StringUtility;
import util.ZodiacMapping;
import util.ZodiacMapping.Zodiacs;

public class ZodiacMappingTest
{
	@Test 
	public void checkIsZodiac()
	{
		String word = "Sun_0_Scorpio";
		String groupName = "zodiac";
		System.out.println(StringUtility.substituteWordOnPattern(ZodiacSignMapper.REGEX, 
						word, groupName));
	}
}
