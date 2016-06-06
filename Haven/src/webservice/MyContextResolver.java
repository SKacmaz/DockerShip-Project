package webservice;

import java.net.MalformedURLException;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;

import resourcerer.DockerEndpoint;
import resourcerer.Resourcerer;

@Provider
public class MyContextResolver extends SingletonTypeInjectableProvider<Context, Resourcerer>{
	public MyContextResolver() throws MalformedURLException {
		super(Resourcerer.class, new Resourcerer(new DockerEndpoint()));
	}
}

