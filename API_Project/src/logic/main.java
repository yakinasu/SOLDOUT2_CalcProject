package logic;

import java.net.MalformedURLException;
import java.net.URL;

class Main{
    public static void main(String[]args) throws MalformedURLException{

    	URL url = new URL("https://so2-api.mutoys.com/master/item.json");
    	System.out.println(url);
    }
}