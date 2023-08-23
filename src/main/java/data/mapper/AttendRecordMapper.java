package data.mapper;

import data.dto.response.DelayUserDto;
import data.entity.AttendRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendRecordMapper {

    public static DelayUserDto entityToDelayUserDto(AttendRecord attendRecord) {
        return DelayUserDto.builder()
                .delayDate(attendRecord.getAttendDate())
                .money(attendRecord.getMoney())
                .delayMin(attendRecord.getDelayInMin())
                .streak(attendRecord.getStreak())
                .build();
    }

    public static List<DelayUserDto> entityListToDelayUserDtoList(List<AttendRecord> attendRecords){
        return attendRecords.stream().map(AttendRecordMapper::entityToDelayUserDto).collect(Collectors.toList());
    }
}
