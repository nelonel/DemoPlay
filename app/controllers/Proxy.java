package controllers;

import java.net.URLEncoder;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;


public class Proxy extends Controller
{
	
	public Promise<Result> proxy()
	{
		String query = request().getQueryString("q");
		
		query = URLEncoder.encode(query);
		
		String url = "http://google.com/search?q="+query;
		
		return WS.url(url).get().map(new Function<WSResponse , Result>()
		{
			
			@Override
			public Result apply(WSResponse arg0) throws Throwable {
				
				return ok(arg0.getBody()).as("text/html");
			}
		});
		

		
	}
	
	public Promise<Result> home()
	{
		
		String url = "http://google.com";
		
		return WS.url(url).get().map(new Function<WSResponse , Result>()
		{
			
			@Override
			public Result apply(WSResponse arg0) throws Throwable {
				
				return ok(arg0.getBody()).as("text/html");
			}
		});
		

		
	}

}
