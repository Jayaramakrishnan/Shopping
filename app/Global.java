import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import play.Application;
import play.GlobalSettings;

/**
 * Application wide behaviour. We establish a Spring application context for the dependency injection system and configure Spring Data.
 */
public class Global extends GlobalSettings
{

    /**
     * Declare the application context to be used - a Java annotation based application context requiring no XML.
     */
    final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStart(final Application app)
    {
        super.onStart(app);
        ctx.register(SpringDataJpaConfiguration.class);
        ctx.scan("com.crackers");
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
    @EnableNeo4jRepositories("com.crackers.repositories")
    @EnableTransactionManagement
    public static class SpringDataJpaConfiguration extends Neo4jConfiguration
    {

        @Override
        @Bean
        public Neo4jServer neo4jServer()
        {
            return new RemoteServer("http://localhost:7474", "neo4j", "root");
        }

        @Override
        @Bean
        public SessionFactory getSessionFactory()
        {
            return new SessionFactory("com.crackers.model.*");
        }

        @Bean
        @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
        public Session getSession() throws Exception
        {
            return super.getSession();
        }
    }
}