package data.service;

import data.dto.request.AddDelayDto;
import data.dto.response.DelayUserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AttendRecordService {

    void addDelayMin(AddDelayDto addDelayDto);

    List<DelayUserDto> getAllByUser(Long id);
}
