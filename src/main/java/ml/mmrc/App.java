package ml.mmrc;

import lombok.Data;
import ml.mmrc.examples.Relations;
import ml.mmrc.model.Comment;
import ml.mmrc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App {

    public static ServiceContainer services = new ServiceContainer();

    @Data
    public static class ServiceContainer {
        public SessionFactory sessionFactory;
    }

    public static void main( String[] args ) {

        App.bootstrap();

        Relations relations = new Relations();
        relations.createOneToManyBiDirectonal();
        relations.fetchWithRelationData();

        services.sessionFactory.close();
    }

    private static void bootstrap() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1/hibertest");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.addPackage(User.class.getPackage().getName());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Comment.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        services.setSessionFactory(sessionFactory);
    }
}
