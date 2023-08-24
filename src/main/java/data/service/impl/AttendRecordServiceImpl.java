package data.service.impl;

import data.dto.request.AddDelayDto;
import data.dto.response.AddAttendResponse;
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
    public AddAttendResponse addDelayMin(AddDelayDto addDelayDto) {
        if (!attendanceRepo.existsByUserEntityIdAndAttendDate(addDelayDto.getUserId(), addDelayDto.getDate())) {
            AttendRecord attendRecord1 = new AttendRecord();
            attendRecord1.setUserEntity(userRepo.findById(addDelayDto.getUserId()).orElseThrow(() ->
                    new BaseException("такого юзер с айди =" + addDelayDto.getUserId() + " нет", HttpStatus.NOT_FOUND)));
            attendRecord1.setDelayInMin(addDelayDto.getDelayInMin());
            attendRecord1.setAttendDate(addDelayDto.getDate());

            AttendRecord attendRecord = attendanceRepo.findLast(addDelayDto.getUserId()).orElse(null);
            //Выбранная дата суббота или воскресенье
            if (addDelayDto.getDate().getDayOfWeek().getValue() == 6 || addDelayDto.getDate().getDayOfWeek().getValue() == 7) {
                throw new BaseException("В субботу и воскресенье отдыхайте", HttpStatus.NOT_FOUND);
            }
            //1-опоздание
            else if (attendRecord == null) {
                attendRecord1.setStreak(1);
                attendRecord1.setMoney((long) (addDelayDto.getDelayInMin()));
            }
            //1 case : если последнее опоздание было в пятницу а след в понедельник
            //2 case : чтобы определить streak
            else if (attendRecord.getAttendDate().getDayOfWeek().getValue() == 5 && addDelayDto.getDate().getDayOfWeek().getValue() == 1
                    || attendRecord.getAttendDate().plusDays(1).compareTo(addDelayDto.getDate()) == 0) {
                attendRecord1.setStreak(attendRecord.getStreak() + 1);
                attendRecord1.setMoney(attendRecord.getMoney() + (long) attendRecord1.getStreak() * attendRecord1.getDelayInMin());
            }
            //если просто в любой день опоздал - начало streak'a
            else {
                attendRecord1.setStreak(1);
                attendRecord1.setMoney((long) (addDelayDto.getDelayInMin()));
            }
            attendanceRepo.save(attendRecord1);
            return AddAttendResponse.builder()
                    .userId(attendRecord1.getUserEntity().getId())
                    .delayInMin(attendRecord1.getDelayInMin())
                    .attendDate(attendRecord1.getAttendDate())
                    .money(attendRecord1.getMoney())
                    .streak(attendRecord1.getStreak())
                    .build();
        } else throw new BaseException("В этот день уже опаздывали, выберите другой день", HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public List<DelayUserDto> getAllByUser(Long id) {
        List<DelayUserDto> delayUserDtos = AttendRecordMapper
                .entityListToDelayUserDtoList(attendanceRepo.getAttendRecordsByUserEntityId(id));
        return delayUserDtos;
    }
}
