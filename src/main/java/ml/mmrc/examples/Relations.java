package ml.mmrc.examples;

import ml.mmrc.App;
import ml.mmrc.model.Comment;
import ml.mmrc.model.User;
import org.hibernate.Session;

import java.sql.Timestamp;

public class Relations {

    public void createOneToManyBiDirectonal() {
        try (Session session = App.services.sessionFactory.openSession()) {
            User user = new User();
            user.setEmail("mmamula@mmrc.ml");
            user.setPassword("$2a$04$35Fjd5Yfmk9bushWRV2L8u6ImZGdldapyl2ALLzLwxfonTBOllpnS");
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            user.getComments().add(new Comment("Test comment", user));
            user.getComments().add(new Comment("New Comment", user));
            session.save(user);   
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
    
    public void fetchWithRelationData() {
        try (Session session = App.services.sessionFactory.openSession()) {
            System.out.println(session.createQuery("from User").list());
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}
