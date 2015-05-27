package util;

public class ZodiacMapping
{
	public enum Zodiacs {
	    ARIES("Aries"),
	    TAURUS("Taurus"),
	    GEMINI("Gemini"),
	    CANCER("Cancer"),
	    LEO("Leo"),
	    VIRGO("Virgo"),
	    LIBRA("Libra"),
	    SCORPIO("Scorpio"),
	    SAGGITARIUS("Saggitarius"),
	    CAPRICORN("Capricorn"),
	    AQUARIUS("Aquarius"),
	    PISCIES("Pisces");	 
	    
	    private String value;
	    private Zodiacs(String value)
	    {
	    		this.value = value;
	    }
	    public String getValue()
	    {
	    		return value;
	    }
	};

}
