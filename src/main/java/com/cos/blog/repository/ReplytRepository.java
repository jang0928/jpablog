package com.cos.blog.repository;

import com.cos.blog.dto.ReplySaveRequstDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReplytRepository extends JpaRepository<Reply,Integer>
{
    @Modifying
    @Query(value = "INSERT INTO reply(boardId,userId,content,createDate) VALUES(?1,?2,?3,now())",nativeQuery = true)
    int mSave(int boardId, int userId, String content);//update된 행의 개수를 리턴

}
