package data.service;

import data.dto.request.AddDelayDto;
import data.dto.response.AddAttendResponse;
import data.dto.response.DelayUserDto;

import java.util.List;


public interface AttendRecordService {

    AddAttendResponse addDelayMin(AddDelayDto addDelayDto);

    List<DelayUserDto> getAllByUser(Long id);
}
