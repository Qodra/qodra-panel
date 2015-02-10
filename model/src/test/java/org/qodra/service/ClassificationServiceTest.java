package org.qodra.service;

import org.junit.Test;
import org.qodra.dao.Classification;
import org.qodra.dao.Media;
import org.qodra.dao.User;

import static org.junit.Assert.assertNotNull;

public class ClassificationServiceTest {

    @Test
    public void createClassification() {
        //Init
        Media media = new Media();
        media.setId("ID");
        media.setPath("/path/to/a/media.mp4");
        media.setTitle("This is a title!");
        MediaService mediaService = new MediaService();
        media = mediaService.createMedia(media);

        String username = "user_test";
        String password = "user_test";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail("user_test@qodra.org");
        UserService userService = new UserService();
        user = userService.createUser(user);

        Classification classification = new Classification();
        classification.setMedia(media);
        classification.setUser(user);
        classification.setOrigin("MANUAL");
        classification.setTag("http://dbpedia.org/resource/Brazil");
        classification.setClassification("RELATED");

        ClassificationService classificationService = new ClassificationService();

        //Execute
        classification = classificationService.addClassification(classification);

        //Assert
        assertNotNull(classification.getId());

        //Dispose
        classificationService.deleteClassification(classification);
        mediaService.deleteMedia(media);
        userService.deleteUser(user);

    }

    @Test(expected = NullPointerException.class)
    public void classificationNullOnAddClassificationMustReturnNullPointerExceptionException() {
        //Init
        ClassificationService classificationService = new ClassificationService();

        //Execute
        classificationService.addClassification(null);
    }

    @Test(expected = NullPointerException.class)
    public void userNullOnAddClassificationMustReturnNullPointerExceptionException() {
        //Init
        ClassificationService classificationService = new ClassificationService();
        Classification classification = new Classification();
        classification.setMedia(new Media());

        //Execute
        classificationService.addClassification(classification);
    }

    @Test(expected = NullPointerException.class)
    public void mediaNullOnAddClassificationMustReturnNullPointerExceptionException() {
        //Init
        ClassificationService classificationService = new ClassificationService();
        Classification classification = new Classification();
        classification.setUser(new User());

        //Execute
        classificationService.addClassification(classification);
    }


    @Test(expected = NullPointerException.class)
    public void classificationNullOnAddDeleteMustReturnNullPointerExceptionException() {
        //Init
        ClassificationService classificationService = new ClassificationService();

        //Execute
        classificationService.deleteClassification(null);
    }


    @Test(expected = NullPointerException.class)
    public void classificationNullOnGetClassificationByMediaAndUserMustReturnNullPointerExceptionException() {
        //Init
        ClassificationService classificationService = new ClassificationService();

        //Execute
        classificationService.getClassificationByMediaAndUser(null);
    }


}
