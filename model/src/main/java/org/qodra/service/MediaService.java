package org.qodra.service;

import com.google.common.base.Preconditions;
import org.apache.ibatis.session.SqlSession;
import org.qodra.dao.Media;
import org.qodra.database.MyBatisUtil;
import org.qodra.mapper.MediaMapper;

import java.util.List;
import java.util.UUID;

public class MediaService {

    public List<Media> getAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Media> result = null;
        try {
            MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
            result = mediaMapper.getAll();
        } finally {
            sqlSession.close();
        }

        return result;

    }

    public Media getById(String id) {
        Preconditions.checkNotNull(id);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        Media result = null;
        try {
            MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
            result = mediaMapper.getById(id);
        } finally {
            sqlSession.close();
        }

        return result;

    }

    public Media createMedia(Media media) {
        Preconditions.checkNotNull(media);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        Media result = null;
        try {
            if (media.getId() == null) {
                media.setId(UUID.randomUUID().toString());
            }
            MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
            mediaMapper.createMedia(media);
            result = mediaMapper.getById(media.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return result;
    }

    public Media updateMedia(Media media) {
        Preconditions.checkNotNull(media);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        Media result = null;
        try {
            MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
            mediaMapper.updateMedia(media);
            result = mediaMapper.getById(media.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return result;

    }

    public void deleteMedia(Media media) {
        Preconditions.checkNotNull(media);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
            mediaMapper.deleteMedia(media);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

}
