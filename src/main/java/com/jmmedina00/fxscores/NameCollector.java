package com.jmmedina00.fxscores;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for collecting random usernames from spinxo.com
 */
public class NameCollector {
	private final static String link = "http://spinxo.com/";

	private static InputStream connect() throws Exception {
		//Suprisingly, no special headers are necessary in order to get the HTML document.
		URL url = new URL(link);
		URLConnection urlConnection = url.openConnection();
		return urlConnection.getInputStream();
	}

	public static List<String> getNames() throws Exception {
		List<String> names = new ArrayList<>();

		InputStream inputStream = connect();
		String response = new String(inputStream.readAllBytes());

		Document document = Jsoup.parse(response);
		Elements links = Selector.select(".clear .list01 > li > a", document);

		for (Element element : links) {
			names.add(element.text());
		}

		return names;
	}
}
