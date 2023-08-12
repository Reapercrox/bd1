package tec.bd;

import tec.bd.weather.*;



public class App 
{
    public static void main( String[] args )
    {
        weatherService service = new weatherServiceImpl();
        System.out.println(weatherService.getTemperature("Alajuela"));
    }
}
