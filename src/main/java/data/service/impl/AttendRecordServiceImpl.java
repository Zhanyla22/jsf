package data.service.impl;

import data.dto.request.AddDelayDto;
import data.dto.response.DelayUserDto;
import data.entity.AttendRecord;
import data.exceptions.BaseException;
import data.mapper.AttendRecordMapper;
import data.repo.AttendanceRepo;
import data.repo.UserRepo;
import data.service.AttendRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendRecordServiceImpl implements AttendRecordService {

    private final AttendanceRepo attendanceRepo;

    private final UserRepo userRepo;

    @Override
    public void addDelayMin(AddDelayDto addDelayDto) {
        if (!attendanceRepo.existsByUserEntityIdAndAttendDate(addDelayDto.getUserId(), addDelayDto.getDate())) {
            AttendRecord attendRecord1 = new AttendRecord();
            attendRecord1.setUserEntity(userRepo.findById(addDelayDto.getUserId()).orElseThrow(() ->
                    new BaseException("такого юзер с айди =" + addDelayDto.getUserId() + " нет", HttpStatus.CONFLICT)));
            attendRecord1.setDelayInMin(addDelayDto.getDelayInMin());
            attendRecord1.setAttendDate(addDelayDto.getDate());

            AttendRecord attendRecord = attendanceRepo.findLast(addDelayDto.getUserId()).orElse(null);
            if (attendRecord == null) {
                attendRecord1.setStreak(1);
            } else if (attendRecord.getAttendDate().getDayOfWeek().getValue() == 5 && addDelayDto.getDate().getDayOfWeek().getValue() == 1) {
                attendRecord1.setStreak(attendRecord.getStreak() + 1);
                attendRecord1.setMoney(attendRecord.getMoney() + attendRecord1.getStreak()*addDelayDto.getDelayInMin());
            } else if (attendRecord.getAttendDate().plusDays(1).compareTo(addDelayDto.getDate()) == 0) {
                attendRecord1.setStreak(attendRecord.getStreak() + 1);
                attendRecord1.setMoney(attendRecord.getMoney()+attendRecord1.getStreak()*addDelayDto.getDelayInMin());
            } else if (addDelayDto.getDate().getDayOfWeek().getValue() == 6 | addDelayDto.getDate().getDayOfWeek().getValue() == 7) {
                throw new BaseException("В субботу и воскресенье отдыхайте", HttpStatus.CONFLICT);
            } else {
                attendRecord1.setStreak(1);
                attendRecord1.setMoney((long) (attendRecord1.getStreak() * addDelayDto.getDelayInMin()));
            }
            attendanceRepo.save(attendRecord1);
        } else throw new BaseException("В этот день уже опаздывали, выберите другой день", HttpStatus.CONFLICT);
    }

    @Override
    public List<DelayUserDto> getAllByUser(Long id) {
        List<DelayUserDto> delayUserDtos = AttendRecordMapper.entityListToDelayUserDtoList(attendanceRepo.getAttendRecordsByUserEntityId(id));
        return delayUserDtos;
    }
}
