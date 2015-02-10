package org.qodra.service;


import com.google.common.base.Preconditions;
import org.apache.ibatis.session.SqlSession;
import org.qodra.dao.Classification;
import org.qodra.database.MyBatisUtil;
import org.qodra.mapper.ClassificationMapper;

import java.util.List;
import java.util.UUID;

public class ClassificationService {

    public List<Classification> getClassificationByMediaAndUser(Classification classification) {
        Preconditions.checkNotNull(classification, classification.getUser().getId(), classification.getMedia().getId());

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Classification> result = null;
        try {
            ClassificationMapper classificationMapper = sqlSession.getMapper(ClassificationMapper.class);
            result = classificationMapper.getClassificationByMediaAndUser(classification);
        } finally {
            sqlSession.close();
        }

        return result;
    }

    public Classification addClassification(Classification classification) {
        Preconditions.checkNotNull(classification, classification.getUser().getId(), classification.getMedia().getId());

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        Classification result = null;
        try {
            if(classification.getId() == null){
                classification.setId(UUID.randomUUID().toString());
            }
            ClassificationMapper classificationMapper = sqlSession.getMapper(ClassificationMapper.class);
            classificationMapper.addClassification(classification);
            result = classificationMapper.getById(classification.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return result;
    }

    public void deleteClassification(Classification classification) {
        Preconditions.checkNotNull(classification);

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            ClassificationMapper classificationMapper = sqlSession.getMapper(ClassificationMapper.class);
            classificationMapper.deleteClassification(classification);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
}
