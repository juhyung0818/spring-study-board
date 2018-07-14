package com.juhyung.jstagram.user.service;

import com.juhyung.jstagram.user.mapper.FollowMapper;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FollowService {
    private final FollowMapper followMapper;

    public List<User> getFollowers(User user) {
        return followMapper.selectFollowers(user.getUserKey());
    }

    public List<User> getFollowings(User user) {
        return followMapper.selectFollowings(user.getUserKey());
    }

    public void requestFollow(int me, int you) {
        followMapper.insertFollow(me, you);
    }

    public void cancelFollow(int me, int you) {
        followMapper.deleteFollow(me, you);
    }
}
