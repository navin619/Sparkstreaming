package com.saurzcode.twitter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class TwitterStreamExample {

	  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
	    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
	    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
	    // add some track terms
	    endpoint.trackTerms(Lists.newArrayList("twitter", "#TRUMP"));

	    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
	    // Authentication auth = new BasicAuth(username, password);

	    // Create a new BasicClient. By default gzip is enabled.
	    Client client = new ClientBuilder()
	            .hosts(Constants.STREAM_HOST)
	            .endpoint(endpoint)
	            .authentication(auth)
	            .processor(new StringDelimitedProcessor(queue))
	            .build();

	    // Establish a connection
	    client.connect();

	    // Do whatever needs to be done with messages
	    for (int msgRead = 0; msgRead < 1000; msgRead++) {
	      String msg = queue.take();
	      System.out.println(msg);
	    }

	    client.stop();

	  }
	  public static void main(String[] args) {
	    try {
	      TwitterStreamExample.run("5Jnt1zLqp0NzUFLsRoB2aQIQ0",
				  "shek6vuYNC9Kih0wacgGrYq8veI9abP4jERMFU69eyQlrOOcuL",
				  "2604584714-UTVEL6QCA8H2uUHAJaWCxTI6K1ShAB51tdunVwA",
				  "lY584q5lPdeQCXOBnXX8FZ8KjBvllkLBC317OoCqbvYQj");
	    } catch (InterruptedException e) {
	      System.out.println(e);
	    }
	  }
	}