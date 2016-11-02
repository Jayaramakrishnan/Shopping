import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;

import play.Application;
import play.GlobalSettings;
import play.Play;

/**
 * Application wide behaviour. We establish a Spring application context for the dependency injection system and configure Spring Data.
 */
public class Global extends GlobalSettings
{

	/**
	 * Declare the application context to be used - a Java annotation based application context requiring no XML.
	 */
	final private AnnotationConfigApplicationContext	ctx	= new AnnotationConfigApplicationContext();

	/**
	 * Sync the context lifecycle with Play's.
	 */
	@Override
	public void onStart(final Application app)
	{
		super.onStart(app);
		ctx.register(SpringDataJpaConfiguration.class);
		ctx.scan("com");
		ctx.refresh();
		ctx.start();
	}

	/**
	 * Sync the context lifecycle with Play's.
	 */
	@Override
	public void onStop(final Application app)
	{
		ctx.close();
		super.onStop(app);
	}

	/**
	 * Controllers must be resolved through the application context. There is a special method of GlobalSettings that we can override to resolve a given controller. This resolution is required by the Play router.
	 */
	@Override
	public <A> A getControllerInstance(Class<A> aClass)
	{
		return ctx.getBean(aClass);
	}

	/**
	 * This configuration establishes Spring Data concerns including those of JPA.
	 */
	@Configuration
	@EnableJpaRepositories("com.crackers.repositories")
	public static class SpringDataJpaConfiguration
	{

		@Bean
		public EntityManagerFactory entityManagerFactory()
		{
			return Persistence.createEntityManagerFactory(Play.application().configuration().getString("jpa.default"));
		}

		@Bean
		public HibernateExceptionTranslator hibernateExceptionTranslator()
		{
			return new HibernateExceptionTranslator();
		}

		@Bean
		public JpaTransactionManager transactionManager()
		{
			return new JpaTransactionManager();
		}
	}
}