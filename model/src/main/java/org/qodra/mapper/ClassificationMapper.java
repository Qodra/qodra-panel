package org.qodra.mapper;

import org.qodra.dao.Classification;

import java.util.List;

public interface ClassificationMapper {

    public void addClassification(Classification classification);

    public void deleteClassification(Classification classification);

    public List<Classification> getClassificationByMediaAndUser(Classification classification);

    public Classification getById(String id);
}
