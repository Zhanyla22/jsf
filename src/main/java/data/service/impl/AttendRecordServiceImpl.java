package data.service.impl;

import data.dto.request.AddDelayDto;
import data.entity.AttendRecord;
import data.repo.AttendanceRepo;
import data.repo.UserRepo;
import data.service.AttendRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendRecordServiceImpl implements AttendRecordService {

    private final AttendanceRepo attendanceRepo;

    private final UserRepo userRepo;

    @Override
    public void addDelayMin(AddDelayDto addDelayDto) throws Exception {
        if (!attendanceRepo.existsByUserEntityIdAndAttendDate(addDelayDto.getUserId(), addDelayDto.getDate())) {
            AttendRecord attendRecord1 = new AttendRecord();
            attendRecord1.setUserEntity(userRepo.findById(addDelayDto.getUserId()).orElseThrow(() -> new RuntimeException("no user with this id")));
            attendRecord1.setDelayInMin(addDelayDto.getDelayInMin());
            attendRecord1.setAttendDate(addDelayDto.getDate());

            AttendRecord attendRecord = attendanceRepo.findLast(addDelayDto.getUserId())
                    .orElseThrow(() -> new Exception("нет даты до этого"));
            if (attendRecord == null) {
                attendRecord1.setStreak(1);
            }
            else if (attendRecord.getAttendDate().getDayOfWeek().getValue() == 5 && addDelayDto.getDate().getDayOfWeek().getValue() == 1) {
                attendRecord1.setStreak(attendRecord.getStreak() + 1);
            }
            else if (attendRecord.getAttendDate().plusDays(1) == addDelayDto.getDate()) {
                attendRecord1.setStreak(attendRecord1.getStreak() + 1);
                System.out.println(attendRecord.getAttendDate().plusDays(1));
            }
            else attendRecord1.setStreak(1);
            attendRecord1.setMoney((long) (attendRecord1.getStreak()* attendRecord.getDelayInMin()));
            attendanceRepo.save(attendRecord1);
        }
    }
}
