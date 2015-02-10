package org.qodra.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.qodra.dao.Media;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MediaServiceTest {

    @Test
    public void createMedia() {
        //Init
        Media media = new Media();
        media.setId("ID");
        media.setPath("/path/to/a/media.mp4");
        media.setTitle("This is a title!");
        MediaService service = new MediaService();

        //Execute
        Media result = service.createMedia(media);

        //Assert
        assertNotNull(result.getId());
    }

    @Test
    public void doUpdateMedia() {
        //Init
        String id = "ID";
        String newTitle = "A new title";
        MediaService service = new MediaService();
        Media media = service.getById(id);

        //Execute
        media.setTitle(newTitle);
        service.updateMedia(media);
        media = service.getById(id);

        //Assert
        assertEquals(newTitle, media.getTitle());

    }

    @Test
    public void removeMedia() {
        //Init
        String id = "ID";
        MediaService service = new MediaService();
        Media media = service.getById("ID");

        //Execute
        service.deleteMedia(media);
        media = service.getById(id);

        //Assert
        assertNull(media);
    }


    @Test(expected = NullPointerException.class)
    public void mediaNullOnCreateMustReturnNullPointerExceptionException() {
        //Init
        MediaService service = new MediaService();

        //Execute
        service.createMedia(null);
    }

    @Test(expected = NullPointerException.class)
    public void mediaNullOnUpdateMustReturnNullPointerExceptionException() {
        //Init
        MediaService service = new MediaService();

        //Execute
        service.updateMedia(null);
    }

    @Test(expected = NullPointerException.class)
    public void mediaNullOnDeleteMustReturnNullPointerExceptionException() {
        //Init
        MediaService service = new MediaService();

        //Execute
        service.deleteMedia(null);
    }

    @Test(expected = NullPointerException.class)
    public void idNullOnGetByIdMustReturnNullPointerExceptionException() {
        //Init
        MediaService service = new MediaService();

        //Execute
        service.getById(null);
    }

}
