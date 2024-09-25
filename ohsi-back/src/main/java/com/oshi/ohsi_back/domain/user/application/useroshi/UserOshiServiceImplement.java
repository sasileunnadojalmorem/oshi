package com.oshi.ohsi_back.domain.user.application.useroshi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oshi.ohsi_back.core.properties.ErrorCode;
import com.oshi.ohsi_back.domain.ohsi.domain.entity.OshiEntity;
import com.oshi.ohsi_back.domain.ohsi.infrastructure.OshiRepository;
import com.oshi.ohsi_back.domain.ohsi.presentation.dto.response.OshiResponseDto;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserEntity;
import com.oshi.ohsi_back.domain.user.domain.entitiy.UserOshiEntity;
import com.oshi.ohsi_back.domain.user.infrastructure.UserOshiRepository;
import com.oshi.ohsi_back.domain.user.infrastructure.UserRepository;
import com.oshi.ohsi_back.domain.user.presentation.dto.request.AddUserOshiRequsetDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.AddUserOshiResponseDto;
import com.oshi.ohsi_back.domain.user.presentation.dto.response.GetUserOshiResponseDto;
import com.oshi.ohsi_back.exception.exceptionclass.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserOshiServiceImplement implements UserOshiService {

    private final UserRepository userRepository;
    private final UserOshiRepository userOshiRepository;
    private final OshiRepository oshiRepository;

    @Override
    public AddUserOshiResponseDto findstate(AddUserOshiRequsetDto dto, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new CustomException(ErrorCode.NOT_EXISTED_USER);
        }

        int userid = userEntity.getUserId();
        int oshiId = dto.getOshi_id();
        UserOshiEntity checkuserOshiEntity = userOshiRepository.findByUser_idAndOshi_id(userid, oshiId);

        if ("check".equals(dto.getState())) {
            // 오시 정보가 존재하는지 확인하고 결과를 반환
            return checkuserOshiEntity != null ? new AddUserOshiResponseDto(1) : new AddUserOshiResponseDto(0);
        } else if ("handle".equals(dto.getState())) {
            if (checkuserOshiEntity != null) {
                // 오시 정보가 존재하면 삭제
                userOshiRepository.delete(checkuserOshiEntity);
                return new AddUserOshiResponseDto(0);
            } else {
                // 오시 정보가 없으면 추가
                UserOshiEntity userOshiEntity = new UserOshiEntity(userid, oshiId);
                userOshiRepository.save(userOshiEntity);
                return new AddUserOshiResponseDto(1);
            }
        }

        throw new CustomException(ErrorCode.DATABASE_ERROR);
    }

    @Override
    public GetUserOshiResponseDto GetUserOshi(String email) {
        try {
            // 1. 유저를 이메일로 찾고 존재 여부 확인
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) {
                throw new CustomException(ErrorCode.NOT_EXISTED_USER);
            }

            int userId = userEntity.getUserId();

            // 2. 커스텀 쿼리를 사용하여 유저의 오시 리스트와 관련된 데이터를 한 번에 가져옴
            List<UserOshiEntity> oshiEntities = userOshiRepository.findByUser(userId);

            List<OshiResponseDto> oshiResponseDtos = oshiEntities.stream()
            .map(oshiEntity -> new OshiResponseDto(oshiEntity.getOshi())) // 필요한 경우 status 추가 가능
            .collect(Collectors.toList());
            // 3. 성공적으로 유저 오시 리스트 반환
            return new GetUserOshiResponseDto(oshiResponseDtos);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }
}