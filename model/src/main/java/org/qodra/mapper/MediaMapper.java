package org.qodra.mapper;

import org.qodra.dao.Media;

import java.util.List;

public interface MediaMapper {

    public List<Media> getAll();

    public void createMedia(Media media);

    public Media getById(String id);

    public void updateMedia(Media media);

    public void deleteMedia(Media media);

}
