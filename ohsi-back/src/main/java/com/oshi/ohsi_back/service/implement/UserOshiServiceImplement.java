package com.oshi.ohsi_back.service.implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.oshi.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.dto.response.oshi.AddUserOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.GetUserOshiResponseDto;
import com.oshi.ohsi_back.dto.response.oshi.OshiResponseDto;
import com.oshi.ohsi_back.entity.OshiEntity;
import com.oshi.ohsi_back.entity.UserEntity;
import com.oshi.ohsi_back.entity.UserOshiEntity;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;
import com.oshi.ohsi_back.properties.ErrorCode;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.repository.OshiRepository.OshiRepository;
import com.oshi.ohsi_back.repository.UserOshiRepository;
import com.oshi.ohsi_back.service.UserOshiService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserOshiServiceImplement implements UserOshiService {

    private final UserRepository userRepository;
    private final UserOshiRepository userOshiRepository;
    private final OshiRepository oshiRepository;
    @Override
    public ResponseEntity<? super AddUserOshiResponseDto> findstate(AddUserOshiRequsetDto dto, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            return AddUserOshiResponseDto.notExistUser();
        }

        int userid = userEntity.getUserId();
        int oshi_id = dto.getOshi_id();
        UserOshiEntity checkuserOshiEntity = userOshiRepository.findByUser_idAndOshi_id(userid, oshi_id);

        if ("check".equals(dto.getState())) {
            // 오시 정보가 존재하는지 확인하고 결과를 반환
            if (checkuserOshiEntity != null) {
                return AddUserOshiResponseDto.alreadyExists();
            } else {
                return AddUserOshiResponseDto.doesNotExist();
            }
        } else if ("handle".equals(dto.getState())) {
            // 오시 정보가 존재하면 삭제, 그렇지 않으면 추가
            if (checkuserOshiEntity != null) {
                return DelUserOshi(userid, oshi_id);
            } else {
                return AddUserOshi(userid, oshi_id);
            }
        }

        // state가 "check"나 "handle"이 아닌 경우의 처리
        return AddUserOshiResponseDto.databaseError();
    }

    @Override
    public ResponseEntity<? super AddUserOshiResponseDto> AddUserOshi(int user_id, int oshi_id) {
        UserOshiEntity userOshiEntity = new UserOshiEntity(user_id, oshi_id);
        userOshiRepository.save(userOshiEntity);
        return AddUserOshiResponseDto.success();
    }

    @Override
    public ResponseEntity<? super AddUserOshiResponseDto> DelUserOshi(int user_id, int oshi_id) {
        UserOshiEntity userOshiEntity = userOshiRepository.findByUser_idAndOshi_id(user_id, oshi_id);
        if (userOshiEntity != null) {
            userOshiRepository.delete(userOshiEntity);
            return AddUserOshiResponseDto.success();
        } else {
            return AddUserOshiResponseDto.doesNotExist();
        }
    }

    @Override
public ResponseEntity<? super GetUserOshiResponseDto> GetUserOshi(String email) {
    try {
        // 1. 유저를 이메일로 찾고 존재 여부 확인
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }

        int userId = userEntity.getUserId();

        // 2. 커스텀 쿼리를 사용하여 유저의 오시 리스트와 관련된 데이터를 한 번에 가져옴
        GetUserOshiResponseDto oshiResponseList = oshiRepository.findByUserId(userId);

        // 3. 오시 리스트가 없는 경우 빈 리스트 반환
        

        // 4. 성공적으로 유저 오시 리스트 반환
        return ResponseEntity.ok(oshiResponseList);

    } catch (Exception e) {
        e.printStackTrace();
            throw new CustomException(ErrorCode.DATABASE_ERROR);
    }
}
}
