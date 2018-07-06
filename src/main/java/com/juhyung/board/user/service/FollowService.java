package com.juhyung.board.user.service;

import com.juhyung.board.user.mapper.FollowMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FollowService {
    private final FollowMapper followMapper;
}
