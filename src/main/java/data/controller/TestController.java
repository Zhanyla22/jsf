package data.controller;

import data.dto.request.AddDelayDto;
import data.service.AttendRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AttendRecordService attendRecordService;

    @PostMapping("/add")
    public void add(@RequestBody AddDelayDto addDelayDto) throws Exception{
        attendRecordService.addDelayMin(addDelayDto);
    }
}
