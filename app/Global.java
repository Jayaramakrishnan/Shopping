import org.apache.log4j.xml.DOMConfigurator;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import play.Application;
import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;

/**
 * Application wide behaviour. We establish a Spring application context for the dependency injection system and configure Spring Data.
 */
public class Global extends GlobalSettings {

	/**
	 * Declare the application context to be used - a Java annotation based application context requiring no XML.
	 */
	final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

	/**
	 * Sync the context lifecycle with Play's.
	 */
	@Override
	public void onStart(final Application app) {
		super.onStart(app);
		initLog4jCongiguration();
		ctx.register(SpringDataJpaConfiguration.class);
		ctx.scan("com");
		ctx.refresh();
		ctx.start();
	}

	/**
	 * Sync the context lifecycle with Play's.
	 */
	@Override
	public void onStop(final Application app) {
		ctx.close();
		super.onStop(app);
	}

	/**
	 * Controllers must be resolved through the application context. There is a special method of GlobalSettings that we can override to resolve a given controller. This resolution is required by the Play router.
	 */
	@Override
	public <A> A getControllerInstance(Class<A> aClass) {
		return ctx.getBean(aClass);
	}

	/**
	 * This configuration establishes Spring Data concerns including those of neo4j.
	 */
	@Configuration
	@EnableNeo4jRepositories("com.crackers.repositories")
	@EnableTransactionManagement
	public static class SpringDataJpaConfiguration extends Neo4jConfiguration {

		@Bean
		public org.neo4j.ogm.config.Configuration getConfiguration() {
			org.neo4j.ogm.config.Configuration cfg = new org.neo4j.ogm.config.Configuration();
			cfg.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver").setURI("http://localhost:7474").setCredentials("neo4j", "iamwatiam");
			return cfg;
		}

		@Override
		@Bean
		public SessionFactory getSessionFactory() {
			return new SessionFactory(getConfiguration(), "com.crackers.model");
		}
	}

	private void initLog4jCongiguration() {
		String log4jFileFullPah = "conf/log4j.xml";
		DOMConfigurator.configure(log4jFileFullPah);
	}

	@SuppressWarnings("unchecked")
	public <T extends EssentialFilter> Class<T>[] filters() {
		return new Class[] { GzipFilter.class };
	}
}