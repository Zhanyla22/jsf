package data.service;

import data.dto.request.AddDelayDto;
import org.springframework.stereotype.Service;


public interface AttendRecordService {

    void addDelayMin(AddDelayDto addDelayDto) throws Exception;
}
